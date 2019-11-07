package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpenSupplier;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  11:57
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.BidOpenSupplierB
 */

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BidOpenSupplierB
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 11:57
 * @Version 1.0
 **/
public class BidOpenSupplierBDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("supplierId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[] { "code" ,"name","unit_name","spec","plan_amount","description","bid_price","usedepart","need_time","bid_mark","supplier_project_bid_id",
                "data1","data2","data3","data4","data5","data6","data7","data8","data9","data10","data11","data12","data13","data14","data15"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}