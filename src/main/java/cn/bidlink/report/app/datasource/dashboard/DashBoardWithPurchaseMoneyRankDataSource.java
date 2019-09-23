package cn.bidlink.report.app.datasource.dashboard;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.entity.SupplierPurchasesDto;
import cn.bidlink.report.server.service.SupplierPurchaseStatisticsService;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DashBoardWithPurchaseMoneyRankDataSource
 * @Author congyaozhu
 * @Description 采购品采购金额排名
 * @Date 2019/9/19 11:54
 * @Version 1.0
 **/
public class DashBoardWithPurchaseMoneyRankDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("startDate"),
                new Parameter("endDate")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"company_id", "price", "directory_id" , "directory_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        SupplierPurchaseStatisticsService supplierPurchaseStatisticsService = dataServiceFactory.getDataService(SupplierPurchaseStatisticsService.class);
        String companyId = param.get("companyId");
        String startDate = param.get("startDate");
        String endDate = param.get("endDate");


        if(StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(startDate)  && StringUtils.isNotEmpty(endDate)){
            SupplierPurchasesDto dto = new SupplierPurchasesDto();
            dto.setCompanyId(Long.valueOf(companyId));
            dto.setcTime(startDate);
            dto.seteTime(endDate);
            ServiceResult<List<Map>> listServiceResult = supplierPurchaseStatisticsService.directoryFirstten(dto);
            List<Map> result = listServiceResult.getResult();

            return result;
        }
        return null;

    }

}