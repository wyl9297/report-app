package cn.bidlink.report.app.datasource.demo;

import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectItemSupplierQuoteDto;
import cn.bidlink.procurement.purchase.cloud.service.ProjectRestService;
import cn.bidlink.procurement.purchase.cloud.service.QuotedPriceRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName QuotedPriceRestService
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/5/20 14:46
 * @Version 1.0
 **/
public class PurchaseItemDimensionDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("param1"),
                new Parameter("param2"),
                new Parameter("...")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"column1", "column2", "..."};
    }

    @Override
    public List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        QuotedPriceRestService quotedPriceRestService = dataServiceFactory.getDataService(QuotedPriceRestService.class);
        ProjectRestService projectRestService = dataServiceFactory.getDataService(ProjectRestService.class);
        Map<String, Object> projectExt = projectRestService.findProjectExt(305995674334265966L, 11113174124L);
        //查询数据并返回
        TableData<ProjectItemSupplierQuoteDto> projectItemSupplierQuoteDtoTableData
                = quotedPriceRestService.findItemSupplierQuoteInfo(11113174124L,305995674334265966L,true,0,20);
        List<ProjectItemSupplierQuoteDto> list = projectItemSupplierQuoteDtoTableData.getTableData();
        return list;
    }

}
