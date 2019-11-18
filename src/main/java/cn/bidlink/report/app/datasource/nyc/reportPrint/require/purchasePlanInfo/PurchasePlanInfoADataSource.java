package cn.bidlink.report.app.datasource.nyc.reportPrint.require.purchasePlanInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.plan.DubboPurchasePlanInfoService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PurchasePlanInfoADataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(PurchasePlanInfoADataSource.class);

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
        List<Map<String, Object>> result = new ArrayList<>();
        //校验是否缺失必填参数
        boolean sel = ParamUtils.sel(param, planId, companyId);
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {

            ServiceResult<List<Map<String, Object>>> listServiceResult = purchasePlanInfoService.purchasePlanInfoA(planId, companyId);

            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
            if (result == null || result.size() == 0) {
                return null;
            }
        }
        return result;
    }
}
