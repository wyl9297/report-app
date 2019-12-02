package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.notPackageAuctionSupplierList;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboNotPackageAuctionSupplierListService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class NotPackageAuctionSupplierListDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(NotPackageAuctionSupplierListDataSource.class);

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

        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.notPackageAuctionSupplierList(projectId, companyId, directoryName);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboNotPackageAuctionSupplierListService.notPackageAuctionSupplierList",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        } else{
            log.error("{}数据源所需必要参数不全", log.getName());
            return null;
        }
    }


}
