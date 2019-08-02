
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectItemDto;
import cn.bidlink.procurement.purchase.cloud.service.ProjectItemRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购品信息（含报价方案信息）中 采购品数据集
 * @Date 2019/5/23
 *
 */
public class PurchaseProjectItemListDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id" ,"projectId" ,"directoryId" ,"code", "name", "purchaseAmount", "marketPrice",
                "planPrice", "unitName", "spec", "techParameters", "needTime", "createTime", "subCompanyId",
                "appliedEnterprise", "appliedEnterpriseId", "appliedPersonAndPhone", "useDept", "purpose",
                "comment", "sourceType", "sourceName", "companyId", "createUserId", "createUserName",
                "updateUserId", "updateUserName", "executed", "optType", "modifyDisabled", "confirmDisabled",
                "selectionDisabled", "isAppend", "rowNum", "catalogId", "catalogCode", "catalogName"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        ProjectItemRestService projectItemRestService = dataServiceFactory.getDataService(ProjectItemRestService.class);
        List<ProjectItemDto> projectItemDtos = projectItemRestService.projectItemList(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        return projectItemDtos;

    }

}
