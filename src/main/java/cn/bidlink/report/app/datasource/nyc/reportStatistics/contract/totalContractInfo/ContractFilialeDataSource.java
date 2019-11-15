package cn.bidlink.report.app.datasource.nyc.reportStatistics.contract.totalContractInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboTotalContractInfoService;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractFilialeDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code","name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboTotalContractInfoService dataService = dataServiceFactory.getDataService(DubboTotalContractInfoService.class);
        String companyId = String.valueOf(param.get("compId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.contractFiliale(companyId);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;

    }
}
