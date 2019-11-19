package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchaseResultProcess;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  13:19
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.SupplierName
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SupplierName
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 13:19
 * @Version 1.0
 **/
public class SupplierNameDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(SupplierNameDataSource.class);
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("bs"),
                new Parameter("companyId"),
                new Parameter("sids")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[] { "中标商","supplier_id","mobile"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessService.class);
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
                    log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessService.getPurchaseResultProcessSupplierNameBidAll",
                            "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                    throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
                }
            } else {
                listServiceResult = dataService.getPurchaseResultProcessSupplierNameAll(projectId, companyId);
                if (!listServiceResult.getSuccess()) {
                    log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessService.getPurchaseResultProcessSupplierNameAll",
                            "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                    throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
                }
            }
            result = listServiceResult.getResult();
        }
        return result;
    }
}