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
 * @date 2019-11-05 15:49
 */
public class ArchivedAuctionUnProjectDataSource extends AbstractBaseTableData {

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("directoryName", "炉石传说");
        resultMap.put("directoryCode", "c325545111211");
        resultMap.put("planAmount", 488);
        resultMap.put("supplierName", "暴雪");
        resultMap.put("linkMan", "酒馆老板");
        resultMap.put("currency", "人民币");
        resultMap.put("realPrice", 328);
        resultMap.put("divideRate", 10);
        resultMap.put("amount", 20);
        resultList.add(resultMap);
        return resultList;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"directoryName", "directoryCode", "planAmount", "supplierName", "linkMan", "currency",
        "realPrice", "divideRate", "amount"};
    }
}
