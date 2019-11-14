package cn.bidlink.report.app.datasource.nyc.reportPrint.procurement.printProcurementReport;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchases.DubboPrintProcurementReportService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class StatisticReportCDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] {
                new Parameter("directoryId"),
                new Parameter("catalogId"),
                new Parameter("updateTimeBegin"),
                new Parameter("updateTimeEnd"),
                new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        String[] column = { "sacount" ,"scount","dealcount","totalRangeCount","dealRangeCount"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboPrintProcurementReportService printProcurementReportService = dataServiceFactory.getDataService(DubboPrintProcurementReportService.class);
        String directoryId = param.get("directoryId");
        String catalogId = param.get("catalogId");
        String updateTimeBegin = param.get("updateTimeBegin");
        String updateTimeEnd = param.get("updateTimeEnd");
        String companyId = param.get("companyId");

        ServiceResult<List<Map<String, Object>>> listServiceResult = printProcurementReportService.statisticReportC(directoryId, catalogId, updateTimeBegin, updateTimeEnd, companyId);

        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        if ( result == null || result.size() == 0){
            return null;
        }
        return listServiceResult.getResult();
    }
}
