package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_directory_supplier;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractColumnPositionTableData;
import cn.bidlink.report.app.datasource.nyc.ParamUtils;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.statistics.report.service.service.report_print.purchase.DubboPurchaseResultProcessDirectorySupplierService;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DirectoryVerticalBDataSource extends AbstractColumnPositionTableData {
    private static Logger log = LoggerFactory.getLogger(DirectoryVerticalBDataSource.class);
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        DubboPurchaseResultProcessDirectorySupplierService dataService = dataServiceFactory.getDataService(DubboPurchaseResultProcessDirectorySupplierService.class);
        String projectId = String.valueOf(param.get("projectId"));
        String companyId = String.valueOf(param.get("companyId"));
        List<Map<String, Object>> result = new ArrayList<>();
        //校验是否缺失必填参数
        boolean sel = ParamUtils.sel(param, "projectId", "companyId");
        if (sel == Boolean.FALSE){
            log.error("{}数据源所需必要参数不全", log.getName());
        }else {
            ServiceResult<List<Map<String, Object>>> listServiceResult = dataService.directoryVerticalB(projectId, companyId);
            if (!listServiceResult.getSuccess()) {
                throw new RuntimeException("err_code:" + listServiceResult.getCode() + ",err_msg:" + listServiceResult.getMessage());
            }
            result = listServiceResult.getResult();
        }
        return result;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "supplier_id" ,"supplier_name","supplier_type","mobile"};
    }
}

