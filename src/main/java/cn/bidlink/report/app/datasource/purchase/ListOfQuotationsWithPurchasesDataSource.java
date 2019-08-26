package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ListOfQuotationsDataSource
 * @Author 从尧
 * @Description 报价一览表(供应商维度) 表单报表[报价供应商对应采购品列表] 程序集
 * @Date 2019-05-23 10:49
 * @Version 1.0
 **/
public class ListOfQuotationsWithPurchasesDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName", "subTaxPriceExcluded", "code","purpose", "subLabourCost", "subCombinedPrice","useDept", "spec", "subTax","techParameters",
                "subManagementCost", "needTime","planPrice", "appliedEnterprise", "appliedPersonAndPhone","unitName", "index","quoteUnitPrice","subProfit", "purchaseAmount",
                "quoteTotalPrice", "subMaterialCost", "subMachineryCost","name","comment", "subTaxPrice" , "supplierListCode" , "projectItemId" , "supplierId", "currency"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        //查询数据并返回
        List<Map<String, Object>> result = purchaseProxyService.getSupplierItemList(Long.valueOf(projectId), Long.valueOf(companyId));
        return result;
    }
}
