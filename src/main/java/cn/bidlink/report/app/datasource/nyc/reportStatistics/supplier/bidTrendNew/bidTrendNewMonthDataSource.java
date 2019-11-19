package cn.bidlink.report.app.datasource.nyc.reportStatistics.supplier.bidTrendNew;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:46
 * @description :cn.bidlink.nyc.report.statistics.report.supplier.bidTrendNewMonth
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_statistics.DubboBidTrendNewService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName bidTrendNewMonthDataSource
 * @Author Administrator
 * @Description
 * @Date 2019/11/7 14:46
 * @Version 1.0
 **/
public class bidTrendNewMonthDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(bidTrendNewMonthDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("supplierId"),
                new Parameter("begin"),
                new Parameter("end"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "month" ,"ofall","t2","already","t3","failed","t4"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboBidTrendNewService dataService = dataServiceFactory.getDataService(DubboBidTrendNewService.class);
        String supplierId = String.valueOf(param.get("supplierId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        String companyId = String.valueOf(param.get("companyId"));
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "companyId", "startTime", "endTime", "supplierId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.bidTrendNewMonth(supplierId, startTime, endTime, companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }
}