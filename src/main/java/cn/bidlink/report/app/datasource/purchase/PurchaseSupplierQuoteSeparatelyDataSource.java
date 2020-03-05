
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.report.app.model.vo.purchase.QuoteSeparatelyVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目->报价一览表->采购品维度->分项报价项数据集
 * @Date 2019/5/22
 */

public class PurchaseSupplierQuoteSeparatelyDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("projectId"),
                new Parameter("quoteResult"),
                new Parameter("pageNum"),
                new Parameter("pageSize")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"key", "value", "projectItemId", "supplierId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        List<QuoteSeparatelyVo> itemSupplierQuoteInfoWithTableDate =
                purchaseProxyService.findItemSupplierQuoteInfoWithTableDate(UserContext.getCompanyId(), Long.valueOf(Long.valueOf(param.get("projectId"))), Boolean.getBoolean(param.get("quoteResult")),null, null);
        return itemSupplierQuoteInfoWithTableDate;
    }

}
