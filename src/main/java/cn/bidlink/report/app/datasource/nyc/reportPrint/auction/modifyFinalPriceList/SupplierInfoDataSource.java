package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.modifyFinalPriceList;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboModifyFinalPriceListService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class SupplierInfoDataSource extends AbstractColumnPositionTableData {

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

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.supplierInfo(projectId, companyId, supplierId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

}
