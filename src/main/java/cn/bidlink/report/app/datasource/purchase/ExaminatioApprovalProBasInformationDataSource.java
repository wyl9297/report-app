
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectDto;
import cn.bidlink.procurement.purchase.cloud.service.ProjectRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：审批导出项目基本信息
 * @Date 2019/7/11
 *
 */
public class ExaminatioApprovalProBasInformationDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name" ,"code" ,"createUserName" ,"createTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        ProjectRestService project = dataServiceFactory.getDataService(ProjectRestService.class);
        ProjectDto projectBase = project.getProjectBase(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        List<ProjectDto> projectDetail = new ArrayList<>();
        if ( null != projectBase){
            projectDetail.add(projectBase);
        }
        return projectDetail;

    }

}
