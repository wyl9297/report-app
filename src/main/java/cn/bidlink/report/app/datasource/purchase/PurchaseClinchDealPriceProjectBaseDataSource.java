
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
 * @description:采购项目：成交定价采购品-供应商维度 项目基本信息
 * @Date 2019/7/12
 *
 */
public class PurchaseClinchDealPriceProjectBaseDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "id", "createTimeStr", "dealTotalPrice", "createUserName", "createTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        ProjectRestService dataService = dataServiceFactory.getDataService(ProjectRestService.class);
        ProjectDto projectBase = dataService.getProjectBase(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        List<ProjectDto> projectDetail = new ArrayList<>();
        List<ProjectDto> projectDetailNull = new ArrayList<>();
        if ( null != projectBase && !"".equals(projectBase)){
            projectDetail.add(projectBase);
            return projectDetail;
        }else {
            return projectDetailNull;
        }

    }

}
