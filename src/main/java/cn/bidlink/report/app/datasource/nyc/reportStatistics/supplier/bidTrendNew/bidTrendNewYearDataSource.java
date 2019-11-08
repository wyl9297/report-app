package cn.bidlink.report.app.datasource.nyc.reportStatistics.supplier.bidTrendNew;
/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/7  14:47
 * @description :cn.bidlink.nyc.report.statistics.report.supplier.bidTrendNewYear
 */

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName bidTrendNewYearDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/7 14:47
 * @Version 1.0
 **/
public class bidTrendNewYearDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("supplierId"),
                new Parameter("begin"),
                new Parameter("end"),
                new Parameter("comp_id")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "year" ,"ofall","t2","already","t3","failed","t4"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}