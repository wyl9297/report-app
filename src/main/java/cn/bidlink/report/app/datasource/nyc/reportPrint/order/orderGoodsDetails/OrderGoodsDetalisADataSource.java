package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderGoodsDetails;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->订单信息和收货人信息(基础数据集)
 * @Date 2019/6/28
 *
 */
public class OrderGoodsDetalisADataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("goodsId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id" ,"order_item_id","goods_code","order_id","address_id","goods_state","arrive_time","reason","send_goods_time",
                "send_goods_method","take_goods_time","supplier_user_id","supplier_user_name","supplier_phone","take_user_id","take_user_name",
                "reject_user_id","reject_user_name","project_time","update_user_id","update_user_name","update_time","goods_mess","bill_id",
                "bill_code","bill_goods_mess","sure_bill_money","serial_number","please_money_status","is_auto_evaluate","erp_order_itemId",
                "delivery_date","part_sign","rec_org","settlement","taxRate","purchaser_id","purchaser_name","order_publish_date","order_code",
                "address","name","dept","phone","create_user_name","create_date","supplier_name","main_user_mobile","bank_name","bank_account","order_status_text","goods_status_text","goods_remark"};
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

        for (String s : getColumn()) {
            resultMap.put(s,"77777");
        }
        resultList.add(resultMap);

        return resultList;
    }
}