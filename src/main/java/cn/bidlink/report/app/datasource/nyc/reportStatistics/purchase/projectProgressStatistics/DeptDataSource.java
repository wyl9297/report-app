package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectProgressStatistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectProgressStatisticsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class DeptDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectProgressStatisticsService dataService = dataServiceFactory.getDataService(DubboProjectProgressStatisticsService.class);
        String companyId = String.valueOf(param.get("compId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.overviewStatisticsDept(companyId);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;

    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] {new Parameter("compId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id","code","name","parentId"};
    }
}
