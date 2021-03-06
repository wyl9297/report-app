
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeParamDto;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.purchase.ApproveRecodeDtoVO;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：审批导出 审批具体内容详情(审批模式与业务节点)
 * @Date 2019/7/11
 *
 */
public class ExaminatioApprovalProjectDetailDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("module")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"index" ,"nodeType" ,"nodeTypeName" ,"nodeApproveResult" ,"moduleName" ,"moduleNodeName" ,"companyId" ,"module" ,"moduleNodeType" ,"serialNumber"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        PurchaseProxyService project = dataServiceFactory.getDataService(PurchaseProxyService.class);

        ApproveRecodeParamDto approveRecodeParamDto = new ApproveRecodeParamDto();
        approveRecodeParamDto.setProjectId(Long.valueOf(param.get("projectId")));
        approveRecodeParamDto.setCompanyId(UserContext.getCompanyId());
        //approveRecodeParamDto.setExecutor(param.get("executor"));
        approveRecodeParamDto.setModule(Integer.parseInt(param.get("module")));
        List<ApproveRecodeDtoVO> workflowApproveProjectDetail = project.findWorkflowApproveProjectDetail(approveRecodeParamDto);
        return workflowApproveProjectDetail;

    }

}
