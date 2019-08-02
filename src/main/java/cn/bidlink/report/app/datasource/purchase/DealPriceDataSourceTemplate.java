package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierDimensionDealResultVO;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:20 2019-05-08
 * @Description : 采购项目成交结果模板  结果集
 */
public class DealPriceDataSourceTemplate extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("showNoDeal")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id","businessId","supplierId","supplierName","quoteTime","quoteTotalPrice","dealTotalPrice","quoteFileName",
                "quoteFileDownLoadUrl","dealStatus","dealStatusStr","priceSaving","priceSavingRatio","marketTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        DealPriceSupplierDimensionRestService  dealPriceSupplierDimensionRestService = dataServiceFactory.getDataService(DealPriceSupplierDimensionRestService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        String showNoDeal = param.get("showNoDeal");
        //查询数据并返回
        List<ProjectSupplierDimensionDealResultVO> supplierDealResultList = dealPriceSupplierDimensionRestService.findSupplierDealResultList(Long.valueOf(projectId), Long.valueOf(companyId), Boolean.valueOf(showNoDeal));
        return supplierDealResultList;
    }
}
