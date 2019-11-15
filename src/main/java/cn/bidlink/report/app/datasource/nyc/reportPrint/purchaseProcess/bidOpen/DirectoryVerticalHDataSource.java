package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpen;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:18
 * @description : 原E采供  cn.bidlink.nyc.report.dataSource.purchaseProcess.DirectoryVerticalH
 */
public class DirectoryVerticalHDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "order_number" ,"item_name","required"};
    }
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenService bidOpenService = dataServiceFactory.getDataService(DubboBidOpenService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenService.directoryVerticalH(projectId, companyId);
        return listServiceResult.getResult();

//        String[] column = this.getColumn();
//        return InsertParam.insert(column);
    }
}

