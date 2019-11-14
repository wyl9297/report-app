package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.ArchivedAuctionUnProject;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboArchivedAuctionUnProjectService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author liuqi
 * @date 2019-11-05 15:49
 */
public class ArchivedAuctionUnProjectDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ArchivedAuctionUnProjectDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboArchivedAuctionUnProjectService dataService = dataServiceFactory.getDataService(DubboArchivedAuctionUnProjectService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.archivedAuctionUnProject(projectId, companyId);
        if (!listServiceResult.getSuccess()){
            log.error("{}调用{}时发生未知异常,error Message:{}", "DubboArchivedAuctionUnProjectService.archivedAuctionUnProject",
                    "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"采购品名称","采购品编码","采购量","中标供应商名称","联系人","币种","最终价","分标比例","单项总价"};
    }
}
