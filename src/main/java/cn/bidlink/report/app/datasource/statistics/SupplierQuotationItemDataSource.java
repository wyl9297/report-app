package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.entity.ProjectWideTable;
import cn.bidlink.report.server.service.SupplierPurchaseStatisticsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SupplierPurchaseStatisticsDataSource
 * @Author congyaozhu
 * @Description //供应商采购列表 数据集
 * @Date 2019/9/10 11:45
 * @Version 1.0
 **/
public class SupplierQuotationItemDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("supplierId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "projectCode", "projectName", "createTime", "createUserName", "supplierName" , "supplierId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        SupplierPurchaseStatisticsService supplierPurchaseStatisticsService = dataServiceFactory.getDataService(SupplierPurchaseStatisticsService.class);
        Long companyId = Long.valueOf(param.get("companyId"));
        Long supplierId = Long.valueOf(param.get("supplierId"));
        ServiceResult<List<ProjectWideTable>> listServiceResult = supplierPurchaseStatisticsService.selSupplierQuoteBySupplierId(supplierId, companyId);
        List<ProjectWideTable> result = listServiceResult.getResult();
        return result;
    }
}