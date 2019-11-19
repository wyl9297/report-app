package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenDirectoryHorizontal;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenDirectoryHorizontalService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:26
 * @description :
 */
public class DirectoryVerticalHDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(DirectoryVerticalHDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"order_number", "item_name", "required"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenDirectoryHorizontalService bidOpenDirectoryHorizontalService = dataServiceFactory.getDataService(DubboBidOpenDirectoryHorizontalService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenDirectoryHorizontalService.directoryVerticalH(projectId, companyId);

            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        } else{
            log.error("{}数据源所需必要参数不全", log.getName());
            return null;
        }
    }
}

