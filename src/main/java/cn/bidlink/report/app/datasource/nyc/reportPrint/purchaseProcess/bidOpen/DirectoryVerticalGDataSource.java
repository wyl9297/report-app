package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.bidOpen;

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
 * @date :  2019/11/6  11:18
 * @description :原E采供  cn.bidlink.nyc.report.dataSource.purchaseProcess.DirectoryVerticalG
 */
public class DirectoryVerticalGDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected  String[] getColumn() {
        return new String[] { "code" ,"name","unit_name","spec","plan_amount","description","bid_price","usedepart","need_time","bid_mark","supplier_project_bid_id","tech_parameters","purpose","applied_enterprise","applied_person_and_phone",
                "data1","data2","data3","data4","data5","data6","data7","data8","data9","data10","data11","data12","data13","data14","data15"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}

