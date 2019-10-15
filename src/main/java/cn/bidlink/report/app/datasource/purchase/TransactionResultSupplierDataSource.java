package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.service.ProjectSupplierRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionResultSupplierDataSource
 * @Author ylw
 * @Description 成交结果供应商数据源
 * @Date 2019/5/22 17:14
 * @Version 1.0
 **/
public class TransactionResultSupplierDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("projectId"),
                new Parameter("showFlag")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName" ,"quoteTime","quoteTotalPrice","dealTotalPricePrinting","supplierPriceSavingRatio","quoteTotalTitle","quoteTotalItem","supplierListCode"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        Boolean showFlag = Boolean.valueOf(param.get("showFlag"));
        ProjectSupplierRestService projectSupplierRestService = dataServiceFactory.getDataService(ProjectSupplierRestService.class);
        Map<String, Object> transactionResult = projectSupplierRestService.findTransactionResult(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")),showFlag);
        List supplierList = (List)transactionResult.get("supplierList");
        return supplierList;
    }

}
