package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.service.ProjectRestService;
import cn.bidlink.procurement.auction.cloud.vo.ProjectVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionProject
 * @Author Administrator
 * @Description //竞价项目数据源
 * @Date 2019/5/31 15:40
 * @Version 1.0
 **/
public class AuctionProject extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code", "name", "createUserName","createTime" , "auctionEndTimeStr", "projectType", "projectTypeValue"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        ProjectRestService projectRestService = dataServiceFactory.getDataService(ProjectRestService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        //查询数据并返回
        ProjectVo projectDetail = projectRestService.getProjectDetail(projectId, companyId);
        List<Object> list = new ArrayList<>();
        list.add(projectDetail);
        return list;
    }

}
