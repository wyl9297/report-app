package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectProgressStatistics;

import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectProgressStatisticsADataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> listResult = new ArrayList<>();
          Map map = new HashMap();
          map.put("1", "项目建档");
          map.put("2", "邀请报价");
          map.put("3", "开标大厅");
          map.put("4","采购结果处理");
          map.put("5","项目归档");
        listResult.add(map);
        return listResult;
    }

    @Override
    protected Parameter[] getParameter() {
        //saas源码
        return new Parameter[]{new Parameter("项目建档"),new Parameter("邀请报价"),new Parameter("开标大厅"),
                new Parameter("采购结果处理"),new Parameter("项目归档")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id", "name" };
    }
}
