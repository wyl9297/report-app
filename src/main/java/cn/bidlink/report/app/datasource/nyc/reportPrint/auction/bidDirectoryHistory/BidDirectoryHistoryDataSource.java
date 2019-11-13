package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.bidDirectoryHistory;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboBidDirectoryHistoryService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author liuqi
 * @date 2019-11-05 16:05
 */
public class BidDirectoryHistoryDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboBidDirectoryHistoryService dataService = dataServiceFactory.getDataService(DubboBidDirectoryHistoryService.class);
        //获取报表查询的参数
        String directoryId = String.valueOf(param.get("directoryId"));
        String projectOrSupplierName = String.valueOf(param.get("projectOrSupplierName"));
        String startTime = String.valueOf(param.get("startTime"));
        String endTime = String.valueOf(param.get("endTime"));
        String compId = String.valueOf(param.get("compId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.bidDirectoryHistory(directoryId, compId, projectOrSupplierName, startTime, endTime);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] {
                new Parameter("directoryId"),
                new Parameter("projectOrSupplierName"),
                new Parameter("startTime"),
                new Parameter("endTime"),
                new Parameter("compId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"directory_id" ,"directory_name","project_name","project_id","update_time","final_price",
                "real_price","unit_name","supplier_id","supplier_name","plan_amount","RMB"};
    }
}
