package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_sanyou;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessSanyouService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class SupplierNameDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessSanyouService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessSanyouService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String bs = String.valueOf(param.get("bs"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = new ServiceResult<>();
        if (bs != null && "2".equals(bs)){
            dataService.getPurchaseResultProcessSupplierNameBidAll(projectId, companyId);
        }else {
            dataService.getPurchaseResultProcessSupplierNameAll(projectId, companyId);
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
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
