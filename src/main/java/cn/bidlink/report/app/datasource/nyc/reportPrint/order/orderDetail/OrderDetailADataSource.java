package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderDetail;

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
public class OrderDetailADataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("orderId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id" ,"order_code","order_reason","supplier_id","supplier_name","project_id","project_name","data_source","create_type","order_source","purchaser_id","purchaser_name","order_status","order_remark",
                "create_user_id","create_user_name","create_date","order_linkman","order_publish_date","supplier_confim_user_name","supplier_confim_user_id","supplier_confim_date",
                "supplier_reject_reason","update_user_id","update_user_name","update_date","cancel_user_id","cancel_user_name","cancel_date","end_user_id","end_user_name","end_date","supplier_project_bid_id",
                "order_flag","plan_id","angin_generate_goods","department_code","approve_status","process_instance_id","erp_order_code","custom_def_id","order_finish_time","merchandiser_name","order_source_text","order_status_text"};
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