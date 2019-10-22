
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.purchase.cloud.vo.DealItemSupplierVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.purchase.ClinchDealVo;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：成交定价采购品-供应商维度 采购品数据集
 * @Date 2019/7/11
 *
 */
public class PurchaseClinchDealPricePurProductDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("showNoDeal")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"projectItemId" ,"directoryId" , "name", "spec", "purchaseAmount","unitName", "marketPrice", "planPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        DealItemSupplierVo itemSupplierVoList = purchaseProxyService.quotationPricing(Long.valueOf(param.get("projectId")),
                UserContext.getCompanyId(), UserContext.getUserId(), 2, false,Boolean.valueOf(param.get("showFlag")));
        List<Map<String, Object>> tableData = itemSupplierVoList.getTableData();

        List<ClinchDealVo> tableDatanull = new ArrayList<>();
        if (null != tableData && tableData.size()>0){
            return tableData;
        }else {
            return tableDatanull;
        }

    }

}
