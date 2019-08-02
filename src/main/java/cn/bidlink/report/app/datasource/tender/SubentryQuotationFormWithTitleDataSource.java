package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.procurement.bidding.cloud.service.BidSubProjectRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SubentryQuotationFormWithPurchasesDataSource
 * @Author 从尧
 * @Description 分项报价表[分项key,title] 程序集
 * @Date 2019-06-26 14:43
 * @Version 1.0
 **/
public class SubentryQuotationFormWithTitleDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"key", "title"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        BidSubProjectRestService bidSubProjectRestService = dataServiceFactory.getDataService(BidSubProjectRestService.class);
        //获取报表查询的参数
        String subProjectId = param.get("projectId");
        //查询数据并返回
        List<Map<String, Object>> quoteItem = bidSubProjectRestService.findQuoteItem(Long.valueOf(subProjectId));
        return quoteItem;
    }
}
