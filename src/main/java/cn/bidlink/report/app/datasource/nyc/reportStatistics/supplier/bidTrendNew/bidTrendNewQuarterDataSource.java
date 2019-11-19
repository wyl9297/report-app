package cn.bidlink.report.app.datasource.nyc.reportStatistics.supplier.bidTrendNew;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:46
 * @description :cn.bidlink.nyc.report.statistics.report.supplier.bidTrendNewQuarter
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
 * @ClassName bidTrendNewQuarterDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/7 14:46
 * @Version 1.0
 **/
public class bidTrendNewQuarterDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(bidTrendNewQuarterDataSource.class);

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
        return new String[]{ "quater" ,"ofall","t2","already","t3","failed","t4"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboBidTrendNewService dataService = dataServiceFactory.getDataService(DubboBidTrendNewService.class);
        String supplierId = String.valueOf(param.get("supplierId"));
        String startTime = String.valueOf(param.get("begin"));
        String endTime = String.valueOf(param.get("end"));
        String companyId = String.valueOf(param.get("companyId"));
        List<Map<String, Object>> result = new ArrayList<>();
        boolean sel = ParamUtils.sel(param, "supplierId", "begin", "end", "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.bidTrendNewQuarter(supplierId, startTime, endTime, companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }
}