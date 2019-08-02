package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SeperateBiddingItemDataSource
 * @Author Administrator
 * @Description //招标项目分项报价表采购品数据源
 * @Date 2019/5/30 14:18
 * @Version 1.0
 **/
public class SeperateBiddingItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("subProjectId"),
                new Parameter("supplierIds")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "code" , "name" , "unitName" , "num" , "marketPrice" , "purchaseAmount" , "totalPlanPrice" , "techParameters" , "sumTotalPlanPrice","itemId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        BidSupplierItemRestService bidSupplierItemRestService = dataServiceFactory.getDataService(BidSupplierItemRestService.class);
        //获取报表查询的参数
        Long subProjectId = Long.valueOf(param.get("subProjectId"));
        String supplierIds = param.get("supplierIds");
        //查询数据并返回
        Map<String, Object> dataMap = bidSupplierItemRestService.getBidItemSupplierColumnList(subProjectId,supplierIds);
        List<Map<String,String>> dataList = (List)dataMap.get("dataList");
        BigDecimal bigDecimal = new BigDecimal(0);
        for (Map<String, String> map : dataList) {
            String totalPlanPrice = map.get("totalPlanPrice");
            if(StringUtils.isNotEmpty(totalPlanPrice)){
                bigDecimal = bigDecimal.add(new BigDecimal(totalPlanPrice.replace(",","")));
            }
        }
        dataList.get(0).put("sumTotalPlanPrice",bigDecimal.toString());
        return dataList;
    }
}