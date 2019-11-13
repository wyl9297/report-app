package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.SupplierBidResultDataSet;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboSupplierBidResultDataSetService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class BidOpenSupplierResultADataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboSupplierBidResultDataSetService dataService = dataServiceFactory.getDataService(DubboSupplierBidResultDataSetService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String supplierId = String.valueOf(param.get("supplierId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.bidOpenSupplierResultA(projectId, companyId, supplierId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("supplierId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "code" ,"name","spec","productor","pcode","plan_amount","unit_name","usedepart",
                "need_time","description","bid_price","deal_price","deal_total_price","deal_amount","divide_bid_ration","tech"};
    }
}
