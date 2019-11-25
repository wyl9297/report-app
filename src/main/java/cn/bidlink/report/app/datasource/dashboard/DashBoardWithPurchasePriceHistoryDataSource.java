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
 * @ClassName DashBoardWithPurchasePriceHistoryDataSource
 * @Author congyaozhu
 * @Description 采购价格历史走势
 * @Date 2019/9/19 11:54
 * @Version 1.0
 **/
public class DashBoardWithPurchasePriceHistoryDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("supplierId"),
                new Parameter("startDate"),
                new Parameter("endDate")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"company_id", "price", "time" , "supplier_id" , "supplier_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        SupplierPurchaseStatisticsService supplierPurchaseStatisticsService = dataServiceFactory.getDataService(SupplierPurchaseStatisticsService.class);
        String companyId = param.get("companyId");
        String supplierId = param.get("supplierId");
        String startDate = param.get("startDate");
        String endDate = param.get("endDate");

        SupplierPurchasesDto dto = new SupplierPurchasesDto();
        dto.setCompanyId(Long.valueOf(companyId));
        dto.setcTime(startDate);
        dto.seteTime(endDate);

        // 初始化采购价格历史走势
        if(StringUtils.isEmpty(supplierId)){
            ServiceResult<List<Map>> companyFirstten = supplierPurchaseStatisticsService.companyFirstten(dto);
            List<Map> companyFirsttenResult = companyFirstten.getResult();
            Map map = companyFirsttenResult.get(companyFirsttenResult.size()-1);
            if(!map.isEmpty()){
                Object supplier_id = map.get("supplier_id");
                dto.setSupplierId(Long.valueOf(String.valueOf(supplier_id)));

                if(StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(String.valueOf(supplier_id))
                        && StringUtils.isNotEmpty(startDate)  && StringUtils.isNotEmpty(endDate)){

                    ServiceResult<List<Map>> listServiceResult = supplierPurchaseStatisticsService.companySupplier(dto);
                    List<Map> result = listServiceResult.getResult();
                    return result;
                }
            }
        }else{
            if(StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(supplierId)
                    && StringUtils.isNotEmpty(startDate)  && StringUtils.isNotEmpty(endDate)){

                dto.setSupplierId(Long.valueOf(supplierId));
                ServiceResult<List<Map>> listServiceResult = supplierPurchaseStatisticsService.companySupplier(dto);
                List<Map> result = listServiceResult.getResult();
                return result;
            }
        }
        return null;
    }
}