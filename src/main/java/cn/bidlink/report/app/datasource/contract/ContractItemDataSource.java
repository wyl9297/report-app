package cn.bidlink.report.app.datasource.contract;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContractItemSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/7/1 16:37
 * @Version 1.0
 **/
public class ContractItemDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id" , "itemId" , "code" , "name" , "spec" , "techParameters" , "purchaseAmount" , "unitName" , "quoteUnitPrice"
                , "quoteTotalPrice" , "comment" , "appliedPersonAndPhone","appliedEnterprise","needTime" , "purpose" , "projectSources" , "useDept" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //todo 上线暂时注释
        /*ContractItemRestService contractItemRestService = dataServiceFactory.getDataService(ContractItemRestService.class);
        ContractProjectService contractProjectService = dataServiceFactory.getDataService(ContractProjectService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId =  Long.valueOf(param.get("companyId"));
        ResponseObj responseObj = contractProjectService.getFileNames(projectId, companyId);
        Map m = (Map) responseObj.getData();
        if( !"C".equals(String.valueOf(m.get("contractStyleCode"))) ){
            Map map = contractItemRestService.list(projectId, (short) 0, 0, 1000, companyId);
            Object tableData = map.get("tableData");
            if( null != tableData ){
                return (List)tableData;
            }
        }*/
        return new ArrayList();
    }

}
