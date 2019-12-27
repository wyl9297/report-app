package cn.bidlink.report.app.datasource.nyc.reportPrint.supplierManage.evaluateReportPrint;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.suppliermanage.DubboEvaluateReportPrintService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class ChartsDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ChartsDataSource.class);

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
            ServiceResult<List<Map<String, Object>>> listServiceResult = evaluateReportPrintService.charts(catalogId, reportId, companyId, beginTime, endTime, chooseType);

            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
            if ( result == null || result.size() == 0){
                return null;
            }
            result = revertList(result);
        }
        return result;

    }

    private List<Map<String, Object>> revertList(List<Map<String, Object>> valueList){
        int num = valueList.size();
        BigDecimal total =new BigDecimal(0);
        BigDecimal all =new BigDecimal(1.00);
        List list = new ArrayList<>();
        for(int j=0;j<num;j++){
            Map<String, Object> firstSup = valueList.get(j);
            int totalMoney = ((BigDecimal)firstSup.get("totalMoney")).intValue();
            BigDecimal persent = (BigDecimal)firstSup.get("rate");
            if(totalMoney != 0){
                total= total.add(persent);
                firstSup.put("rate", persent.multiply(new BigDecimal(100)));
                list.add(firstSup);
            }
        }

        if(all.compareTo(total) != 0){
            Map<String, Object> otherSup = new LinkedHashMap<>();
            otherSup.put("id",null);
            otherSup.put("supplier_id",null);
            otherSup.put("company_name","其他供应商");
            otherSup.put("report_supplier_level",null);
            otherSup.put("supplier_status",null);
            otherSup.put("corp_status",null);
            otherSup.put("supplier_trade_evaluation",null);
            otherSup.put("priceNum",null);
            otherSup.put("evaluationNum",null);
            otherSup.put("totalMoney",null);
            otherSup.put("orderNum",null);
            BigDecimal num2 = new BigDecimal(100);
            BigDecimal rate = (BigDecimal)valueList.get(0).get("rate");
            otherSup.put("rate",(num2.subtract(rate)));
            list.add(otherSup);
        }
        return list;
    }
}
