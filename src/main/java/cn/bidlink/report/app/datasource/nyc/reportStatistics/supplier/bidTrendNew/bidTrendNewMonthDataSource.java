package cn.bidlink.report.app.datasource.nyc.reportStatistics.supplier.bidTrendNew;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:46
 * @description :cn.bidlink.nyc.report.statistics.report.supplier.bidTrendNewMonth
 */

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName bidTrendNewMonthDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/7 14:46
 * @Version 1.0
 **/
public class bidTrendNewMonthDataSource extends AbstractBaseTableData {
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
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}