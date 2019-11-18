package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenDirectoryVertical;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:45
 * @description : 原E采供 cn.bidlink.nyc.report.dataSource.purchaseProcess.DirectoryVerticalD
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboBidOpenDirectoryVerticalService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DirectoryVerticalD
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 11:45
 * @Version 1.0
 **/
public class DirectoryVerticalDDataSource extends AbstractColumnPositionTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"), new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplier_name", "supplier_id", "total"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboBidOpenDirectoryVerticalService bidOpenDirectoryVerticalService = dataServiceFactory.getDataService(DubboBidOpenDirectoryVerticalService.class);
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, projectId, companyId);

        if (panduan) {

            ServiceResult<List<Map<String, Object>>> listServiceResult = bidOpenDirectoryVerticalService.directoryVerticalD(projectId, companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        }
        return null;
    }
}