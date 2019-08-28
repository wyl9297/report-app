package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName purchaseTotalQuoteTitle
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/8/12 15:17
 * @Version 1.0
 **/
public class purchaseTotalQuoteTitle extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"title", "key"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        String projectId = param.get("projectId");
        //查询数据并返回
        List<Map<String,String>> list = purchaseProxyService.totalQuoteTitle(Long.valueOf(projectId));
        return list;
    }

}
