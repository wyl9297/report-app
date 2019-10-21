
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.purchase.cloud.vo.DealItemSupplierVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchaseQuotationsPurDealTotPriceDataSource
 * @Author 从尧
 * @Description 采购项目 报价一览表 采购品-供应商维度
 * @Date 2019-07-10 11:29
 * @Version 1.0
 **/
public class PurchaseQuotationsPurDealTotPriceDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("showNoDeal")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"dealTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        DealItemSupplierVo supplierVo = purchaseProxyService.quotationPricing(Long.valueOf(param.get("projectId")),
                UserContext.getCompanyId(), UserContext.getUserId(), 1, true, false);
        List<DealItemSupplierVo> tableData = new ArrayList<>();
        BigDecimal dealTotalPrice = supplierVo.getDealTotalPrice();
        DealItemSupplierVo dealItemSupplierVo = new DealItemSupplierVo();
        dealItemSupplierVo.setDealTotalPrice(dealTotalPrice);
        tableData.add(dealItemSupplierVo);
        return tableData;

    }

}
