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
 * @date 2019-11-05 16:01
 */
public class ArchivedAuctionUnProjectSaveDataSource extends AbstractBaseTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("bidPrice", 488);
        resultMap.put("avgPrice", 328);
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
        return new String[]{"bidPrice", "avgPrice"};
    }
}
