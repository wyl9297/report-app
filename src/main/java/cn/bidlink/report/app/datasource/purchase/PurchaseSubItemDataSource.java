package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.service.ProjectRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchaseSubItemDataSource
 * @Author wyl
 * @Description 采购项目分项报价项数据源
 * @Date 2019/5/29 15:59
 * @Version 1.0
 **/
public class PurchaseSubItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"key", "title"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        ProjectRestService projectRestService = dataServiceFactory.getDataService(ProjectRestService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        List<Map<String, Object>> printingSubItem = projectRestService.findPrintingSubItem(projectId, companyId);
        return printingSubItem;
    }

}
