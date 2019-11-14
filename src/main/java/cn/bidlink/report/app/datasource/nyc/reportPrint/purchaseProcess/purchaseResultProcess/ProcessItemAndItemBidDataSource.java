package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchaseResultProcess;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  13:18
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.ProcessItemAndItemBid
 */

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProcessItemAndItemBid
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 13:18
 * @Version 1.0
 **/
public class ProcessItemAndItemBidDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(ProcessItemAndItemBidDataSource.class);
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("bs"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "采购品编码" ,"采购品名称","单位","规格","数量","报价","成交价","分标数量","节约额","分标说明","备注","supplier_id"
                ,"用途","使用单位","技术参数/材质","申报单位","申报人电话","中标状态","平均价格", "need_time",
                "data1","data2","data3","data4","data5","data6","data7","data8","data9","data10",
                "data11","data12","data13","data14","data15","contractName","bid_status"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessService.class);
        //获取报表查询的参数
        String projectId = String.valueOf(param.get("projectId"));
        String bs = String.valueOf(param.get("bs"));
        String companyId = String.valueOf(param.get("companyId"));
        ServiceResult<List<Map<String, Object>>> listServiceResult = new ServiceResult<>();
        if(bs != null && ("2").equals(bs)){
            listServiceResult = dataService.getPurchaseResultProcessDsThr(projectId, companyId);
            if (!listServiceResult.getSuccess()){
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessService.getPurchaseResultProcessDsThr",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
        }else {
            listServiceResult = dataService.getPurchaseResultProcessDsTwo(projectId, companyId);
            if (!listServiceResult.getSuccess()){
                log.error("{}调用{}时发生未知异常,error Message:{}", "DubboPurchaseResultProcessService.getPurchaseResultProcessDsTwo",
                        "serviceResult", listServiceResult.getCode() + "_" + listServiceResult.getMessage());
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
        }
        List<Map<String, Object>> result = listServiceResult.getResult();
        return result;
    }
}