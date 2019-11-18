package cn.bidlink.report.app.datasource.nyc.reportPrint.procurement.printProcurementReport;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchases.DubboPrintProcurementReportService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class StatisticReportDDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] {
                new Parameter("directoryId"),
                new Parameter("catalogId"),
                new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        String[] column = { "supplierLevel" ,"leveCount"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboPrintProcurementReportService printProcurementReportService = dataServiceFactory.getDataService(DubboPrintProcurementReportService.class);
        String directoryId = param.get("directoryId");
        String catalogId = param.get("catalogId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, companyId);

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = printProcurementReportService.statisticReportD(directoryId, catalogId, companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        }
        return null;
    }
}
