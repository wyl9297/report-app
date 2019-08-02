package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.service.QuotedPriceRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchasePriceWithSupplierDataSourceTemplate
 * @Author 从尧
 * @Description 报价一览表 程序集
 * @Date 2019-05-21 13:53
 * @Version 1.0
 **/
public class PurchasePriceWithSupplierDataSourceTemplate extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("quoteResult"),
                new Parameter("pageNum"),
                new Parameter("pageSize")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "supplierName", "quoteItemCount", "quoteResult", "linkMan", "linkPhone", "inviteFlag", "supplierSource", "quoteTime", "isUploadQuoteFile", "quoteTotalPrice",
                "isAppend", "quoteStatus", "quoteStatusFlag", "isDownloadPurchaseFile", "isUploadQuoteFile", "quoteFileDownLoadUrl", "inviteTime", "savingTotalPrice",
                "savingTotalRatio", "isQuote"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        QuotedPriceRestService quotedPriceRestService = dataServiceFactory.getDataService(QuotedPriceRestService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        String quoteResult = param.get("quoteResult");
        String pageNum = param.get("pageNum");
        String pageSize = param.get("pageSize");
        //查询数据并返回
        Map<String, Object> quotedSupplierList = quotedPriceRestService.findQuotedSupplierList(Long.valueOf(projectId), Long.valueOf(companyId), Boolean.valueOf(quoteResult), "".equals(pageNum) ? 0 : Integer.valueOf(pageNum), "".equals(pageSize) ? 0 : Integer.valueOf(pageSize));
        List<Map<String, Object>> tableData = (List<Map<String, Object>>) quotedSupplierList.get("tableData");
        return tableData;
    }
}
