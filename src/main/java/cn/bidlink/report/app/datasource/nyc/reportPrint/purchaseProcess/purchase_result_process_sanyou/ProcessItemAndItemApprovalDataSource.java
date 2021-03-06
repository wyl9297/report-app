package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_sanyou;

import cn.bidlink.nyc.workflow.service.workflow.WorkflowApproveDubboService;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ApprovalCommon;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessSanyouService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessItemAndItemApprovalDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProcessItemAndItemApprovalDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        List<Map<String, Object>> result = new ArrayList<>();
        //校验是否缺失必填参数
        boolean sel = ParamUtils.sel(param, "projectId", "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            WorkflowApproveDubboService dataService = dataServiceFactory.getDataService(WorkflowApproveDubboService.class);
            ApprovalCommon approvalCommon = new ApprovalCommon();
            result = approvalCommon.processItemAndItemApproval(dataService, projectId, companyId);
            return result;
        }
        return result;

    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId"),new Parameter("createUsername")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"approveCount","index", "approve_user_name","approve_opition","create_tm","approve_result","approve_id"};
    }
}
