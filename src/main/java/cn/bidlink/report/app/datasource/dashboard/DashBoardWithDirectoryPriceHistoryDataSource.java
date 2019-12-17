package cn.bidlink.report.app.datasource.dashboard;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.entity.SupplierPurchasesDto;
import cn.bidlink.report.server.service.SupplierPurchaseStatisticsService;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DashBoardWithDirectoryPriceHistoryDataSource
 * @Author congyaozhu
 * @Description 采购品价格历史走势
 * @Date 2019/9/19 11:54
 * @Version 1.0
 **/
public class DashBoardWithDirectoryPriceHistoryDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("directoryId"),
                new Parameter("startDate"),
                new Parameter("endDate")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"company_id", "price", "time", "directory_id", "directory_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        SupplierPurchaseStatisticsService supplierPurchaseStatisticsService = dataServiceFactory.getDataService(SupplierPurchaseStatisticsService.class);
        String companyId = param.get("companyId");
        String directoryId = param.get("directoryId");
        String startDate = param.get("startDate");
        String endDate = param.get("endDate");

        SupplierPurchasesDto dto = new SupplierPurchasesDto();
        dto.setCompanyId(Long.valueOf(companyId));
        dto.setcTime(startDate);
        dto.seteTime(endDate);

        // 初始化采购品价格历史走势
        if (StringUtils.isEmpty(directoryId)) {

            ServiceResult<List<Map>> directoryFirstten = supplierPurchaseStatisticsService.directoryFirstten(dto);
            List<Map> directoryResult = directoryFirstten.getResult();
            if(!CollectionUtils.isEmpty(directoryResult)){
                Map map = directoryResult.get(directoryResult.size()-1);
                if (!map.isEmpty()) {

                    Object directory_id = map.get("directory_id");
                    dto.setDirectId(Long.valueOf(String.valueOf(directory_id)));

                    if (StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(String.valueOf(directory_id)) && StringUtils.isNotEmpty(startDate)
                            && StringUtils.isNotEmpty(endDate)) {
                        ServiceResult<List<Map>> listServiceResult = supplierPurchaseStatisticsService.companyDirectory(dto);
                        List<Map> result = listServiceResult.getResult();
                        return result;
                    }
                }
            }
        }else{
            if (StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(directoryId) && StringUtils.isNotEmpty(startDate)
                    && StringUtils.isNotEmpty(endDate)) {
                dto.setDirectId(Long.valueOf(directoryId));
                ServiceResult<List<Map>> listServiceResult = supplierPurchaseStatisticsService.companyDirectory(dto);
                List<Map> result = listServiceResult.getResult();
                return result;
            }
        }

        return new ArrayList();
    }

}