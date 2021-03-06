package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.archivedAuctionProject;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.auction.DubboArchivedAuctionProjectService;
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
public class ArchivedAuctionDirectoryDataSource extends AbstractColumnPositionTableData {
    private static Logger logger = LoggerFactory.getLogger(ArchivedAuctionDirectoryDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"采购品名称", "采购品编码", "规格/型号", "技术参数/材质", "采购量", "单位"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboArchivedAuctionProjectService dubboArchivedAuctionProjectService = dataServiceFactory.getDataService(DubboArchivedAuctionProjectService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));

        boolean panduan = ParamUtils.panduan(param, "projectId", "companyId");

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dubboArchivedAuctionProjectService.archivedAuctionDirectory(projectId, companyId);
            if (!listServiceResult.getSuccess()) {
                logger.error("{}调用{}时发生未知异常,error Message:{}", "dubboArchivedAuctionProjectService.archivedAuctionDirectory",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        } else{
            logger.error("{}数据源所需必要参数不全", logger.getName());
            return null;
        }

    }

}
