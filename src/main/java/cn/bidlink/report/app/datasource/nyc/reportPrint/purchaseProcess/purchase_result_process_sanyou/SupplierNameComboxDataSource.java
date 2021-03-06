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

public class SupplierNameComboxDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(SupplierNameComboxDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessSanyouService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessSanyouService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String bs = String.valueOf(param.get("bs"));

        List<Map<String, Object>> result = new ArrayList<>();
        //校验是否缺失必填参数
        boolean sel = ParamUtils.sel(param, "projectId", "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = new ServiceResult<>();
            if (bs != null && "2".equals(bs)) {
                listServiceResult = dataService.getPurchaseResultProcessSupplierNameBidAll(projectId, companyId);
                if (!listServiceResult.getSuccess()) {
                    log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessSanyouService.getPurchaseResultProcessSupplierNameBidAll",
                            "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                    throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
                }
            } else {
                listServiceResult = dataService.getPurchaseResultProcessSupplierNameAll(projectId, companyId);
                if (!listServiceResult.getSuccess()) {
                    log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessSanyouService.getPurchaseResultProcessSupplierNameBidAll",
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
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId"),new Parameter("bs")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "supplierName","supplierId"};
    }
}
