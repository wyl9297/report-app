package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_sanyou;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;

import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessSanyouService;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ProcessItemAndItemBidDataSource extends AbstractColumnPositionTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessSanyouService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessSanyouService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String bs = String.valueOf(param.get("bs"));
        String companyId = String.valueOf(param.get("companyId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = new ServiceResult<>();
        if(bs != null && ("2").equals(bs)){
            listServiceResult = dataService.getPurchaseResultProcessDsThr(projectId, companyId);
        }else {
            listServiceResult = dataService.getPurchaseResultProcessDsTwo(projectId, companyId);
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("bs"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "采购品编码" ,"采购品名称","单位","规格","数量","报价","成交价","分标数量","节约额","分标说明","备注","supplier_id"
                ,"用途","使用单位","技术参数/材质","申报单位","申报人电话","中标状态","平均价格", "need_time",
                "data1","data2","data3","data4","data5","data6","data7","data8","data9","data10",
                "data11","data12","data13","data14","data15","contractName","bid_status"};
    }
}
