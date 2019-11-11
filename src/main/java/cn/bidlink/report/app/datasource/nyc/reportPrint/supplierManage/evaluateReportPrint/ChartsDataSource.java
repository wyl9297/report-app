package cn.bidlink.report.app.datasource.nyc.reportPrint.supplierManage.evaluateReportPrint;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.suppliermanage.DubboEvaluateReportPrintService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ChartsDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("beginTime"),
                new Parameter("endTime"),
                new Parameter("reportId"),
                new Parameter("chooseType"),
                new Parameter("catalogId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id","supplier_id","company_name","report_supplier_level","supplier_status",
                "corp_status","supplier_trade_evaluation","priceNum","orderNum","evaluationNum","totalMoney","rate","isCore","supplier_status_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboEvaluateReportPrintService evaluateReportPrintService = dataServiceFactory.getDataService(DubboEvaluateReportPrintService.class);
        String beginTime = param.get("beginTime");
        String endTime = param.get("endTime");
        String reportId = param.get("reportId");
        String chooseType = param.get("chooseType");
        String catalogId = param.get("catalogId");
        String companyId = param.get("companyId");
        ServiceResult<List<Map<String, Object>>> listServiceResult = evaluateReportPrintService.charts(beginTime, endTime, reportId, chooseType, catalogId, companyId);
        return listServiceResult.getResult();


//        List<Map<String, Object>> resultList = new ArrayList<>();
//        Map<String, Object> resultMap = new HashMap<>();
//
//        for (String s : getColumn()) {
//            resultMap.put(s,"77777");
//        }
//        resultList.add(resultMap);
//
//        return resultList;
    }

}
