package cn.bidlink.report.app.datasource.nyc.reportPrint.auction;

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
        resultMap.put("directoryName", "炉石传说");
        resultMap.put("directoryId", 21321);
        resultMap.put("projectName", "c325545111211");
        resultMap.put("projectId", 488);
        resultMap.put("supplierName", "暴雪");
        resultMap.put("updateTime", "2019-11-05 16:05");
        resultMap.put("finalPrice", 21321);
        resultMap.put("realPrice", 328);
        resultMap.put("unitName", "人民币");
        resultMap.put("supplierId", 202121212);
        resultMap.put("supplierName", "人民币");
        resultMap.put("planAmount", 20);
        resultMap.put("currency", "人民币");
        resultList.add(resultMap);
        return resultList;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("directoryId"),
                new Parameter("companyId"),
                new Parameter("name"),
                new Parameter("startTime"),
                new Parameter("endTime"),
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"directoryId", "directoryName", "projectName", "projectId", "updateTime", "finalPrice", "realPrice",
        "unitName", "supplierId", "supplierName", "planAmount", "currency"};
    }
}
