package cn.bidlink.report.app.datasource.nyc.reportPrint.order.orderGoodsInfo;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
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
public class OrderGoodsInfoADataSource extends AbstractColumnPositionTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("goodsId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"item_code" ,"item_name","item_spec","item_unit","applied_enterprise","applied_person_and_phone","unit_price","item_number","sign_number","send_number","remark","refund_number","usedepart"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboOrderGoodsInfoService orderGoodsInfoService = dataServiceFactory.getDataService(DubboOrderGoodsInfoService.class);
        String goodsId = param.get("goodsId");
        String companyId = param.get("companyId");

        if(StringUtils.isNotEmpty(goodsId) && StringUtils.isNotEmpty(companyId)){
            ServiceResult<List<Map<String, Object>>> listServiceResult = orderGoodsInfoService.orderGoodsInfoA(goodsId, companyId);
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