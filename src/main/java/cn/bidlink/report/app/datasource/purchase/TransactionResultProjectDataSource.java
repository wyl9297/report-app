package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.procurement.purchase.cloud.service.ProjectSupplierRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TransactionResultProjectDataSource
 * @Author ylw
 * @Description //成交结果采购项目数据源
 * @Date 2019/5/23 9:24
 * @Version 1.0
 **/
public class TransactionResultProjectDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"projectCode", "projectName" ,"totalPriceSaving", "dealTotalPrice","totalPriceSavingRatio","createUserName","createTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        ProjectSupplierRestService projectSupplierRestService = dataServiceFactory.getDataService(ProjectSupplierRestService.class);
        Map<String, Object> transactionResult = projectSupplierRestService.findTransactionResult(Long.valueOf(param.get("projectId")), Long.valueOf(param.get("companyId")));
        List<Map> list = new ArrayList<>();
        list.add(transactionResult);
        return list;
    }

}
