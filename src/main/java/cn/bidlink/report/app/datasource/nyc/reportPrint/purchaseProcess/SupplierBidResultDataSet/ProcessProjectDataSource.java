package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.SupplierBidResultDataSet;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboSupplierBidResultDataSetService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ProcessProjectDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboSupplierBidResultDataSetService dataService = dataServiceFactory.getDataService(DubboSupplierBidResultDataSetService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.processProject(projectId, companyId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "项目编号" ,"项目名称","用户名称","创建时间","project_status","approve_status","type","divide_bid_mark","报价精度","purchase_name"};
    }
}
