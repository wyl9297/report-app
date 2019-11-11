package cn.bidlink.report.app.datasource.nyc.reportStatistics.purchase.projectProgressStatisticsBps;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectProgressStatisticsBpsADataSource extends AbstractBaseTableData {
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
//        Object[][] datas = { { "1", "项目建档" },{ "2","资格预审" }, { "3", "招标公告" }, { "4", "项目开标" }, { "5", "项目评标" }
//                ,{ "6","项目定标" },{ "7","项目归档" },{ "8","已归档" },{ "9","已撤项" }};
        return new Parameter[]{new Parameter("项目建档"),new Parameter("资格预审"),new Parameter("招标公告"),
                new Parameter("项目开标"),new Parameter("项目评标"),new Parameter("项目定标"),new Parameter("项目归档"),
                new Parameter("已归档"),new Parameter("已撤项")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id", "name" };
    }

}
