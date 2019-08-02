package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierItemDealDto;
import cn.bidlink.procurement.purchase.cloud.dto.ResultTabListVo;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionPricingPurchasesDimDataSource
 * @Author 从尧
 * @Description 采购项目 成交定价 采购品维度
 * @Date 2019-07-10 11:29
 * @Version 1.0
 **/
public class TransactionPricingPurchasesDimDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("showUnConfirmed"),
                new Parameter("pageNum"),
                new Parameter("pageSize")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "techParameters", "purchaseAmount", "spec", "needTime", "appliedPersonAndPhone", "unitName",
                "marketPrice",  "appliedEnterprise", "purpose","comment","useDept" , "projectItemId" , "dealTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
         //fixme 以下是demo示例 老司机请直接删除
        //获取服务
        DealPriceRestService dealPriceRestService = dataServiceFactory.getDataService(DealPriceRestService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        String companyId = param.get("companyId");
        String showUnConfirmed = param.get("showUnConfirmed");
        String pageNum = param.get("pageNum");
        String pageSize = param.get("pageSize");
        //查询数据并返回
        ResultTabListVo<ProjectSupplierItemDealDto> processedDealList = dealPriceRestService.findProcessedDealList(Long.parseLong(projectId), Long.parseLong(companyId), Boolean.parseBoolean(showUnConfirmed), 0, 100);
        List<ProjectSupplierItemDealDto> tableData = processedDealList.getTableData();
        return tableData;
    }
}
