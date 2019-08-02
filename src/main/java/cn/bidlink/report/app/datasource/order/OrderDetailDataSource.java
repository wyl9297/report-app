package cn.bidlink.report.app.datasource.order;

import cn.bidlink.procurement.order.cloud.dto.OrderDetailDto;
import cn.bidlink.procurement.order.cloud.service.OrderListResultService;
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
public class OrderDetailDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("mainId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "orderCode" , "createUserName" , "createTime" , "orderName" , "orderSource" , "projectName" , "orderTotalPrice" ,"orderStatus" , "arriveTime" , "addressUserName","addressName","addressPhone","supplierName","supplierUserName","supplierPhone","supplierLoginName"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        OrderListResultService orderListResultService = dataServiceFactory.getDataService(OrderListResultService.class);
        Long mainId = Long.valueOf(param.get("mainId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        OrderDetailDto orderDetail = orderListResultService.getOrderDetail(mainId, companyId);

        ArrayList<OrderDetailDto> orderDetailData = new ArrayList<>();
        orderDetailData.add(orderDetail);

        return orderDetailData;
    }
}