package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.custom.use.cloud.response.ResponseResult;
import cn.bidlink.custom.use.cloud.service.cloud.QueryConfigurationCloudService;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchaseQuotationItemSupplierChekBoxValue
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/8/5 11:30
 * @Version 1.0
 **/
public class PurchaseQuotationItemSupplierChekBoxValue extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("viewlet"),
                new Parameter("ExtendParam"),
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"value"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        QueryConfigurationCloudService queryConfigurationCloudService = dataServiceFactory.getDataService(QueryConfigurationCloudService.class);
        String viewlet = param.get("viewlet");
        String extendParam = param.get("ExtendParam");
        String projectId = param.get("projectId");
        String templateId = null;
        if ( "purchase/purchaseClinchDealPriceDataSource.cpt".equals(viewlet) ) {
            templateId = "T-CGXM-CJDJ-CLLB-20190701930100127";
        } else {
            templateId = "T-CGXM-CKBJ-CGLB-20190704993100130";
        }
        ResponseResult responseResult = queryConfigurationCloudService.queryCustomConf(UserContext.getUserId(), UserContext.getCompanyId(), templateId, projectId + "," +  UserContext.getCompanyId() + ",true");
        Boolean success = responseResult.getSuccess();
        if (!success) {
            throw new RuntimeException("查询动态列配置失败");
        }
        Object resultData = responseResult.getData();
        JSONObject jsonObject = JSON.parseObject(resultData.toString());
        Map typeData = (Map) jsonObject.get("typeData");
        List<String> list = new ArrayList();
        list.add("规格型号");
        list.add("数量");
        list.add("计量单位");
        if (typeData.size() > 0) {
            if ( typeData.containsKey("marketPrice") ) {
                list.add("市场参考价");
            } else  {
                list.add("计划单价");
            }
        }
        return list;
    }

}
