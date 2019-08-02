package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.purchase.PurchasesWithSupplierItemVO;
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
public class PurchaseDimensionOfTransactionResult extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"marketPrice", "code", "unitName", "purpose", "useDept", "unitName", "spec", "purchaseAmount", "techParameters",
                "name", "comment", "planPrice", "projectId", "appliedEnterprise", "appliedPersonAndPhone", "subTaxPriceExcluded", "subLabourCost", "dealUnitPrice",
                "subCombinedPrice", "subTax", "dealRation", "quoteAmount", "subManagementCost", "planPrice", "dealTotalPrice", "supplierName", "dealAmount",
                "quoteUnitPrice", "subProfit", "quoteTotalPrice", "subMaterialCost", "subMachineryCost", "haveBargain", "subTaxPrice", "projectItemId", "projectId",
                "supplierId" , "dealDescription" , "needTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService dealPriceRestService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        List<PurchasesWithSupplierItemVO> purchaseDealItem = dealPriceRestService.findPurchaseDealItem(Long.valueOf(projectId), Long.valueOf(companyId));
        return purchaseDealItem;
    }
}
