package cn.bidlink.report.app.datasource.nyc.reportPrint.procurement.printProcurementReport;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchases.DubboPrintProcurementReportService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class StatisticReportBDataSource extends AbstractBaseTableData {

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
        String[] column = { "month" ,"monthprice"};
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
        ServiceResult<List<Map<String, Object>>> listServiceResult = printProcurementReportService.statisticReportB(directoryId, catalogId, updateTimeBegin, updateTimeEnd, companyId);
        return listServiceResult.getResult();

//        List insert = InsertParam.insert(this.getColumn());
//        return insert;
    }
}
