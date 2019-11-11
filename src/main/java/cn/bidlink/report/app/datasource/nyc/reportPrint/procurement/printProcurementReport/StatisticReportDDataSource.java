package cn.bidlink.report.app.datasource.nyc.reportPrint.procurement.printProcurementReport;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class StatisticReportDDataSource extends AbstractBaseTableData {

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List insert = InsertParam.insert(this.getColumn());
        return insert;
    }

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
}
