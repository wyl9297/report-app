package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.report.server.service.PurchaseStatisticalDealDetailService;
import com.fr.base.Parameter;
import com.fr.third.org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:查询采购成交明细统计列表数据集
 * @Date 2019/9/12
 *
 */
public class PurchaseStatisticalClinchDealDetailDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("departmentName"),
                new Parameter("catalogNamePath"),
                new Parameter("startCreateTime"),
                new Parameter("endCreateTime"),
                new Parameter("startArchiveTime"),
                new Parameter("endArchiveTime")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "projectId", "companyId", "supplierId", "directoryId", "projectName", "departmentName", "catalogNamePath", "createUserName", "createTime", "archiveTime", "supplierName", "spec", "directoryName", "directoryCode", "unitName", "useDept", "bidPrice", "dealPrice", "dealAmount", "dealTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseStatisticalDealDetailService purchaseStatisticalDealDetailService = dataServiceFactory.getDataService(PurchaseStatisticalDealDetailService.class);
        PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
        purchaseNewProjectWideTable.setCompanyId(Long.valueOf(param.get("companyId")));
        purchaseNewProjectWideTable.setDepartmentName(param.get("departmentName"));
        purchaseNewProjectWideTable.setCatalogNamePath(param.get("catalogNamePath"));
        try {
            purchaseNewProjectWideTable.setStartCreateTime(StringUtils.isNotEmpty(param.get("startCreateTime"))?DateUtils.parseDate(param.get("startCreateTime"), "yyyy-MM-dd HH:mm:ss"):null);
            purchaseNewProjectWideTable.setEndCreateTime(StringUtils.isNotEmpty(param.get("endCreateTime"))?DateUtils.parseDate(param.get("endCreateTime"), "yyyy-MM-dd HH:mm:ss"):null);
            purchaseNewProjectWideTable.setStartArchiveTime(StringUtils.isNotEmpty(param.get("startArchiveTime"))?DateUtils.parseDate(param.get("startArchiveTime"), "yyyy-MM-dd HH:mm:ss"):null);
            purchaseNewProjectWideTable.setEndArchiveTime(StringUtils.isNotEmpty(param.get("endArchiveTime"))?DateUtils.parseDate(param.get("endArchiveTime"), "yyyy-MM-dd HH:mm:ss"):null);
        }catch (Exception e){
            e.printStackTrace();
        }
        ServiceResult<List<PurchaseNewProjectWideTable>> purchaseDealStatistical = purchaseStatisticalDealDetailService.findPurchaseStatisticalDealDetail(purchaseNewProjectWideTable);
        List<PurchaseNewProjectWideTable> result = purchaseDealStatistical.getResult();
        if (result != null && result.size()>0){
            return result;
        }
        List<PurchaseNewProjectWideTable> resultNull = new ArrayList<>();
        return resultNull;
    }
}