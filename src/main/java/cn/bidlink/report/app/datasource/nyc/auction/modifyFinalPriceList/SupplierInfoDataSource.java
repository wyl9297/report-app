package cn.bidlink.report.app.datasource.nyc.auction.modifyFinalPriceList;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.*;

public class SupplierInfoDataSource extends AbstractBaseTableData {

    private static String[]  column = {"supplier_name", "link_man", "link_phone", "real_price", "final_price","RMB"};

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("supplierId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"supplier_name", "link_man", "link_phone", "real_price", "final_price","RMB"};
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
