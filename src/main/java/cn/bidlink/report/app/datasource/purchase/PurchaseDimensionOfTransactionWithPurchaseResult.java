package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchaseDimensionOfTransactionResult
 * @Author 从尧
 * @Description 成交结果采购品维度
 * @Date 2019-06-25 16:25
 * @Version 1.0
 **/
public class PurchaseDimensionOfTransactionWithPurchaseResult extends AbstractBaseTableData {
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
        return new String[]{"marketPrice", "code", "unitName", "purpose", "useDept", "unitName", "spec", "purchaseAmount", "techParameters",
                "name", "comment", "planPrice", "projectId", "appliedEnterprise", "appliedPersonAndPhone", "needTime" , "directoryId" , "supplierId" , "projectItemId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService dealPriceRestService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        Boolean showFlag = Boolean.valueOf(param.get("showFlag"));
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        List<Map<String, Object>> purchaseDealItemWithPurchase = dealPriceRestService.findPurchaseDealItemWithPurchase(Long.valueOf(projectId), Long.valueOf(companyId),showFlag);
        return purchaseDealItemWithPurchase;
    }
}
