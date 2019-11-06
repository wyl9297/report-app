package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchase_result_process_directory;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.datasource.nyc.InsertParam;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

public class ProcessProjectDataSource extends AbstractBaseTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] strings = this.getColumn();
        return InsertParam.insert(strings);
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[] { new Parameter("projectId"),new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "项目编号" ,"项目名称","用户名称","创建时间","project_status","approve_status","type",
                "divide_bid_mark","报价精度","purchase_name"};
    }
}
