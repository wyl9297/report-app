package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectOverviewStatistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectOverviewStatisticsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ProjectOverviewStatisticsADataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectOverviewStatisticsService dataService = dataServiceFactory.getDataService(DubboProjectOverviewStatisticsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        String code = String.valueOf(param.get("selectDepName"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectOverviewStatisticsA(companyId, code, startTime, endTime);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return  result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("compId"),new Parameter("begin"),new Parameter("end"),
                new Parameter("contrastBegin"),new Parameter("contrastEnd"),new Parameter("selectDepName")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "archived" ,"newly","deal_sum","deal_num"};
    }
}
