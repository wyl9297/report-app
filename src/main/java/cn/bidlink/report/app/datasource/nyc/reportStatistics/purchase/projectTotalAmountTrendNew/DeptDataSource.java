package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectTotalAmountTrendNew;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:36
 * @description :cn.bidlink.nyc.report.statistics.report.overviewStatistics.Dept
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboProjectTotalAmountTrendNewService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
    private static Logger log = LoggerFactory.getLogger(DeptDataSource.class);

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
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.overviewStatisticsDept(companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return  result;


    }
}