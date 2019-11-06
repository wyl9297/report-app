package cn.bidlink.report.app.datasource.nyc.reportPrint.auction;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:liuqi@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @Date 2019/11/05
 *
 */
public class ArchivedAuctionSupplierUnProjectDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("supplierId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "directoryName" , "directoryCode" , "planAmount" , "currency" , "divideRate" , "preTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

//        OrderListResultService orderListResultService = dataServiceFactory.getDataService(OrderListResultService.class);
//        String projectId = param.get("projectId");
//        String companyId = param.get("companyId");
//        String supplierId = param.get("supplierId");
//        OrderDetailDto orderDetail = orderListResultService.getOrderDetail(projectId, companyId, supplierId);
//        OrderDetailDto orderDetailDto = new OrderDetailDto();
//        orderDetailDto.setAddressName("12");
//        orderDetailDto.setAddressPhone("34234");
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("directoryName","炉石传说");
        resultMap.put("directoryCode","c325545111211");
        resultMap.put("planAmount",488);
        resultMap.put("currency","人民币");
        resultMap.put("divideRate","%12");
        resultMap.put("preTotalPrice",328);
        resultList.add(resultMap);

        return resultList;
    }
}