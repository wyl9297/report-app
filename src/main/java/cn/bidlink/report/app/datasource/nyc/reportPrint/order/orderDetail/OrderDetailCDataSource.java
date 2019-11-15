package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderDetail;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.order.DubboOrderDetailService;
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
public class OrderDetailCDataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("orderId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"og.id" ,"p.id","item_code","item_name","item_spec","item_unit","unit_price","send_number","item_number",
                "order_goods_item_id","remark","usedepart","tech_parameters","purpose","refund_number"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboOrderDetailService orderDetailService = dataServiceFactory.getDataService(DubboOrderDetailService.class);
        String orderId = param.get("orderId");
        String companyId = param.get("companyId");

        if(StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(companyId)){
            ServiceResult<List<Map<String, Object>>> listServiceResult = orderDetailService.orderDetailC(orderId, companyId);
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