package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectOverviewStatisticsBps;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectOverviewStatisticsBpsService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectOverviewStatisticsBpsDDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProjectOverviewStatisticsBpsDDataSource.class);

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectOverviewStatisticsBpsService dataService = dataServiceFactory.getDataService(DubboProjectOverviewStatisticsBpsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("contrastBegin"));
        String endTime = String.valueOf(param.get("contrastEnd"));
        String code = String.valueOf(param.get("selectDepName"));
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "compId", "contrastBegin", "contrastEnd");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectOverviewStatisticsBpsD(companyId, code, startTime, endTime);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("compId"),new Parameter("begin"),new Parameter("end"),
                new Parameter("contrastBegin"),new Parameter("contrastEnd"),new Parameter("selectDepName")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "name","id","totalSum","bidsum","supplierType"};
    }
}
