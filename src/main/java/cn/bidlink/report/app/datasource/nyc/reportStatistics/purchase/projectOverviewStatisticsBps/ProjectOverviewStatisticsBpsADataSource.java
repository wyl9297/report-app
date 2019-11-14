package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectOverviewStatisticsBps;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectOverviewStatisticsBpsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ProjectOverviewStatisticsBpsADataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectOverviewStatisticsBpsService dataService = dataServiceFactory.getDataService(DubboProjectOverviewStatisticsBpsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        String code = String.valueOf(param.get("selectDepName"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectOverviewStatisticsBpsA(companyId, code, startTime, endTime);
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
