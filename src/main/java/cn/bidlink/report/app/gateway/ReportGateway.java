package cn.bidlink.report.app.gateway;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.custom.use.cloud.response.ResponseResult;
import cn.bidlink.custom.use.cloud.service.cloud.QueryConfigurationCloudService;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.appset.dal.service.DubboAppsetPrintCompanySetService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 报表访问网关
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-05-13 15:37:30
 */
@Controller
@RequestMapping("reportGateway")
public class ReportGateway {

    @Autowired
    QueryConfigurationCloudService queryConfigurationCloudService;
    @Autowired
    DubboAppsetPrintCompanySetService dubboAppsetPrintCompanySetService;

    private Logger logger = LoggerFactory.getLogger(ReportGateway.class);

    @RequestMapping("frReportRoute/{frApplicationPath}/{frViewlet}")
    public ModelAndView frReportRoute(HttpServletRequest request, HttpServletResponse response, @PathVariable String frApplicationPath, @PathVariable String frViewlet) {
        String reportUrl = reportViewResolver(request, frApplicationPath, frViewlet);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + reportUrl);
        return modelAndView;
    }

    @RequestMapping("reportWindow/{frApplicationPath}/{frViewlet}")
    public ModelAndView reportWindow(HttpServletRequest request, HttpServletResponse response, @PathVariable String frApplicationPath, @PathVariable String frViewlet) {
        ModelAndView modelAndView = new ModelAndView("report-window");
        String path = reportViewResolver(request, frApplicationPath, frViewlet);
        modelAndView.addObject("path", path);
        return modelAndView;
    }

    @RequestMapping("urlList")
    public ModelAndView urlList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("report-description");
        Map<String, String> detailMap = UrlHandler.detailMap;
        modelAndView.addObject("map",detailMap);
        return modelAndView;
    }

    private String reportViewResolver(HttpServletRequest request, String frApplicationPath, String frViewlet) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String confUrl = frApplicationPath + "/" + frViewlet;
        StringBuilder sb = new StringBuilder(UrlHandler.getUrl(confUrl));
        logger.info("公司：{} 企业ID：{} 用户：{} 登录名：{} 用户ID：{} 访问的报表：{}",UserContext.getRegCompany().getName(), UserContext.getCompanyId() ,UserContext.getUser().getName(),UserContext.getUserName(), UserContext.getUserId() ,confUrl);
        Iterator<String> iterator = parameterMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String[] strings = parameterMap.get(key);
            if( strings.length > 0 ){
                if ("templateId".equals(key) && parameterMap.containsKey("projectId") ) {
                    String[] projectIds = parameterMap.get("projectId");
                    if(projectIds.length > 0 && StringUtils.isNotEmpty(projectIds[0])){
                        String result = appendShowColumns(strings[0], Long.valueOf(projectIds[0]));
                        if( StringUtils.isNotEmpty(result) ){
                            sb.append("&").append("Columns").append("=").append(result);
                        }
                    }
                } else if (null != strings && strings.length > 0 && !"frViewlet".equals(key) && !"frApplicationPath".equals(key) && !"FineWaterMark".equals(key)) {
                    sb.append("&").append(key).append("=").append(strings[0]);
                }
            }
        }
        //调用接口查询该公司的水印值
        // todo 暂时注释获取水印 等待应用设置上线
        ServiceResult<String> fineWaterMark = dubboAppsetPrintCompanySetService.getFineWaterMark(UserContext.getCompanyId());
        if ( !fineWaterMark.getSuccess() ) {
            logger.error("获取水印失败 错误内容 {}" , fineWaterMark.getMessage());
        } else if(StringUtils.isNotEmpty(fineWaterMark.getResult())) {
            sb.append("&").append("FineWaterMark").append("=").append(fineWaterMark.getResult());
        }
        return sb.toString();
    }

    private String appendShowColumns(String templateId, Long projectId) {
        ResponseResult responseResult = queryConfigurationCloudService.queryCustomConf(UserContext.getUserId(), UserContext.getCompanyId(), templateId, projectId + "," + UserContext.getCompanyId());
        Boolean success = responseResult.getSuccess();
        if (!success) {
            logger.info("查询动态列配置失败 {} , {}" , templateId,projectId);
            throw new RuntimeException("查询动态列配置失败");
        }
        Object resultData = responseResult.getData();
        logger.info("查询动态列成功，内容：{}" ,resultData.toString() );
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject = JSON.parseObject(resultData.toString());
        JSONObject typeData = (JSONObject) jsonObject.get("typeData");
        Set<Map.Entry<String, Object>> entrySet = typeData.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            JSONObject value = (JSONObject) entry.getValue();
            JSONObject customClass = value.getJSONObject("customClass");
            Boolean isChecked = customClass.getBoolean("isChecked");
            Boolean isShow = customClass.getBoolean("isShow");
            if(BooleanUtils.isTrue(isChecked) && BooleanUtils.isTrue(isShow)){
               sb.append(value.getString("title")).append(",");
            }
        }
        if( sb.length() > 0 ){
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }
}
