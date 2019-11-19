package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.modifyFinalPriceList;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboModifyFinalPriceListService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ModifyFinalPriceListDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ModifyFinalPriceListDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"directory_name", "directory_code", "spec", "tech_prameters", "unit_name", "plan_amount", "RMB"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboModifyFinalPriceListService dataService = dataServiceFactory.getDataService(DubboModifyFinalPriceListService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.modifyFinalPriceList(projectId, companyId);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboModifyFinalPriceListService.modifyFinalPriceList",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
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
