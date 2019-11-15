package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenSupplier;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:56
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.ProcessProject
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenSupplierService;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProcessProject
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 11:56
 * @Version 1.0
 **/
public class ProcessProjectDataSource extends AbstractColumnPositionTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "项目编号" ,"项目名称","用户名称","创建时间","project_status","approve_status","type","divide_bid_mark","报价精度","purchase_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboBidOpenSupplierService dataService = dataServiceFactory.getDataService(DubboBidOpenSupplierService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.processProject(projectId,companyId);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}