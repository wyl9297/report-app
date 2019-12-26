package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_sanyou;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessSanyouService;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SupplierNameDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(SupplierNameDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessSanyouService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessSanyouService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String bs = String.valueOf(param.get("bs"));
        String sids = String.valueOf(param.get("sids"));
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

            if (StringUtils.isNotEmpty(sids)) {
                result = filterList((ArrayList) result, sids);
            }
        }
        return result;
    }

    //过滤 下拉选 供应商
    private ArrayList filterList(ArrayList valueList, String supplierIds) {
        if ("全部供应商".equals(supplierIds)) {
            return valueList;
        }
        ArrayList list = new ArrayList();
        for (int i = 0; i < valueList.size(); i++) {
            LinkedHashMap obj = (LinkedHashMap) valueList.get(i);
            String supplierId = obj.get("supplier_id").toString();
            if (supplierIds.indexOf(supplierId) != -1) {
                list.add(obj);
            }
        }
        return list;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("bs"),new Parameter("companyId"),new Parameter("sids")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "中标商","supplier_id","mobile"};
    }
}
