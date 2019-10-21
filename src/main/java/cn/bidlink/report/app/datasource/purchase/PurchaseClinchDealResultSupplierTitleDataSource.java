
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
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
 * @description:采购项目：成交结果采购品-供应商维度 对应供应商表头
 * @Date 2019/9/25
 *
 */
public class PurchaseClinchDealResultSupplierTitleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("showNoDeal")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierId", "key", "title", "dealStatus", "dealStatusStr"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        List<QuoteSeparatelyVo> quoteSeparatelyVos = purchaseProxyService.priceSupplierTitle(Long.valueOf(param.get("projectId")),
                UserContext.getCompanyId(), 3, false,Boolean.valueOf(param.get("showNoDeal")));
        return quoteSeparatelyVos;
    }

}
