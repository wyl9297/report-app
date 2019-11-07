package cn.bidlink.report.app.datasource.nyc.reportStatistics.contract.totalContractInfo;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractTotalInfoDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("compId"),
                new Parameter("create_time_start"),
                new Parameter("create_time_end"),
                new Parameter("sign_time_start"),
                new Parameter("sign_time_end"),
                new Parameter("keyWords"),
                new Parameter("keyValue"),
                new Parameter("filiale"),
                new Parameter("agreement")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"create_time" ,"create_user_name","code","serial_code","contract_name" ,"total_price","status","department_code","dept_name" ,
                "purchaser_company_sign_name","supplier_company_sign_name","sign_time","classification_code" ,"classification_name","project_insdustry_code",
                "project_insdustry_name","agreement"};
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
