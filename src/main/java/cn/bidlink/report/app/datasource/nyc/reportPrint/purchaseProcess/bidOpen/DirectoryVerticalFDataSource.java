package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpen;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:18
 * @description :原E采供  cn.bidlink.nyc.report.dataSource.purchaseProcess.DirectoryVerticalF
 */
public class DirectoryVerticalFDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(DirectoryVerticalFDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplier_name", "supplier_type", "project_id", "id", "invite_flag", "supplier_id"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenService bidOpenService = dataServiceFactory.getDataService(DubboBidOpenService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenService.directoryVerticalF(projectId, companyId);

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
