
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeParamDto;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.purchase.TaskRecodeDtoVO;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：审批导出 审批具体内容详情(审批人，审批时间，审批意见，审批结果)
 * @Date 2019/7/11
 *
 */
public class ExaminatioApprovalProjectDetailTwoDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("projectId"),
                new Parameter("executor"),
                new Parameter("module")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"taskId" ,"loginName" ,"userName" ,"approveTime" ,"approveSuggestion" ,"taskApproveResult" ,"companyId" ,"module" ,"moduleNodeType" ,"serialNumber" ,"index" ,"nodeType" ,"nodeTypeName" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService project = dataServiceFactory.getDataService(PurchaseProxyService.class);

        ApproveRecodeParamDto approveRecodeParamDto = new ApproveRecodeParamDto();
        approveRecodeParamDto.setProjectId(Long.valueOf(param.get("projectId")));
        approveRecodeParamDto.setCompanyId(Long.valueOf(param.get("companyId")));
        approveRecodeParamDto.setExecutor(param.get("executor"));
        approveRecodeParamDto.setModule(Integer.parseInt(param.get("module")));
        List<TaskRecodeDtoVO> workflowApproveProjectDetailTwo = project.findWorkflowApproveProjectDetailTwo(approveRecodeParamDto);

        return workflowApproveProjectDetailTwo;

    }

}
