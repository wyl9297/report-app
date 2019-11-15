package cn.bidlink.report.app.datasource.nyc.reportPrint.require.purchasePlanInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.plan.DubboPurchasePlanInfoService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class PurchasePlanInfoBDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("planId"),
                new Parameter("approveFlag"),
                new Parameter("sumNumber"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"CODE" ,"NAME","SPEC","UNITNAME","tech_parameters","id","company_id","plan_id","directorys_id","distribution_flag",
                "approve_flag","remain_number","plan_number","actual_number","plan_price","purpose","workshop","recommend_supplier",
                "use_date","summarize_id","tec_param","declarant_name","erp_plan_code","remark"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboPurchasePlanInfoService purchasePlanInfoService = dataServiceFactory.getDataService(DubboPurchasePlanInfoService.class);
        String planId = param.get("planId");
        String companyId = param.get("companyId");
        ServiceResult<List<Map<String, Object>>> listServiceResult = purchasePlanInfoService.purchasePlanInfoB(planId, companyId);

        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        if ( result == null || result.size() ==0 ){
            return null;
        }
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
