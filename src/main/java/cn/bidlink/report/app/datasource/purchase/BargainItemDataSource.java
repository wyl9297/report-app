package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.purchase.cloud.dto.BargainAllListVo;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierDimensionItemDetailVO;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BargainItemDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/7/24 14:21
 * @Version 1.0
 **/
public class BargainItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName" , "itemName" , "spec" , "purchaseAmount" , "unitName" , "quoteUnitPrice" , "supplierId",
                "afterPrice" , "purchaseRemark" , "supplierItemId" , "needConfirm" , "bargainTime" , "supplierConfirmTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = UserContext.getCompanyId();
        List<Map<String, String>> bargainItemList = purchaseProxyService.getBargainItemList(projectId, companyId);
        return bargainItemList;
    }

}
