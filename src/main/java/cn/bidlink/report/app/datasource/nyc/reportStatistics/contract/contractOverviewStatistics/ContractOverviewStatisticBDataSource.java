package cn.bidlink.report.app.datasource.nyc.reportStatistics.contract.contractOverviewStatistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboContractOverviewStatisticsService;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractOverviewStatisticBDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("contrastBegin"),
                new Parameter("contrastEnd")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"totalNum" ,"total_price","validNum","validPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboContractOverviewStatisticsService dataService = dataServiceFactory.getDataService(DubboContractOverviewStatisticsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("contrastBegin"));
        String endTime = String.valueOf(param.get("contrastEnd"));
        if (startTime != null && !"".equals(startTime)) {
            startTime = startTime.concat(" 00:00:00");
        }
        if (endTime != null && !"".equals(endTime)) {
            endTime = endTime.concat(" 23:59:59");
        }
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.contractOverviewStatisticB(companyId, startTime, endTime);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}
