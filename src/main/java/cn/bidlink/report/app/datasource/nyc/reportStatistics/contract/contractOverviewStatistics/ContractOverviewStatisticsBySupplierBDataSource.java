package cn.bidlink.report.app.datasource.nyc.reportStatistics.contract.contractOverviewStatistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboContractOverviewStatisticsService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContractOverviewStatisticsBySupplierBDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ContractOverviewStatisticsBySupplierBDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("contrastBegin"),
                new Parameter("contrastEnd")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplier_id" ,"supplier_name","totalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboContractOverviewStatisticsService dataService = dataServiceFactory.getDataService(DubboContractOverviewStatisticsService.class);
        String companyId = String.valueOf(param.get("compId"));
        String startTime = String.valueOf(param.get("contrastBegin"));
        String endTime = String.valueOf(param.get("contrastEnd"));
        if (startTime != null && !"".equals(startTime)) {
            startTime = startTime.concat(" 00:00:00");
        }
        if (endTime != null && !"".equals(endTime)) {
            endTime = endTime.concat(" 23:59:59");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "compId", "contrastBegin", "contrastEnd");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.contractOverviewStatisticsBySupplierB(companyId, startTime, endTime);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }

}
