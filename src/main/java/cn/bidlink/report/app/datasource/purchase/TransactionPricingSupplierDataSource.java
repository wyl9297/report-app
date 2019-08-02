package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.common.entity.ResponseObj;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionPricingSupplierDataSource
 * @Author ylw
 * @Description //成交结果供应商数据源
 * @Date 2019/5/27 9:52
 * @Version 1.0
 **/
public class TransactionPricingSupplierDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName" ,"quoteTime","quoteTotalPrice", "preDealTotalPrice", "quoteTotalTitle","quoteTotalItem","supplierId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DealPriceSupplierDimensionRestService dealPriceSupplierDimensionRestService
                = dataServiceFactory.getDataService(DealPriceSupplierDimensionRestService.class);
        ResponseObj responseObj1
                = dealPriceSupplierDimensionRestService.findPreDealSupplierItemList(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        Map responseObjData1 = (Map)responseObj1.getData();
        List supplierList = (List)responseObjData1.get("supplierList");
        return supplierList;
    }

}
