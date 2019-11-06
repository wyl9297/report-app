package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.archivedAuctionSupplierProject;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  9:28
 * @description :
 */
public class ArchivedAuctionSupplierProjectDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("purchaserId"),
                new Parameter("supplierId")
        };
    }

    @Override
    protected String[] getColumn() {
         return new String[]{"采购品名称","采购品编号","规格/型号","技术参数/材质","采购量","单位"};
    }
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        InsertParam insertParam = new InsertParam();
        insertParam.insert(column);
        return  insertParam.insert(column);
    }
}
