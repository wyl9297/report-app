package cn.bidlink.report.app.datasource.contract;

import cn.bidlink.procurement.contract.cloud.service.ContractItemRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.ContractProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.alibaba.fastjson.JSONObject;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContractItemSource
 * @Author Administrator
 * @Description 合同分项值数据源
 * @Date 2019/7/1 16:37
 * @Version 1.0
 **/
public class ContractQuoteSubItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{  "itemId" , "key" , "value" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        ContractProxyService contractProxyService = dataServiceFactory.getDataService(ContractProxyService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId =  Long.valueOf(param.get("companyId"));
        List<Map<String, Object>> contractQuoteItemData = contractProxyService.getContractQuoteItemData(companyId, projectId);
        return contractQuoteItemData;
    }

}
