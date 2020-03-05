
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
 * @ClassName PurchaseQuotationsPurProductDataSource
 * @Author 从尧
 * @Description 采购项目 报价一览表 采购品-供应商维度
 * @Date 2019-07-10 11:29
 * @Version 1.0
 **/
public class PurchaseQuotationsPurProductDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("showNoDeal")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"projectItemId" ,"directoryId" , "name", "spec", "purchaseAmount","unitName", "marketPrice", "planPrice" , "techParameters"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        DealItemSupplierVo itemSupplierVoList = purchaseProxyService.quotationPricing(Long.valueOf(param.get("projectId")),
                UserContext.getCompanyId(), UserContext.getUserId(), 1, true, false);
        List<Map<String, Object>> tableData = itemSupplierVoList.getTableData();
        List<ClinchDealVo> tableDatanull = new ArrayList<>();
        if (null != tableData && tableData.size()>0){
            return tableData;
        }else {
            return tableDatanull;
        }


    }

}
