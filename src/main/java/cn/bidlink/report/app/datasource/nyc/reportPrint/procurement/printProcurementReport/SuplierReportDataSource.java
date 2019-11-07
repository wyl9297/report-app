package cn.bidlink.report.app.datasource.nyc.reportPrint.procurement.printProcurementReport;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class SuplierReportDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("directoryId"),
                new Parameter("catalogId"),
                new Parameter("updateTimeBegin"),
                new Parameter("updateTimeEnd"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = { "supplierId" ,"suppelierName","supplierLevel","tradeEvaluation","amount","totalprice"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List insert = InsertParam.insert(this.getColumn());
        return insert;
    }
}
