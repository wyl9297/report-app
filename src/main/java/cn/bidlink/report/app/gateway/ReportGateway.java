package cn.bidlink.report.app.gateway;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.custom.use.cloud.response.ResponseResult;
import cn.bidlink.custom.use.cloud.service.cloud.QueryConfigurationCloudService;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.appset.dal.service.DubboAppsetPrintCompanySetService;
import cn.bidlink.procurement.auction.cloud.service.AuctionPrintService;
import cn.bidlink.procurement.purchase.cloud.service.ProjectRestService;
import cn.bidlink.report.app.individuation.ReportUrlAppender;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    ProjectRestService purchaseProjectRestService;
    @Autowired
    AuctionPrintService auctionPrintService;

    private Logger logger = LoggerFactory.getLogger(ReportGateway.class);

    @RequestMapping("route/{frApplicationPath}/{frViewlet}")
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

    @RequestMapping("multiWindow/{frApplicationPath}/{frViewlet}")
    public ModelAndView multiWindow(HttpServletRequest request, HttpServletResponse response, @PathVariable String frApplicationPath, @PathVariable String frViewlet) {
        ModelAndView modelAndView = new ModelAndView("multi-window");
        StringBuilder path = new StringBuilder();
        path.append("SpecialPath=").append(frApplicationPath).append("/").append(frViewlet);
        Map<String, String[]> parameterMap = request.getParameterMap();
        Iterator<String> iterator = parameterMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String[] strings = parameterMap.get(key);
            if( strings.length > 0 ){
                String value = strings[0];
                path.append("&").append(key).append("=").append(value);
            }
        }
        modelAndView.addObject("params",path.toString());
        return modelAndView;
    }

    @RequestMapping("getMultiReportMetaData")
    @ResponseBody
    public String getMultiReportMetaData(HttpServletRequest request, HttpServletResponse response, @RequestParam("SpecialPath") String SpecialPath, @RequestParam(value = "Active",required = false) String Active) {
        Map<String,String> multiUrl = UrlHandler.getMultiUrl(SpecialPath);
        if( null == multiUrl ){
            return null;
        }
        //获取项目主id
        Long primaryId = getPrimaryId(request);
        Set<String> set = null;
        if ( null != primaryId ) {
            set = queryNeedShowTab(SpecialPath, primaryId);
        }
        List<Map<String,String>> list = new ArrayList();
        Iterator<Map.Entry<String, String>> iterator = multiUrl.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if ( null != set && !set.contains(value) ) {
                continue;
            }
            Map<String, String> map = new HashMap<>();
            String url = reportViewResolver(UrlHandler.getTemplateId(SpecialPath + "/" + key),request, key);
            map.put("url",url);
            map.put("name",value);
            if ( StringUtils.isNotEmpty(Active) && Active.equals(value) ) {
                map.put("active","true");
            }
            list.add(map);
        }
        String metaData = JSONObject.toJSONString(list);
        return metaData;
    }

    @RequestMapping("urlList")
    public ModelAndView urlList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("report-description");
        Map<String, String> detailMap = UrlHandler.detailMap;
        modelAndView.addObject("map",detailMap);
        return modelAndView;
    }

    @RequestMapping("getMultiHtml")
    @ResponseBody
    public String getMultiHtml(HttpServletRequest request, HttpServletResponse response){
        StringBuilder multiDetailHtml = UrlHandler.multiDetailHtml;
        return multiDetailHtml.toString();
    }

    private String reportViewResolver(HttpServletRequest request, String frApplicationPath, String frViewlet) {
        String confUrl = frApplicationPath + "/" + frViewlet;
        return reportViewResolver( null , request, confUrl);
    }

    private String reportViewResolver(String templateId , HttpServletRequest request , String confUrl) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String url = UrlHandler.getUrl(confUrl);
        if ( StringUtils.isEmpty(url) ){
            return confUrl;
        }
        StringBuilder sb = new StringBuilder(url);
        try {
            logger.info("公司：{} 企业ID：{} 用户：{} 登录名：{} 用户ID：{} 访问的报表：{} 访问时间 {}",UserContext.getRegCompany().getName(), UserContext.getCompanyId()
                    ,UserContext.getUser().getName(),UserContext.getUserName(), UserContext.getUserId() , confUrl , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) );
        } catch (Exception e){
            logger.info("日志打印失败");
        }
        Iterator<String> iterator = parameterMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String[] strings = parameterMap.get(key);
            if( strings.length > 0 ){
                //如果参数中有templateId 则取参数中的templateId值 如果参数中没有 就取配置里写死的templateId
                if ( ("templateId".equals(key) && (parameterMap.containsKey("projectId") || parameterMap.containsKey("PrimaryId") ))
                        || ("projectId".equals(key) && !parameterMap.containsKey("templateId") && StringUtils.isNotEmpty(templateId) ) ) {
                    Long primaryId = getPrimaryId(request);
                    if(null != primaryId){
                        String extendParam = null;
                        if( parameterMap.containsKey("ExtendParam") && parameterMap.get("ExtendParam").length > 0 && StringUtils.isNotEmpty(parameterMap.get("ExtendParam")[0].toString()) ){
                            extendParam = parameterMap.get("ExtendParam")[0].toString();
                        }
                        if ("templateId".equals(key)) {
                            templateId = strings[0];
                        }
                        if ( StringUtils.isNotEmpty(templateId) && templateId.contains("&") ) {
                            String[] oriId = templateId.split("&");
                            templateId = oriId[0];
                            extendParam = oriId[1];
                            //扩展列参数查询
                            if ( StringUtils.isNotEmpty(extendParam) ) {
                                Pattern pattern = Pattern.compile("(?<=\\[)(.+?)(?=\\])");
                                Matcher matcher = pattern.matcher(extendParam);
                                while(matcher.find()){
                                    String group = matcher.group();
                                    String parameter = null;
                                    if ( "companyId".equals(group) ){
                                        parameter = UserContext.getCompanyId().toString();
                                    } else if ( "userId".equals(group) ) {
                                        parameter = UserContext.getUserId().toString();
                                    } else {
                                        parameter = request.getParameter(group);
                                    }
                                    extendParam = extendParam.replace("[" + group + "]",parameter);
                                }
                            }
                        }
                        String result = appendShowColumns(templateId,primaryId,extendParam);
                        if( StringUtils.isNotEmpty(result) ){
                            sb.append("&").append("Columns").append("=").append(result);
                        }
                        if("projectId".equals(key)){
                            sb.append("&").append(key).append("=").append(strings[0]);
                        }
                    }
                } else if (null != strings && strings.length > 0 && !"frViewlet".equals(key) && !"frApplicationPath".equals(key)
                        && !"FineWaterMark".equals(key)  && !"SpecialPath".equals(key) && !"ExtendParam".equals(key) && !"Active".equals(key) && !"PrimaryId".equals(key)) {
                    sb.append("&").append(key).append("=").append(strings[0]);
                }
            }
        }
        //调用接口查询该公司的水印值
        ServiceResult<String> fineWaterMark = dubboAppsetPrintCompanySetService.getFineWaterMark(UserContext.getCompanyId());
        if ( !fineWaterMark.getSuccess() ) {
            logger.error("获取水印失败 错误内容 {}" , fineWaterMark.getMessage());
        } else if(StringUtils.isNotEmpty(fineWaterMark.getResult())) {
            sb.append("&").append("FineWaterMark").append("=").append(fineWaterMark.getResult());
        }
        //报表定制化接口
        if (UrlHandler.urlAppenderMap.containsKey(confUrl)) {
            ReportUrlAppender reportUrlAppender = UrlHandler.urlAppenderMap.get(confUrl);
            if ( reportUrlAppender.confirm(request,confUrl,templateId,sb) ) {
                sb = reportUrlAppender.urlPostProcesser(request, confUrl, templateId, sb);
            }
        }
        return sb.toString();
    }

    private String appendShowColumns(String templateId, Long projectId , String extendParam) {
        ResponseResult responseResult = queryConfigurationCloudService.queryCustomConf(UserContext.getUserId(), UserContext.getCompanyId(), templateId, extendParam );
        Boolean success = responseResult.getSuccess();
        if (!success) {
            logger.info("查询动态列配置失败 {} , {}" , templateId,projectId);
            throw new RuntimeException("查询动态列配置失败");
        }
        Object resultData = responseResult.getData();
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

    private Set<String> queryNeedShowTab(String path , Long projectId ){
        Long companyId = UserContext.getCompanyId();
        Set<String> set = null;
        String module = path.split("/")[0];
        switch(module){
            case "purchase" :
                set = purchaseProjectRestService.findReportFormTab(projectId, companyId);

                for (String str : set) {
                    logger.error(str);
                }



                break;
            case "auction" :
                set = auctionPrintService.getPrintTitle(projectId,companyId);
                break;
        };
        return set;
    }

    //处理主业务Id不叫projectId的情况
    private Long getPrimaryId( HttpServletRequest request ){
        if( StringUtils.isNotEmpty(request.getParameter("projectId")) ){
            return Long.valueOf(request.getParameter("projectId"));
        } else if ( StringUtils.isNotEmpty(request.getParameter("PrimaryId")) ){
            String primaryIdKey = request.getParameter("PrimaryId");
            String primaryId = request.getParameter(primaryIdKey);
            if ( StringUtils.isNotEmpty(primaryId) ) {
                return Long.valueOf(primaryId);
            }
        }
        return null;
    }
}
