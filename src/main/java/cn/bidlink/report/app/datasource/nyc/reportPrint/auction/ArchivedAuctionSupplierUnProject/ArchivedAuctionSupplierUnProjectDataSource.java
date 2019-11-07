package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.ArchivedAuctionSupplierUnProject;

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
                new Parameter("purchaserId"),
                new Parameter("supplierId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"采购品名称","采购品编号","采购量","币种","分标比例","单项总价"};
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
        resultMap.put("采购品名称","炉石传说");
        resultMap.put("采购品编号","c325545111211");
        resultMap.put("采购量","488");
        resultMap.put("币种","人民币");
        resultMap.put("分标比例","%12");
        resultMap.put("单项总价","328");
        resultList.add(resultMap);

        return resultList;
    }
}