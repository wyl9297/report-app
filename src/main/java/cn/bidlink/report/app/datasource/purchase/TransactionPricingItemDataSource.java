package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.framework.common.entity.ResponseObj;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionPricingItemDataSource
 * @Author ylw
 * @Description //成交定价采购品数据源
 * @Date 2019/5/27 9:52
 * @Version 1.0
 **/
public class TransactionPricingItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code" , "name" , "spec" , "techParameters" , "unitName" , "comment" , "purpose" , "dealUnitPrice" , "useDept" ,  "priceSaving" , "dealRation" , "needTime" , "dealDescription" ,
                "planPrice" , "appliedEnterprise" , "appliedPersonAndPhone" , "dealTotalPrice" , "dealAmount" , "quoteUnitPrice" , "priceSavingRatio" , "purchaseAmount" , "quoteTotalPrice" , "supplierId" ,
                "projectItemId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DealPriceSupplierDimensionRestService dealPriceSupplierDimensionRestService
                = dataServiceFactory.getDataService(DealPriceSupplierDimensionRestService.class);
        ResponseObj responseObj1
                = dealPriceSupplierDimensionRestService.findPreDealSupplierItemList(Long.valueOf(param.get("projectId")), UserContext.getCompanyId());
        Map responseObjData1 = (Map)responseObj1.getData();
        List projectItemList = (List)responseObjData1.get("projectItemList");
        return projectItemList;
    }

}
