package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_directory;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessDirectoryService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ProcessProjectDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProcessProjectDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{new Parameter("projectId"), new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"项目编号", "项目名称", "用户名称", "创建时间", "project_status", "approve_status", "type",
                "divide_bid_mark", "报价精度", "purchase_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessDirectoryService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessDirectoryService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        boolean panduan = ParamUtils.panduan(param, projectId, companyId);

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.processProject(projectId, companyId);
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
