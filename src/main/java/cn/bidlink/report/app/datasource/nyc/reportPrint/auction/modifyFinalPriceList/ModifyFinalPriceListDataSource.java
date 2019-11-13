package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.modifyFinalPriceList;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboModifyFinalPriceListService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ModifyFinalPriceListDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"directory_name", "directory_code", "spec", "tech_prameters", "unit_name", "plan_amount", "RMB"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboModifyFinalPriceListService dataService = dataServiceFactory.getDataService(DubboModifyFinalPriceListService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.modifyFinalPriceList(projectId, companyId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

}
