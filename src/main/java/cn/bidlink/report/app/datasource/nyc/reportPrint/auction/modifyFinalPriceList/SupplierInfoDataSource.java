package cn.bidlink.report.app.datasource.nyc.reportPrint.auction.modifyFinalPriceList;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.*;

public class SupplierInfoDataSource extends AbstractBaseTableData {

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
        String[] column = {"supplier_name", "link_man", "link_phone", "real_price", "final_price","RMB"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List insert = InsertParam.insert(this.getColumn());
        return insert;
    }


}
