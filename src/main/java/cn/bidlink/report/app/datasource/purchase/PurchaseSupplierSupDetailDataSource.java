
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierItemQuoteVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;
/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目->报价一览表->采购品维度->供应商数据集
 * @Date 2019/5/21
 *
 */
public class PurchaseSupplierSupDetailDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("projectId"),
                new Parameter("quoteResult"),
                new Parameter("pageNum"),
                new Parameter("pageSize")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id" ,"code" ,"name" ,"purchaseAmount" ,"quoteAmount", "supplierName", "supplierType",
                "quoteResult", "quoteStatus", "quoteValidNum", "quoteUnitPrice", "quoteTotalPrice", "quoteComment",
                "quoteTime", "isUploadQuoteFile", "businessId", "inviteFlag" , "supplierSource", "quoteItem", "sourceId",
                "companyId", "projectId" , "supplierProjectId", "supplierId", "projectItemId", "lowestFlag"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        PurchaseProxyService quotedPriceRestService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        String pageNum = param.get("pageNum");
        String pageSize = param.get("pageSize");
        List<ProjectSupplierItemQuoteVo> list = quotedPriceRestService.findItemSupplierQuoteInfo(Long.valueOf(param.get("companyId")), Long.valueOf(param.get("projectId")), true, null, null);
        return list;
    }

}
