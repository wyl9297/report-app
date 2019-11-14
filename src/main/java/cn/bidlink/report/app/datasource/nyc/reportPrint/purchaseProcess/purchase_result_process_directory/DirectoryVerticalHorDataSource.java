package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_directory;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessDirectoryService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

import cn.bidlink.report.app.datasource.nyc.InsertParam;

public class DirectoryVerticalHorDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessDirectoryService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessDirectoryService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.directoryVerticalHor(projectId,companyId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "code" ,"name","unit_name","spec","plan_amount","description","bid_price","usedepart",
                "need_time","bid_mark","supplier_project_bid_id","project_item_id",
                "data1","data2","data3","data4","data5","data6","data7","data8","data9","data10","data11","data12",
                "data13","data14","data15","supplier_user_name","supplier_id","bid_status"};
    }
}
