package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectTotalAmountTrendNew;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:36
 * @description :cn.bidlink.nyc.report.statistics.report.overviewStatistics.Dept
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectTotalAmountTrendNewService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DeptDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/7 14:36
 * @Version 1.0
 **/
public class DeptDataSource extends AbstractColumnPositionTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id","code","name","parentId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboProjectTotalAmountTrendNewService dataService = dataServiceFactory.getDataService(DubboProjectTotalAmountTrendNewService.class);
        String companyId = String.valueOf(param.get("compId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.overviewStatisticsDept(companyId);
        if (!listServiceResult.getSuccess()) {
            throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return  result;


    }
}