package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchaseResultProcess;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  13:19
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.ProcessItemAndItemStandard
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProcessItemAndItemStandard
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 13:19
 * @Version 1.0
 **/
public class ProcessItemAndItemStandardDataSource extends AbstractColumnPositionTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[] { "divide_bid_mark"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.processItemAndItemStandard(projectId, companyId);
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}