package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.archivedAuctionSupplierProject;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboArchivedAuctionSupplierProjectService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  9:28
 * @description :
 */
public class ArchivedAuctionSupplierProjectMoneyDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ArchivedAuctionSupplierProjectMoneyDataSource.class);
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("purchaserId"),
                new Parameter("supplierId")
        };
    }

    @Override
    protected String[] getColumn() {
         return new String[]{"合计金额"};
    }
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboArchivedAuctionSupplierProjectService dataService = dataServiceFactory.getDataService(DubboArchivedAuctionSupplierProjectService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String purchaserId = String.valueOf(param.get("purchaserId"));
        String supplierId = String.valueOf(param.get("supplierId"));

        boolean panduan = ParamUtils.panduan(param, "projectId", "purchaserId", "supplierId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.archivedAuctionSupplierProjectMoney(projectId, purchaserId, supplierId);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboArchivedAuctionSupplierProjectService.archivedAuctionSupplierProjectMoney",
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
