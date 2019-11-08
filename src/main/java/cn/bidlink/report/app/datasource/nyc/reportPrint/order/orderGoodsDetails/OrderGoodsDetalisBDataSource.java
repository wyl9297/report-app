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
public class OrderGoodsDetalisBDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("goodsId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id" ,"order_goods_item_id","order_goods_id","purchase_id","purchase_name","order_id","item_name","unit_price","item_code", "item_spec","item_unit","item_number","transportation_price",
                "tax_rate","product_price","discount","create_user_id","create_user_name","create_date","remark","sign_number","send_number","temp1","temp2","temp3","temp4","temp5","temp6","temp7",
                "temp8","temp9","temp10","usedepart","tech_parameters","purpose","actual_value","actual_price","bpo_plan_item_id","erp_plan_code","applied_enterprise","applied_person_and_phone",
                "check_status","erp_code","erp_item_id","purchase_order_number","barcode","plan_type","refund_number","order_price"};
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