package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenDirectoryVertical;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:49
 * @description : 原E采供 cn.bidlink.nyc.report.dataSource.purchaseProcess.TotalSheet
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenDirectoryVerticalService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TotalSheet
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 11:49
 * @Version 1.0
 **/
public class TotalSheetDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[] { "num","project_id" ,"supplier_id","field_name","is_need","field_value"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenDirectoryVerticalService bidOpenDirectoryVerticalService = dataServiceFactory.getDataService(DubboBidOpenDirectoryVerticalService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenDirectoryVerticalService.totalSheet(projectId, companyId);
        return listServiceResult.getResult();

//        String[] column = this.getColumn();
//        return InsertParam.insert(column);
    }
}