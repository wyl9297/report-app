
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
 * @description:采购项目：成交定价采购品-供应商维度 每个供应商对应成交总计
 * @Date 2019/7/11
 *
 */
public class PurchaseClinchDealPricePurColTitleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("showNoDeal")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"key", "title"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        List<QuoteSeparatelyVo> colSpanColumnsTitle = purchaseProxyService.getColSpanColumnsTitle(Long.valueOf(param.get("projectId")),
                UserContext.getCompanyId(), UserContext.getUserId(), 2, false,Boolean.valueOf(param.get("showNoDeal")));
        return colSpanColumnsTitle;

    }

}
