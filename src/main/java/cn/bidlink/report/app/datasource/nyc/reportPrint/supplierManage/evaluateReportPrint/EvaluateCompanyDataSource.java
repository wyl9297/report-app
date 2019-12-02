package cn.bidlink.report.app.datasource.nyc.reportPrint.supplierManage.evaluateReportPrint;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.suppliermanage.DubboEvaluateReportPrintService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvaluateCompanyDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(EvaluateCompanyDataSource.class);

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
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "reportId", "catalogId", "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = evaluateReportPrintService.evaluateCompany(catalogId, reportId, companyId, beginTime, endTime, chooseType);

            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
            if ( result == null || result.size() == 0){
                return null;
            }
        }
        return result;
    }
}
