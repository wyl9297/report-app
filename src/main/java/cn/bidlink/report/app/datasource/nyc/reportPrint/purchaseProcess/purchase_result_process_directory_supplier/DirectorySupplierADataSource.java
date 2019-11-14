package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_directory_supplier;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessDirectorySupplierService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class DirectorySupplierADataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessDirectorySupplierService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessDirectorySupplierService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.directorySupplierA(projectId,companyId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{  "supplier_id" ,"project_id","supplier_name","supplier_type","deal_price","deal_amount",
                "bid_price","deal_total_price","bid_mark","project_item_id","code","bid_status" };
    }
}
