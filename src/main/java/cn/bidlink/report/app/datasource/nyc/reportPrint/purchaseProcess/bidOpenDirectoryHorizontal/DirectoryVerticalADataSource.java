package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenDirectoryHorizontal;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenDirectoryHorizontalService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:24
 * @description :
 */
public class DirectoryVerticalADataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "code" ,"name","unit_name","spec","plan_amount","usedepart","need_time","id","tech_parameters","purpose","applied_enterprise","applied_person_and_phone","description"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenDirectoryHorizontalService bidOpenDirectoryHorizontalService = dataServiceFactory.getDataService(DubboBidOpenDirectoryHorizontalService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenDirectoryHorizontalService.directoryVerticalA(projectId, companyId);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        if ( result == null){
             return null;
        }
        return listServiceResult.getResult();

//        String[] column = this.getColumn();
//        return InsertParam.insert(column);
    }
}

