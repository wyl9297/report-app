package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.framework.common.entity.ResponseObj;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionPricingProjectDataSource
 * @Author ylw
 * @Description //成交定价项目数据源
 * @Date 2019/5/27 9:51
 * @Version 1.0
 **/
public class TransactionPricingProjectDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"dealTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DealPriceSupplierDimensionRestService dealPriceSupplierDimensionRestService
                = dataServiceFactory.getDataService(DealPriceSupplierDimensionRestService.class);
        ResponseObj responseObj
                = dealPriceSupplierDimensionRestService.findPreDealSupplierItemList(Long.valueOf(param.get("projectId")), UserContext.getCompanyId());
        Map responseObjData = (Map)responseObj.getData();
        String dealTotalPrice = (String) responseObjData.get("dealTotalPrice");
        List list = new ArrayList();
        list.add(dealTotalPrice);
        return list;
    }

}
