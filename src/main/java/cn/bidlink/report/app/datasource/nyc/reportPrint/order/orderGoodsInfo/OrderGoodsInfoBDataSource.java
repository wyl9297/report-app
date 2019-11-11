package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderGoodsInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.order.DubboOrderGoodsInfoService;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->订单信息和收货人信息(基础数据集)
 * @Date 2019/6/28
 *
 */
public class OrderGoodsInfoBDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("goodsId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"order_code" ,"order_status","purchaser_name","supplier_name","goods_code","goods_state","arrive_time","take_goods_time",
                "organ_name","name","phone","address","take_user_name","take_goods_time","reject_user_name","reject_time","create_user_name","create_date",
                "update_user_name","update_date","dept","mobile","main_user_mobile","settlement" ,"taxRate","goods_remark"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboOrderGoodsInfoService orderGoodsInfoService = dataServiceFactory.getDataService(DubboOrderGoodsInfoService.class);
        String orderId = param.get("goodsId");
        String companyId = param.get("companyId");

        if(StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(companyId)){
            ServiceResult<List<Map<String, Object>>> listServiceResult = orderGoodsInfoService.orderGoodsInfoB(orderId, companyId);
            return listServiceResult.getResult();
        }
        return null;

//        List<Map<String, Object>> resultList = new ArrayList<>();
//        Map<String, Object> resultMap = new HashMap<>();
//
//        for (String s : getColumn()) {
//            resultMap.put(s,"77777");
//        }
//        resultList.add(resultMap);
//
//        return resultList;
    }
}