
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.service.ProjectRestService;
import cn.bidlink.procurement.purchase.dal.entity.PurchaseProject;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目->报价一览表->采购品维度->项目基本信息
 * @Date 2019/6/25
 *
 */
public class PurchaseSupplierProjuctDataSource extends AbstractBaseTableData {

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
        PurchaseProject purchaseProject = project.getProject(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        List<PurchaseProject> projectDetail = new ArrayList<>();
        List<PurchaseProject> projectDetailNull = new ArrayList<>();
        if ( null != purchaseProject){
            projectDetail.add(purchaseProject);
            return projectDetail;
        }else {
            return projectDetailNull;
        }

    }

}
