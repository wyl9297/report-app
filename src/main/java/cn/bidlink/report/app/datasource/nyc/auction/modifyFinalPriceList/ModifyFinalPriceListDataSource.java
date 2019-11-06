package cn.bidlink.report.app.datasource.nyc.auction.modifyFinalPriceList;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.*;

public class ModifyFinalPriceListDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        String[] column = {"directory_name", "directory_code", "spec", "tech_prameters", "unit_name", "plan_amount", "RMB"};
        return column;
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        List insert = InsertParam.insert(this.getColumn());
        return insert;
    }


}
