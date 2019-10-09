package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.auction.cloud.service.AuctionRuleRestService;
import cn.bidlink.procurement.auction.cloud.vo.AuctionRuleVo;
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
public class AuctionRuleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"auctionTypeValue", "auctionSpaceValue", "quoteRangeValue","delayRuleValue" , "depreciateValueStr", "delayRuleHallValue" , "quoteSecrecyTypeValue"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        AuctionRuleRestService auctionRuleRestService = dataServiceFactory.getDataService(AuctionRuleRestService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = UserContext.getCompanyId();
        AuctionRuleVo rule = auctionRuleRestService.getRule(projectId, companyId);
        //查询数据并返回
        List<AuctionRuleVo> list = new ArrayList<>();
        list.add(rule);
        return list;
    }

}
