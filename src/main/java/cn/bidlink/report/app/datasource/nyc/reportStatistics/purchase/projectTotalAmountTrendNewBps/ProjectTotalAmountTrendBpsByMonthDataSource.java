package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectTotalAmountTrendNewBps;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:43
 * @description :cn.bidlink.nyc.report.statistics.report.totalAmountTrendsStatistics.ProjectTotalAmountTrendBpsByMonth
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectTotalAmountTrendNewBpsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProjectTotalAmountTrendBpsByMonthDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/7 14:43
 * @Version 1.0
 **/
public class ProjectTotalAmountTrendBpsByMonthDataSource extends AbstractColumnPositionTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("begin"),
                new Parameter("end")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "month" ,"groupType","total","num"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectTotalAmountTrendNewBpsService dataService = dataServiceFactory.getDataService(DubboProjectTotalAmountTrendNewBpsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectTotalAmountTrendBpsByMonth(companyId, startTime, endTime);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}