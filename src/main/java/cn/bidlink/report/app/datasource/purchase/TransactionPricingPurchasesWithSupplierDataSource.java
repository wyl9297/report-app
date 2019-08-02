package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.purchase.ProjectSupplierDealVo;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionPricingPurchasesDimDataSource
 * @Author 从尧
 * @Description 采购项目 成交定价 采购品维度
 * @Date 2019-07-10 11:29
 * @Version 1.0
 **/
public class TransactionPricingPurchasesWithSupplierDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("showUnConfirmed")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName", "quoteUnitPrice", "quoteTotalPrice", "dealAmount", "dealRation", "dealTotalPrice", "projectItemId",
                "dealUnitPrice", "dealDescription", "supplierId", "dealTotalPriceWithProject"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //fixme 以下是demo示例 老司机请直接删除
        //获取服务
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        String showUnConfirmed = param.get("showUnConfirmed");
        List<ProjectSupplierDealVo> processedDealList = purchaseProxyService.findProcessedDealList(Long.parseLong(projectId), Long.parseLong(companyId), Boolean.parseBoolean(showUnConfirmed));
        return processedDealList;
    }
}
