package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionResultItemDataSource
 * @Author ylw
 * @Description 成交结果采购品数据源
 * @Date 2019/5/22 17:15
 * @Version 1.0
 **/
public class TransactionResultItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code" , "name" , "spec" , "techParameters" , "unitName" , "comment" , "purpose" , "dealUnitPrice" , "useDept" ,  "priceSaving" , "dealRation" , "needTime" , "dealDescription" ,
                "planPrice" , "appliedEnterprise" , "appliedPersonAndPhone" , "dealTotalPrice" , "dealAmount" , "quoteUnitPrice" , "priceSavingRatio" , "purchaseAmount" , "quoteTotalPrice" ,
                "supplierListCode" , "supplierId" , "projectItemId", "currency"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        List<Map> transactionResult = purchaseProxyService.findTransactionResult(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        return transactionResult;
    }

}
