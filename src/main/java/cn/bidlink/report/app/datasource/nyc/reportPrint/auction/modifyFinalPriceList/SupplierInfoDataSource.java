package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.modifyFinalPriceList;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboModifyFinalPriceListService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class SupplierInfoDataSource extends AbstractColumnPositionTableData {

    private static Logger log = LoggerFactory.getLogger(SupplierInfoDataSource.class);
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("supplierId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"supplier_name", "link_man", "link_phone", "real_price", "final_price","RMB"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboModifyFinalPriceListService dataService = dataServiceFactory.getDataService(DubboModifyFinalPriceListService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String supplierId = String.valueOf(param.get("supplierId"));

        boolean panduan = ParamUtils.panduan(param, projectId, companyId, supplierId);

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.supplierInfo(projectId, companyId, supplierId);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboModifyFinalPriceListService.supplierInfo",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        }
        return null;
    }

}
