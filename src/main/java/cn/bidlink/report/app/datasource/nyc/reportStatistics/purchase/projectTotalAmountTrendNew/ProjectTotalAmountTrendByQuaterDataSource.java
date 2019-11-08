package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectTotalAmountTrendNew;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:37
 * @description :cn.bidlink.nyc.report.statistics.report.totalAmountTrendsStatistics.ProjectTotalAmountTrendByQuater
 */
public class ProjectTotalAmountTrendByQuaterDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("begin"),
                new Parameter("end"),
                new Parameter("deptCode")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "quater" ,"groupType","total","num"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}