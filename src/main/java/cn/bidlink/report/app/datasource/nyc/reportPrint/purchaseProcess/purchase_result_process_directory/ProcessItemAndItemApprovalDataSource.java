package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_directory;

import cn.bidlink.nyc.workflow.service.workflow.WorkflowApproveDubboService;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ApprovalCommon;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ProcessItemAndItemApprovalDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProcessItemAndItemApprovalDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{new Parameter("projectId"), new Parameter("companyId"), new Parameter("createUsername")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"approveCount", "index", "approve_user_name", "approve_opition", "create_tm", "approve_result", "approve_id"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

        if (panduan) {
            WorkflowApproveDubboService dataService = dataServiceFactory.getDataService(WorkflowApproveDubboService.class);
            ApprovalCommon approvalCommon = new ApprovalCommon();
            List<Map<String, Object>> result = approvalCommon.processItemAndItemApproval(dataService, projectId, companyId);
            return result;
        } else{
            log.error("{}数据源所需必要参数不全", log.getName());
            return null;
        }
    }
}
