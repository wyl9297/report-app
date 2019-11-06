package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.SupplierBidResultDataSet;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class BidOpenSupplierResultADataSource extends AbstractBaseTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] strings = this.getColumn();
        return InsertParam.insert(strings);
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("supplierId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "code" ,"name","spec","productor","pcode","plan_amount","unit_name","usedepart",
                "need_time","description","bid_price","deal_price","deal_total_price","deal_amount","divide_bid_ration","tech"};
    }
}
