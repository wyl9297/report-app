package cn.bidlink.report.app.datasource.contract;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.ContractProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ContractHeadDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/7/1 16:37
 * @Version 1.0
 **/
public class ContractHeadAndTailDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("userId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "title" , "value" , "position" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        ContractProxyService contractProxyService = dataServiceFactory.getDataService(ContractProxyService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId =  Long.valueOf(param.get("companyId"));
        Long userId;
        if(StringUtils.isNotEmpty(param.get("userId"))){
            userId = Long.valueOf(param.get("userId"));
        } else {
            userId = UserContext.getUserId();
        }
        List<Map<String, String>> contractHeadData
                = contractProxyService.getContractHeadAndTailData(companyId, projectId, userId);
        return contractHeadData;
    }

}
