package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenSupplier;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:56
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.ProcessProject
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenSupplierService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger log = LoggerFactory.getLogger(ProcessProjectDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"项目编号", "项目名称", "用户名称", "创建时间", "project_status", "approve_status", "type", "divide_bid_mark", "报价精度", "purchase_name"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboBidOpenSupplierService dataService = dataServiceFactory.getDataService(DubboBidOpenSupplierService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

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