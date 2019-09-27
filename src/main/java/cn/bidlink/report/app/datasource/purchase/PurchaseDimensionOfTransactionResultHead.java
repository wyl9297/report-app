package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.service.DealPriceRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchaseDimensionOfTransactionResultHead
 * @Author 从尧
 * @Description 成交结果采购品维度[项目头信息]
 * @Date 2019-06-25 15:36
 * @Version 1.0
 **/
public class PurchaseDimensionOfTransactionResultHead extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("showFlag")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"pName", "pCode", "pCreateUserName" , "dealTotalPrice" ,"pCreateTime", "planPriceTitle", "applyReason"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        Boolean showFlag = Boolean.valueOf(param.get("showFlag"));
        DealPriceRestService dealPriceRestService = dataServiceFactory.getDataService(DealPriceRestService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        Map<String, Object> purchaseDealItem = dealPriceRestService.findPurchaseDealItem(Long.valueOf(projectId), Long.valueOf(companyId),showFlag);
        List list = new ArrayList();
        list.add(purchaseDealItem);
        return list;
    }
}
