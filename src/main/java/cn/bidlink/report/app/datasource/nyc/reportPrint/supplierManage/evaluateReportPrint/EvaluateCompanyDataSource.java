package cn.bidlink.report.app.datasource.nyc.reportPrint.supplierManage.evaluateReportPrint;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateCompanyDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("beginTime"),
                new Parameter("endTime"),
                new Parameter("reportId"),
                new Parameter("chooseType"),
                new Parameter("catalogId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id","supplier_id","company_name","report_supplier_level","supplier_status",
                "corp_status","supplier_trade_evaluation","priceNum","orderNum","evaluationNum","totalMoney","rate","isCore","supplier_status_name"};
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
