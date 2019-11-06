package cn.bidlink.report.app.datasource.nyc.auction.notPackageAuctionSupplierList;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.*;

public class NotPackageAuctionSupplierListDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        Parameter[] parameter = {
                new Parameter("compId"),
                new Parameter("projectId"),
                new Parameter("directoryName")};
        return parameter;
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"bid_id", "project_id", "supplier_id", "supplier_name", "directory_name", "directory_id", "directory_code", "first_quote_price", "real_price", "jiezi", "bid_status", "divide_rate", "divide_bid_mark",
                "spec", "tech_parameters", "unit_name", "plan_amount", "link_man", "RMB"};
        return column;
    }


    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List insert = InsertParam.insert(this.getColumn());
        return insert;
    }


}
