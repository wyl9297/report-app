package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderGoodsDetails;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.order.DubboOrderGoodsDetalisService;
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
public class OrderGoodsDetalisADataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(OrderGoodsDetalisADataSource.class);

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

        DubboOrderGoodsDetalisService orderGoodsDetalisService = dataServiceFactory.getDataService(DubboOrderGoodsDetalisService.class);
        String goodsId = param.get("goodsId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, goodsId, companyId);

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = orderGoodsDetalisService.orderGoodsDetalisA(goodsId, companyId);
            if (!listServiceResult.getSuccess()) {
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