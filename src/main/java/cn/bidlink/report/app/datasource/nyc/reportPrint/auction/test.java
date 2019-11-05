package cn.bidlink.report.app.datasource.nyc.reportPrint.auction;

import cn.bidlink.procurement.order.cloud.dto.OrderDetailDto;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->订单信息和收货人信息(基础数据集)
 * @Date 2019/6/28
 *
 */
public class test extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("orderId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "orderCode" , "createUserName" , "createTime" , "orderName" , "orderSource" , "projectName" , "orderTotalPrice" ,"orderStatus" , "arriveTime" , "addressUserName","addressName","addressPhone","supplierName","supplierUserName","supplierPhone","supplierLoginName","projectType","hasDeliveryCargo"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

//        OrderListResultService orderListResultService = dataServiceFactory.getDataService(OrderListResultService.class);
//        Long mainId = Long.valueOf(param.get("orderId"));
//        Long companyId = Long.valueOf(param.get("companyId"));
       // OrderDetailDto orderDetail = orderListResultService.getOrderDetail(mainId, companyId);
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setAddressName("12");
        orderDetailDto.setAddressPhone("34234");


        ArrayList<OrderDetailDto> orderDetailData = new ArrayList<>();
        orderDetailData.add(orderDetailDto);

        return orderDetailData;
    }
}