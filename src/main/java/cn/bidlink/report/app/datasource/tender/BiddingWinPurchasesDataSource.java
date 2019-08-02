package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  18:25 2019-05-29
 * @Description : 招标项目 中标采购品 程序集
 */
public class BiddingWinPurchasesDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("subProjectId"),
                new Parameter("reserveResult")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"bidProjectItemId" , "name", "code", "spec" , "techParameters","quoteAmount", "unitName", "appliedPersonAndPhone","appliedEnterprise", "useDept","purpose",
                "needTime","comment", "supplierId", "supplierName","quoteUnitPrice","quoteTotalPrice","dealNumber", "dealRatio", "dealPrice","dealTotalPrice" ,
                 "projectItemId" , "listSupplierId" , "dealInstruction"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        TenderProxyService tenderProxyService = dataServiceFactory.getDataService(TenderProxyService.class);
//        String reserveResult = param.get("reserveResult");
        // todo reserveResult：值默认为1
        List result = tenderProxyService.getDecideBidItemView(Long.valueOf(param.get("subProjectId")), 1);
        return result;
    }
}
