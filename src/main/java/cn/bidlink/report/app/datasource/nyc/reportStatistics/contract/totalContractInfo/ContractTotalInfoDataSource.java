package cn.bidlink.report.app.datasource.nyc.reportStatistics.contract.totalContractInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboTotalContractInfoService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContractTotalInfoDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ContractTotalInfoDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("create_time_start"),
                new Parameter("create_time_end"),
                new Parameter("sign_time_start"),
                new Parameter("sign_time_end"),
                new Parameter("keyWords"),
                new Parameter("keyValue"),
                new Parameter("filiale"),
                new Parameter("agreement")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"create_time" ,"create_user_name","code","serial_code","contract_name" ,"total_price","status","department_code","dept_name" ,
                "purchaser_company_sign_name","supplier_company_sign_name","sign_time","classification_code" ,"classification_name","project_insdustry_code",
                "project_insdustry_name","agreement"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboTotalContractInfoService dataService = dataServiceFactory.getDataService(DubboTotalContractInfoService.class);
        String companyId = (String.valueOf(param.get("compId")).equals(""))?null:String.valueOf(param.get("compId"));
        String createTimeStart =  (String.valueOf(param.get("create_time_start")).equals(""))?null:String.valueOf(param.get("create_time_start"));
        String createTimeEnd =  (String.valueOf(param.get("create_time_end")).equals(""))?null:String.valueOf(param.get("create_time_end"));
        String signTimeStart =  (String.valueOf(param.get("sign_time_start")).equals(""))?null:String.valueOf(param.get("sign_time_start"));
        String signTimeEnd =  (String.valueOf(param.get("sign_time_end")).equals(""))?null:String.valueOf(param.get("sign_time_end"));
        String keyWords =  (String.valueOf(param.get("keyWords")).equals(""))?null:String.valueOf(param.get("keyWords"));
        String keyValue = (String.valueOf(param.get("keyValue")).equals(""))?null:String.valueOf(param.get("keyValue"));
        String filiale = (String.valueOf(param.get("filiale")).equals(""))?null:String.valueOf(param.get("filiale"));
        String agreement = (String.valueOf(param.get("agreement")).equals(""))?null:String.valueOf(param.get("agreement"));
        //agreement 只有为 ( 1:协议 2:非协议 ) 时 参与过滤
        agreement = agreement.length() > 1 ? null: agreement;

        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "compId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.contractTotalInfo(companyId, createTimeStart, createTimeEnd, signTimeStart, signTimeEnd, keyWords, keyValue, filiale, agreement);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }
}
