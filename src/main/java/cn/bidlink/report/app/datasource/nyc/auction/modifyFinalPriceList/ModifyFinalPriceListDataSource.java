package cn.bidlink.report.app.datasource.nyc.auction.modifyFinalPriceList;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.*;

public class ModifyFinalPriceListDataSource extends AbstractBaseTableData {

    private static String[] column = {"directory_name", "directory_code", "spec", "tech_prameters", "unit_name", "plan_amount", "RMB"};

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"directory_name", "directory_code", "spec", "tech_prameters", "unit_name", "plan_amount", "RMB"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> regust = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < column.length - 1; i++) {
                Random random = new Random();
                map.put(column[i], i + random.nextInt());
            }
            regust.add(map);
        }

        return regust;
    }


}
