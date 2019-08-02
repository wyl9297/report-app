package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ListOfQuotationsDataSource
 * @Author 从尧
 * @Description 报价一览表(供应商维度) 表单报表[报价供应商列表] 程序集
 * @Date 2019-05-23 10:49
 * @Version 1.0
 **/
public class ListOfQuotationsDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"index", "id", "projectId","businessId", "supplierId", "supplierName","supplierType", "isAppend", "inviteFlag","supplierSource",
                "quoteStatus", "quoteStatusFlag","inviteTime", "isDownloadPurchaseFile", "isUploadQuoteFile","quoteFileDownLoadUrl", "quoteProjectItemCount",
                "haveQuoteProjectItemCount","quoteItemCount", "quoteTime","quoteTotalPrice", "savingTotalPrice", "savingTotalRatio","linkMan",
                "linkPhone", "quoteResult","itemQuoteList", "colKey", "colName" ,"realQuoteStopTime" , "supplierQuotedInfo" , "supplierListCode"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        //查询数据并返回
        List<Map<String, Object>> supplierQuoteData = purchaseProxyService.getSupplierQuoteData(Long.valueOf(projectId), Long.valueOf(companyId));
        return supplierQuoteData;
    }
}
