package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.ArchivedAuctionSupplierUnProject;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboArchivedAuctionSupplierUnProjectService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:liuqi@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @Date 2019/11/05
 *
 */
public class ArchivedAuctionSupplierUnProjectDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ArchivedAuctionSupplierUnProjectDataSource.class);

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
        return new String[]{"采购品名称","采购品编号","采购量","币种","分标比例","单项总价"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboArchivedAuctionSupplierUnProjectService dataService = dataServiceFactory.getDataService(DubboArchivedAuctionSupplierUnProjectService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String purchaserId = String.valueOf(param.get("purchaserId"));
        String supplierId = String.valueOf(param.get("supplierId"));

        boolean panduan = ParamUtils.panduan(param, "projectId", "projectId", "projectId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.archivedAuctionSupplierUnProject(projectId, purchaserId, supplierId);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboArchivedAuctionSupplierUnProjectService.archivedAuctionSupplierUnProject",
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