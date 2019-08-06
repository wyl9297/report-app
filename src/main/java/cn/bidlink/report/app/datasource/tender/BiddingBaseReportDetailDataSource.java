package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.procurement.bidding.cloud.dto.SaveSubProjectVo;
import cn.bidlink.procurement.bidding.cloud.service.BidSubProjectRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:招标项目 审批导出项目基本信息数据源
 * @Date 2019/7/29
 *
 */
public class BiddingBaseReportDetailDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"projectName", "projectCode", "bidOpenTimeStr" , "createUserName" , "linkPhone"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        BidSubProjectRestService bidSubProjectRestService = dataServiceFactory.getDataService(BidSubProjectRestService.class);
        //获取报表查询的参数
        String subProjectId = param.get("projectId");
        //查询数据并返回
        SaveSubProjectVo subProject = bidSubProjectRestService.getBidReportDetail(Long.valueOf(subProjectId));
        List list = new ArrayList();
        list.add(subProject);
        return list;
    }
}
