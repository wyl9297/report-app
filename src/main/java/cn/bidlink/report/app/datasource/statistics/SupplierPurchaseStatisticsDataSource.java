package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.entity.SupplierPurchasesDto;
import cn.bidlink.report.server.service.SupplierPurchaseStatisticsService;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SupplierPurchaseStatisticsDataSource
 * @Author congyaozhu
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
        // todo 暂无部门字段
//        String departmentName = param.get("departmentName");
        String supplierType = param.get("supplierType");
        String createTime = param.get("createTime");
        String endTime = param.get("endTime");
        String archiveStartTime = param.get("archiveStartTime");
        String archiveEndTime = param.get("archiveEndTime");
        supplierPurchasesDto.setCompanyId(companyId);
        supplierPurchasesDto.setSupplierName(supplierName);
        supplierPurchasesDto.setSupplierType(supplierType);
//        supplierPurchasesDto.setDepartmentName(departmentName);
        try {
            supplierPurchasesDto.setCreateTime(StringUtils.isNotEmpty(createTime)?DateUtils.parseDate(createTime, "yyyy-MM-dd HH:mm:ss"):null);
            supplierPurchasesDto.setEndTime(StringUtils.isNotEmpty(endTime)?DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss"):null);
            supplierPurchasesDto.setArchiveStartTime(StringUtils.isNotEmpty(archiveStartTime)?DateUtils.parseDate(archiveStartTime, "yyyy-MM-dd HH:mm:ss"):null);
            supplierPurchasesDto.setArchiveEndTime(StringUtils.isNotEmpty(archiveEndTime)?DateUtils.parseDate(archiveEndTime, "yyyy-MM-dd HH:mm:ss"):null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ServiceResult<List<SupplierPurchasesDto>> listServiceResult = supplierPurchaseStatisticsService.findAllByCondition(supplierPurchasesDto);
        List<SupplierPurchasesDto> result = listServiceResult.getResult();
        return result;
    }
}