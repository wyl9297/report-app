package cn.bidlink.report.app.datasource.nyc.reportPrint.purchaseProcess.purchaseResultProcess;/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/6  13:20
 * @description :cn.bidlink.nyc.report.dataSource.purchaseProcess.ProcessItemAndItemApproval
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
 * @ClassName ProcessItemAndItemApproval
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/11/6 13:20
 * @Version 1.0
 **/
public class ProcessItemAndItemApprovalDataSource
        extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("createUsername")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"approveCount","index", "approve_user_name","approve_opition","create_tm","approve_result","approve_id"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        String[] column = this.getColumn();
        return InsertParam.insert(column);
    }
}