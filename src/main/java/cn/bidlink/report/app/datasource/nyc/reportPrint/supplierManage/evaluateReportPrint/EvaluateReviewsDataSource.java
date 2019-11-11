package cn.bidlink.report.app.datasource.nyc.reportPrint.supplierManage.evaluateReportPrint;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.suppliermanage.DubboEvaluateReportPrintService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class EvaluateReviewsDataSource extends AbstractBaseTableData {

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
        return new String[]{"supplier_id","company_name","report_supplier_level","supplier_status","supplier_trade_evaluation",
                "response_score","price_score","delivery_score","quality_score","evaluation_num"};
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
        ServiceResult<List<Map<String, Object>>> listServiceResult = evaluateReportPrintService.evaluateReviews(catalogId, reportId, companyId, beginTime, endTime, chooseType);
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
