package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenSupplier;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:57
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.BidOpenSupplierA
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenSupplierService;
import com.fr.base.Parameter;


import java.util.List;
import java.util.Map;

/**
 * @ClassName BidOpenSupplierA
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 11:57
 * @Version 1.0
 **/
public class BidOpenSupplierADataSource extends AbstractColumnPositionTableData {
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
        return new String[]{ "supplier_name" ,"supplier_type","project_id","id","invite_flag"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboBidOpenSupplierService dataService = dataServiceFactory.getDataService(DubboBidOpenSupplierService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        String supplierId = String.valueOf(param.get("supplierId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.bidOpenSupplierA(projectId,companyId,supplierId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}