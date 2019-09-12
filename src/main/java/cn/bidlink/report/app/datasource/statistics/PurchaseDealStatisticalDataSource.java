package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.report.server.service.PurchaseDealStatisticalService;
import com.fr.base.Parameter;
import com.fr.third.org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:查询采购成交采购品统计  采购品列表数据集
 * @Date 2019/9/10
 *
 */
public class PurchaseDealStatisticalDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("directoryName"),
                new Parameter("directoryCode"),
                new Parameter("spec"),
                new Parameter("catalogNamePath"),
                new Parameter("startCreateTime"),
                new Parameter("endCreateTime")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "projectId", "companyId", "directoryId", "dealAmount", "directoryName", "directoryCode", "spec", "techParameters", "unitName", "recentDealTime", "recentDealPrice", "projectCount", "totalBidPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseDealStatisticalService purchaseDealStatisticalService = dataServiceFactory.getDataService(PurchaseDealStatisticalService.class);
        PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
        purchaseNewProjectWideTable.setCompanyId(Long.valueOf(param.get("companyId")));
        purchaseNewProjectWideTable.setDirectoryName(param.get("directoryName"));
        purchaseNewProjectWideTable.setDirectoryCode(param.get("directoryCode"));
        purchaseNewProjectWideTable.setSpec(param.get("spec"));
        purchaseNewProjectWideTable.setCatalogNamePath(param.get("catalogNamePath"));

        try {
            purchaseNewProjectWideTable.setStartCreateTime(StringUtils.isNotEmpty(param.get("startCreateTime"))?DateUtils.parseDate(param.get("startCreateTime"), "yyyy-MM-dd HH:mm:ss"):null);
            purchaseNewProjectWideTable.setEndCreateTime(StringUtils.isNotEmpty(param.get("endCreateTime"))?DateUtils.parseDate(param.get("endCreateTime"), "yyyy-MM-dd HH:mm:ss"):null);
        }catch (Exception e){
            e.printStackTrace();
        }

        ServiceResult<List<PurchaseNewProjectWideTable>> purchaseDealStatistical = purchaseDealStatisticalService.findPurchaseDealStatistical(purchaseNewProjectWideTable);

        List<PurchaseNewProjectWideTable> result = purchaseDealStatistical.getResult();
        if (result != null && result.size()>0){
            return result;
        }
        List<PurchaseNewProjectWideTable> resultNull = new ArrayList<>();
        return resultNull;
    }
}