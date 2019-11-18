package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenDirectoryVertical;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:46
 * @description :原E采供 cn.bidlink.nyc.report.dataSource.purchaseProcess.DirectoryVerticalC
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenDirectoryVerticalService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DirectoryVerticalC
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 11:46
 * @Version 1.0
 **/
public class DirectoryVerticalCDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(DirectoryVerticalCDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"), new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplier_id", "project_id", "supplier_name", "supplier_type", "bid_price", "deal_total_price", "bid_mark", "project_item_id", "code", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "bid_status"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenDirectoryVerticalService bidOpenDirectoryVerticalService = dataServiceFactory.getDataService(DubboBidOpenDirectoryVerticalService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, projectId, companyId);

        if (panduan) {

            ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenDirectoryVerticalService.directoryVerticalC(projectId, companyId);

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