package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderDetail;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.order.DubboOrderDetailService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->订单信息和收货人信息(基础数据集)
 * @Date 2019/6/28
 *
 */
public class OrderDetailADataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(OrderDetailADataSource.class);

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

        DubboOrderDetailService orderDetailService = dataServiceFactory.getDataService(DubboOrderDetailService.class);
        String orderId = param.get("orderId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, orderId, companyId);

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = orderDetailService.orderDetailA(orderId, companyId);
            if (!listServiceResult.getSuccess()) {
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboOrderDetailService.orderDetailA",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        } else{
            log.error("{}数据源所需必要参数不全", log.getName());
            return null;
        }

    }
}