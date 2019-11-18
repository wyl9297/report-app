package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectOverviewStatistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectOverviewStatisticsService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectOverviewStatisticsCDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProjectOverviewStatisticsCDataSource.class);

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectOverviewStatisticsService dataService = dataServiceFactory.getDataService(DubboProjectOverviewStatisticsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        String code = String.valueOf(param.get("selectDepName"));
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, companyId, startTime, endTime);
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectOverviewStatisticsC(companyId, code, startTime, endTime);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return  result;

    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("compId"),new Parameter("begin"),new Parameter("end"),
                new Parameter("contrastBegin"),new Parameter("contrastEnd"),new Parameter("selectDepName")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "name" ,"id","totalPrice","supplierType"};
    }
}
