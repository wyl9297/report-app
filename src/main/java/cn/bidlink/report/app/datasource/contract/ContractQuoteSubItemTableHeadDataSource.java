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
 * @Description 合同分项值数据源
 * @Date 2019/7/1 16:37
 * @Version 1.0
 **/
public class ContractQuoteSubItemTableHeadDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{  "key" , "title" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //todo 上线暂时注释
        List<Map<String,String>> list = new ArrayList<>();
       /* ContractProjectService contractProjectService = dataServiceFactory.getDataService(ContractProjectService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId =  Long.valueOf(param.get("companyId"));
        ResponseObj responseObj = contractProjectService.getFileNames(projectId, companyId);
        Map map = (Map)responseObj.getData();
        if ( null != map.get("showQuoteSubItem") && "true".equals(map.get("showQuoteSubItem").toString()) && null != map.get("quoteSubItem") ) {
            Map quoteSubItem = JSONObject.parseObject(map.get("quoteSubItem").toString());
            quoteSubItem.forEach((k,v) -> {
                Map m = (Map) v;
                if ( null != m.get("title") ){
                    Map<String, String> headMap = new HashMap<>(4);
                    headMap.put("title",m.get("title").toString());
                    headMap.put("key",k.toString());
                    list.add(headMap);
                }
            });
        }*/
        return list;
    }

}
