package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderGoodsDetails;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.order.DubboOrderGoodsDetalisService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->订单信息和收货人信息(基础数据集)
 * @Date 2019/6/28
 *
 */
public class OrderGoodsDetalisBDataSource extends AbstractColumnPositionTableData {

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

        DubboOrderGoodsDetalisService orderGoodsDetalisService = dataServiceFactory.getDataService(DubboOrderGoodsDetalisService.class);
        String goodsId = param.get("goodsId");
        String companyId = param.get("companyId");

        boolean panduan = ParamUtils.panduan(param, goodsId, companyId);

        if (panduan) {
            ServiceResult<List<Map<String, Object>>> listServiceResult = orderGoodsDetalisService.orderGoodsDetalisB(goodsId, companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            List<Map<String, Object>> result = listServiceResult.getResult();
            return result;
        }
        return null;
    }
}