package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectProgressStatisticsBps;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectProgressStatisticsBpsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ProjectProgressStatisticsBpsDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectProgressStatisticsBpsService dataService = dataServiceFactory.getDataService(DubboProjectProgressStatisticsBpsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.projectProgressStatisticsBps(companyId, startTime, endTime);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return  result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("compId"),new Parameter("begin"),new Parameter("end")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "name" ,"priject_num"};
    }
}
