package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.SupplierBidResultDataSet;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboSupplierBidResultDataSetService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BidOpenSupplierResultBDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(BidOpenSupplierResultBDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboSupplierBidResultDataSetService dataService = dataServiceFactory.getDataService(DubboSupplierBidResultDataSetService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String supplierId = String.valueOf(param.get("supplierId"));

        List<Map<String, Object>> result = new ArrayList<>();
        //校验是否缺失必填参数
        boolean sel = ParamUtils.sel(param, "projectId", "companyId", "supplierId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.bidOpenSupplierResultB(projectId, companyId, supplierId);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboSupplierBidResultDataSetService.bidOpenSupplierResultB",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("supplierId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "supplier_user_name"};
    }
}
