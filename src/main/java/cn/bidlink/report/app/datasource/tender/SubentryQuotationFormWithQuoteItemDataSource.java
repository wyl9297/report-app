package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SubentryQuotationFormWithPurchasesDataSource
 * @Author 从尧
 * @Description 分项报价表[分项结果] 程序集
 * @Date 2019-06-26 14:43
 * @Version 1.0
 **/
public class SubentryQuotationFormWithQuoteItemDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter(  "quoteTurn" )
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"key", "value" , "supplierId" , "bidProjectItemId" , "code" , "name" , "directoryId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        TenderProxyService tenderProxyService = dataServiceFactory.getDataService(TenderProxyService.class);
        //获取报表查询的参数
        String subProjectId = param.get("projectId");
        String quoteTurn = param.get("quoteTurn");
        boolean isNumber = StringUtils.isNumeric(quoteTurn);
        if(!StringUtils.isEmpty(quoteTurn) && isNumber){
            // 如果quoteTurn 不为null，并且是数字 ， 多轮报价
            List<Map<String, Object>> multipleQuoteItem = tenderProxyService.findMultipleQuoteItem(Long.parseLong(subProjectId), Integer.valueOf(quoteTurn));
            return multipleQuoteItem;
        }else{
            //展示所有供应商报价信息
            List<Map<String, Object>> quoteItem = tenderProxyService.findQuoteItem(Long.parseLong(subProjectId));
            return quoteItem;
        }
    }
}
