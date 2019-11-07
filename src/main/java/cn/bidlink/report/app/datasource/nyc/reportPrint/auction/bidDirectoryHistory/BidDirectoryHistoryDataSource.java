package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.bidDirectoryHistory;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuqi
 * @date 2019-11-05 16:05
 */
public class BidDirectoryHistoryDataSource extends AbstractBaseTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("directory_name", "炉石传说");
        resultMap.put("directory_id", 21321);
        resultMap.put("project_name", "c325545111211");
        resultMap.put("project_id", 488);
        resultMap.put("update_time", "2019-11-05 16:05");
        resultMap.put("final_price", 21321);
        resultMap.put("real_price", 328);
        resultMap.put("unit_name", "人民币");
        resultMap.put("supplier_id", 202121212);
        resultMap.put("supplier_name", "人民币");
        resultMap.put("plan_amount", 20);
        resultMap.put("RMB", "人民币");
        resultList.add(resultMap);
        return resultList;
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
