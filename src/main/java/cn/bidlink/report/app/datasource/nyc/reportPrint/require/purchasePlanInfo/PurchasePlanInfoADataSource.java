package cn.bidlink.report.app.datasource.nyc.reportPrint.require.purchasePlanInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.plan.DubboPurchasePlanInfoService;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public class PurchasePlanInfoADataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("planId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id" ,"company_id","plan_code","plan_type","plan_name","create_id","create_name","create_date","purchase_dep_id",
                "purchase_dep_name","plan_cycle","plan_cycle_value","plan_version","budget_total","plan_status","remark","child_value","plan_source","erp_plan_code"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboPurchasePlanInfoService purchasePlanInfoService = dataServiceFactory.getDataService(DubboPurchasePlanInfoService.class);
        String planId = param.get("planId");
        String companyId = param.get("companyId");

        if(StringUtils.isNotEmpty(planId) && StringUtils.isNotEmpty(companyId)){
            ServiceResult<List<Map<String, Object>>> listServiceResult = purchasePlanInfoService.purchasePlanInfoA(planId, companyId);
            return listServiceResult.getResult();
        }
        return null;


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
