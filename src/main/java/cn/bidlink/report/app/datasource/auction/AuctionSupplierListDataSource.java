package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.service.AuctionQuotedPriceRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionSupplierListDataSource
 * @Author 从尧
 * @Description 竞价项目 供应商列表 程序集
 * @Date 2019-06-03 10:34
 * @Version 1.0
 **/
public class AuctionSupplierListDataSource extends AbstractBaseTableData {
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
        return new String[]{"projectId", "supplierId", "supplierName","haveQuoteProjectItemCount" , "quoteProjectItemCount","supplierType", "inviteFlag", "supplierSource", "quoteTime", "quoteTotalPrice", "linkMan", "linkPhone", "quoteResult", "append"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        AuctionQuotedPriceRestService auctionQuotedPriceRestService = dataServiceFactory.getDataService(AuctionQuotedPriceRestService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
//        Boolean quoteResult = Boolean.valueOf(param.get("quoteResult"));

        // quoteResult 默认设置为true
        Map<String, Object> quotedSupplierList = auctionQuotedPriceRestService.findQuotedSupplierList(projectId, companyId, true, 1, 1000);
        List<Map<String, Object>> tableData = (List<Map<String, Object>>) quotedSupplierList.get("tableData");
        return tableData;
    }
}
