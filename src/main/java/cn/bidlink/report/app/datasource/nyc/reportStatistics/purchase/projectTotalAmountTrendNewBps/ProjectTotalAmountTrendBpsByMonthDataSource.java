package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectTotalAmountTrendNewBps;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:43
 * @description :cn.bidlink.nyc.report.statistics.report.totalAmountTrendsStatistics.ProjectTotalAmountTrendBpsByMonth
 */

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProjectTotalAmountTrendBpsByMonthDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/7 14:43
 * @Version 1.0
 **/
public class ProjectTotalAmountTrendBpsByMonthDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("begin"),
                new Parameter("end")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "month" ,"groupType","total","num"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}