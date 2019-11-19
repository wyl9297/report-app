package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectTotalAmountTrendNewBps;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:43
 * @description :cn.bidlink.nyc.report.statistics.report.totalAmountTrendsStatistics.ProjectTotalAmountTrendBpsByMonth
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectTotalAmountTrendNewBpsService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
    private static Logger log = LoggerFactory.getLogger(ProjectTotalAmountTrendBpsByMonthDataSource.class);

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
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "companyId", "startTime", "endTime");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectTotalAmountTrendBpsByMonth(companyId, startTime, endTime);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }
}