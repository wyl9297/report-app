
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.model.vo.purchase.TotalItemsSeparatelyVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购品信息（含报价方案信息）中 方案名称数据集
 * @Date 2019/5/23
 *
 */
public class PurchaseTotalItemsNameDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        List<TotalItemsSeparatelyVo> printingSettingItem = purchaseProxyService.findPrintingSettingItem(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        return printingSettingItem;
    }

}
