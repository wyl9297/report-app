package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchaseResultProcess;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  13:21
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.TotalSheet
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TotalSheet
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 13:21
 * @Version 1.0
 **/
public class TotalSheetDataSource extends AbstractColumnPositionTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "num","project_id" ,"supplier_id","field_name","is_need","field_value"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.totalSheet(projectId, companyId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}