package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.ArchivedAuctionUnProject;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuqi
 * @date 2019-11-05 15:49
 */
public class ArchivedAuctionUnProjectDataSource extends AbstractBaseTableData {

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("采购品名称", "炉石传说");
        resultMap.put("采购品编码", "c325545111211");
        resultMap.put("采购量", 488);
        resultMap.put("中标供应商名称", "暴雪");
        resultMap.put("联系人", "酒馆老板");
        resultMap.put("币种", "人民币");
        resultMap.put("最终价", 328);
        resultMap.put("分标比例", 10);
        resultMap.put("单项总价", 20);
        resultList.add(resultMap);
        return resultList;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"采购品名称","采购品编码","采购量","中标供应商名称","联系人","币种","最终价","分标比例","单项总价"};
    }
}
