package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectProgressStatistics;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectProgressStatisticsADataSource extends AbstractBaseTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> listResult = new ArrayList<>();
        Parameter[] parameter = this.getParameter();
        for (int i=0; i<parameter.length; i++){
            Map<String, Object> mapResult = new HashMap<>();
            mapResult.put("id", String.valueOf(i+1));
            mapResult.put("name", parameter[i]);
            listResult.add(mapResult);
        }
        return listResult;
    }

    @Override
    protected Parameter[] getParameter() {
        //saas源码
//        Object[][] datas = { { "1", "项目建档" }, { "2", "邀请报价" }, { "3", "开标大厅" }
//                ,{ "4","采购结果处理" },{ "5","项目归档" }};
        return new Parameter[]{new Parameter("项目建档"),new Parameter("邀请报价"),new Parameter("开标大厅"),
                new Parameter("采购结果处理"),new Parameter("项目归档")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id", "name" };
    }
}
