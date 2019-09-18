package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @ClassName DashboardSupplierDealPriceTrend
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/9/17 19:50
 * @Version 1.0
 **/
public class DashboardSupplierDealPriceTrend extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("supplierId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierId", "price", "month"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        Long supplierId = Long.valueOf(StringUtils.isEmpty(param.get("supplierId")) ? "0" : param.get("supplierId"));
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 1 ; i <= 12 ; i ++){
            Map<String,Object> map = new HashMap<>();
            map.put("supplierId",supplierId);
            map.put("month",i);
            int max= 30;
            int min = 10;
            map.put("price",new Random().nextInt(max-min+1)+min);
            list.add(map);
        }
        return list;
    }

}
