
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.purchase.QuoteSeparatelyVo;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：成交定价采购品-供应商维度 动态分项报价value
 * @Date 2019/7/11
 *
 */
public class PurchaseClinchDealPricePurQuteValueDataSource extends AbstractBaseTableData {

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
        return new String[]{"projectItemId", "key", "value", "supplierId", "supplierProjectItemId", "directoryId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        List<QuoteSeparatelyVo> quoteSeparatelyVos = purchaseProxyService.purQuoteValue(Long.valueOf(param.get("projectId")),
                Long.valueOf(param.get("companyId")), Long.valueOf(param.get("userId")), 2, false);

        return quoteSeparatelyVos;
    }

}
