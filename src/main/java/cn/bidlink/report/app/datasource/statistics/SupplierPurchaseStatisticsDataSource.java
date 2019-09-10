package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.entity.SupplierPurchasesDto;
import cn.bidlink.report.server.service.SupplierPurchaseStatisticsService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SupplierPurchaseStatisticsDataSource
 * @Author dell
 * @Description //供应商采购成交统计   汇总数据集
 * @Date 2019/9/10 11:45
 * @Version 1.0
 **/
public class SupplierPurchaseStatisticsDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("createTime"),
                new Parameter("endTime"),
                new Parameter("archiveStartTime"),
                new Parameter("archiveEndTime"),
                new Parameter("supplierName"),
                new Parameter("departmentName"),
                new Parameter("supplierType")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "supplierName", "dealTotalPrice", "winProject", "priceProject", "supplierId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        SupplierPurchaseStatisticsService supplierPurchaseStatisticsService = dataServiceFactory.getDataService(SupplierPurchaseStatisticsService.class);
        SupplierPurchasesDto supplierPurchasesDto = new SupplierPurchasesDto();
        Long companyId = Long.valueOf(param.get("companyId"));
        String supplierName = param.get("supplierName");
        String departmentName = param.get("departmentName");
        String supplierType = param.get("supplierType");
        supplierPurchasesDto.setCompanyId(companyId);
        supplierPurchasesDto.setSupplierName(supplierName);
        supplierPurchasesDto.setSupplierType(supplierType);
        supplierPurchasesDto.setDepartmentName(departmentName);
        supplierPurchaseStatisticsService.findAllByCondition(supplierPurchasesDto);
        return null;
    }
}