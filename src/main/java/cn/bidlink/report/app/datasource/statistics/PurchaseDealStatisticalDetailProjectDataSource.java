package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.report.server.service.PurchaseDealStatisticalService;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:查询采购成交采购品统计  查询采购成交采购品统计钻取项目详情数据集
 * @Date 2019/9/10
 *
 */
public class PurchaseDealStatisticalDetailProjectDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("directoryId"),
                new Parameter("supplierName")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "projectId", "companyId", "directoryId", "projectName", "publishBidResultTime", "createSource", "createUserName", "departmentName", "supplierId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseDealStatisticalService purchaseDealStatisticalService = dataServiceFactory.getDataService(PurchaseDealStatisticalService.class);
        PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
        purchaseNewProjectWideTable.setDirectoryId(Long.valueOf(param.get("directoryId")));
        purchaseNewProjectWideTable.setSupplierName(param.get("supplierName"));
        ServiceResult<List<PurchaseNewProjectWideTable>> findPurchaseDealStatisticalDetailProject = purchaseDealStatisticalService.findPurchaseDealStatisticalDetailProject(purchaseNewProjectWideTable);
        List<PurchaseNewProjectWideTable> result = findPurchaseDealStatisticalDetailProject.getResult();
        if (result != null && result.size()>0){
            return result;
        }
        List<PurchaseNewProjectWideTable> resultNull = new ArrayList<>();
        return resultNull;
    }
}