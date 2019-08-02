
package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectItemSupplierQuoteDto;
import cn.bidlink.procurement.purchase.cloud.service.QuotedPriceRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;
/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目->报价一览表->采购品维度->采购品数据集
 * @Date 2019/5/21
 *
 */
public class PurchaseSupplierPurDetailDataSource extends AbstractBaseTableData {

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
        return new String[]{"id" ,"projectId" ,"directoryId" ,"companyId" ,"name", "code", "spec", "techParameters", "purchaseAmount", "unitName", "marketPrice", "appliedPersonAndPhone", "appliedEnterprise", "useDept", "purpose", "needTime", "comment"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        QuotedPriceRestService quotedPriceRestService = dataServiceFactory.getDataService(QuotedPriceRestService.class);
        TableData<ProjectItemSupplierQuoteDto> itemSupplierQuoteInfo = quotedPriceRestService.findItemSupplierQuoteInfo(Long.valueOf(param.get("companyId")), Long.valueOf(param.get("projectId")), true, null, null);
        List<ProjectItemSupplierQuoteDto> projectItemSupplierQuoteDto = itemSupplierQuoteInfo.getTableData();

        return projectItemSupplierQuoteDto;
    }

}
