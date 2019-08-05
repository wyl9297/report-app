package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.bidding.cloud.dto.SaveSubProjectVo;
import cn.bidlink.procurement.bidding.cloud.service.BidSubProjectRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BiddingBaseDataSource
 * @Author 从尧
 * @Description 招标项目 项目基本信息数据源
 * @Date 2019-07-29 9:46
 * @Version 1.0
 **/
public class BiddingBaseDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("subProjectId")
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
        String projectId = param.get("projectId");
        String subProjectId = param.get("subProjectId");
        //查询数据并返回
        SaveSubProjectVo subProject = bidSubProjectRestService.getBidSubDetail(UserContext.getCompanyId(), StringUtils.isNotBlank(projectId)?Long.valueOf(projectId):(StringUtils.isNotBlank(subProjectId)?Long.valueOf(subProjectId):0));
        List list = new ArrayList();
        list.add(subProject);
        return list;
    }
}
