package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.notPackageAuctionSupplierList;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboNotPackageAuctionSupplierListService;
import com.fr.base.Parameter;

import java.util.*;

public class NotPackageAuctionSupplierListDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        Parameter[] parameter = {
                new Parameter("companyId"),
                new Parameter("projectId"),
                new Parameter("directoryName")};
        return parameter;
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"bid_id", "project_id", "supplier_id", "supplier_name", "directory_name", "directory_id", "directory_code", "first_quote_price", "real_price", "jiezi", "bid_status", "divide_rate", "divide_bid_mark",
                "spec", "tech_parameters", "unit_name", "plan_amount", "link_man", "RMB"};
        return column;
    }


    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboNotPackageAuctionSupplierListService dataService = dataServiceFactory.getDataService(DubboNotPackageAuctionSupplierListService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String directoryName = String.valueOf(param.get("directoryName"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.notPackageAuctionSupplierList(projectId, companyId, directoryName);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }


}
