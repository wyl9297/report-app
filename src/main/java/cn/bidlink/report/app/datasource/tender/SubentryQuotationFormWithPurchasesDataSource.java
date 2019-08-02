package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SubentryQuotationFormWithPurchasesDataSource
 * @Author 从尧
 * @Description 分项报价表[采购品] 程序集
 * @Date 2019-06-26 14:43
 * @Version 1.0
 **/
public class SubentryQuotationFormWithPurchasesDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "name", "code" , "techParameters", "purchaseAmount", "spec" , "needTime", "createTimeStr", "appliedPersonAndPhone" , "unitName",
                "marketPrice", "totalPlanPrice" , "appliedEnterprise", "purpose", "needTimeStr" , "comment", "createTime" , "useDept" , "directoryId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        BidSupplierItemRestService bidSupplierItemRestService = dataServiceFactory.getDataService(BidSupplierItemRestService.class);
        //获取报表查询的参数
        String subProjectId = param.get("projectId");
        //查询数据并返回
        Map<String, Object> supplierBidItemList = bidSupplierItemRestService.getSupplierBidItemList(Long.valueOf(subProjectId));
        List<Map<String , Object>> tableData = (List<Map<String, Object>>) supplierBidItemList.get("tableData");
        return tableData;
    }
}
