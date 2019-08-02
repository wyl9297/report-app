package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.dto.SupplierQuotedItemDto;
import cn.bidlink.procurement.purchase.cloud.service.ProjectSupplierItemRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SupplierBaseDataSourceTemplate
 * @Author 从尧
 * @Description 查看单个供应商报价详细情况[基础数据]
 * @Date 2019-05-21 10:56
 * @Version 1.0
 **/
public class SupplierBaseDataSourceTemplate extends AbstractBaseTableData {
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
        return new String[]{"projectName","supplierName","quoteProjectItemCount","haveQuoteProjectItemCount"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        ProjectSupplierItemRestService projectSupplierItemRestService = dataServiceFactory.getDataService(ProjectSupplierItemRestService.class);

        //获取报表查询的参数
        String projectId = param.get("projectId");
        String projectSupplierId = param.get("projectSupplierId");
        String companyId = param.get("companyId");

        //查询数据并返回
        SupplierQuotedItemDto supplierQuotedItemDto = projectSupplierItemRestService.supplierQuotedItemlist(Long.valueOf(projectId), Long.valueOf(projectSupplierId), Long.valueOf(companyId));
        List list = new ArrayList();
        list.add(supplierQuotedItemDto);
        return list;
    }
}
