
package cn.bidlink.report.app.datasource.demo;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierItemVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 简化版报表数据源程序集Demo
 */
public class DemoDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("projectSupplierId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "unitName", "purchaseAmount", "projectItemId", "quoteStatusStr"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
       /* ProjectSupplierItemRestService projectSupplierItemRestService = dataServiceFactory.getDataService(ProjectSupplierItemRestService.class);
        SupplierQuotedItemDto supplierQuotedItemDto = projectSupplierItemRestService.supplierQuotedItemlist(Long.valueOf(param.get("projectId")),Long.valueOf(param.get("projectSupplierId")),Long.valueOf(param.get("companyId")));
        List<ProjectSupplierItemVo> supplierItemVos = supplierQuotedItemDto.getTableData();*/
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProjectSupplierItemVo projectSupplierItemVo = new ProjectSupplierItemVo();
            projectSupplierItemVo.setName("name" + i);
            projectSupplierItemVo.setCode("code" + i);
            projectSupplierItemVo.setUnitName("unitName" + i);
            projectSupplierItemVo.setPurchaseAmount("purchaseAmount" + i);
            projectSupplierItemVo.setProjectItemId(Long.valueOf(param.get("projectId")));
            projectSupplierItemVo.setQuoteStatusStr("quoteStatusStr" + i);
            list.add(projectSupplierItemVo);
        }
        return list;
    }

}
