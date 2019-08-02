
package cn.bidlink.report.app.datasource.purchase;

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
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：成交定价采购品-供应商维度 所有供应商的成交合计dealTotalPrice
 * @Date 2019/7/11
 *
 */
public class PurchaseQuotationsPurDealTotPriceDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("userId"),
                new Parameter("handStatus"),
                new Parameter("viewFlag"),
                new Parameter("supplierIds"),
                new Parameter("exportFlag")
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
                Long.valueOf(param.get("companyId")), Long.valueOf(param.get("userId")), 1, true);
        List<DealItemSupplierVo> tableData = new ArrayList<>();
        BigDecimal dealTotalPrice = supplierVo.getDealTotalPrice();
        DealItemSupplierVo dealItemSupplierVo = new DealItemSupplierVo();
        dealItemSupplierVo.setDealTotalPrice(dealTotalPrice);
        tableData.add(dealItemSupplierVo);
        return tableData;

    }

}
