package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_sanyou;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessSanyouService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessItemAndItemBidDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProcessItemAndItemApprovalDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessSanyouService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessSanyouService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String bs = String.valueOf(param.get("bs"));
        String companyId = String.valueOf(param.get("companyId"));
        List<Map<String, Object>> result = new ArrayList<>();
        //校验是否缺失必填参数
        boolean sel = ParamUtils.sel(param, "projectId", "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = new ServiceResult<>();
            if (bs != null && ("2").equals(bs)) {
                listServiceResult = dataService.getPurchaseResultProcessDsThr(projectId, companyId);
                if (!listServiceResult.getSuccess()) {
                    log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessSanyouService.getPurchaseResultProcessDsThr",
                            "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                    throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
                }
            } else {
                listServiceResult = dataService.getPurchaseResultProcessDsTwo(projectId, companyId);
                if (!listServiceResult.getSuccess()) {
                    log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessSanyouService.getPurchaseResultProcessDsTwo",
                            "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                    throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
                }
            }
            result = listServiceResult.getResult();
        }
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("bs"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "采购品编码" ,"采购品名称","单位","规格","数量","报价","成交价","分标数量","节约额","分标说明","备注","supplier_id"
                ,"用途","使用单位","技术参数/材质","申报单位","申报人电话","中标状态","平均价格", "need_time",
                "data1","data2","data3","data4","data5","data6","data7","data8","data9","data10",
                "data11","data12","data13","data14","data15","contractName","bid_status"};
    }
}
