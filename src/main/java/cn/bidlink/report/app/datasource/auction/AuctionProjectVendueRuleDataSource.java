package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.vendue.cloud.service.VendueProjectViewRestService;
import cn.bidlink.procurement.vendue.dal.dto.ProjectViewRulesDto;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:拍卖项目->规则信息 (基础数据集)
 * @Date 2019/6/4
 *
 */
public class AuctionProjectVendueRuleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "projectId", "vendueBail", "initPrice", "raiseValue" ,"reservePrice", "delayRule", "startTime", "endTime", "vendueStartTime",
                "vendueEndTime", "vendueStartTimeStr", "vendueEndTimeStr", "planPeriodTime", "secretRule" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        VendueProjectViewRestService vendueProjectViewRestService = dataServiceFactory.getDataService(VendueProjectViewRestService.class);

        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));

        List<ProjectViewRulesDto> list = new ArrayList<>();
        ProjectViewRulesDto projectViewRulesDto = vendueProjectViewRestService.getProjectRules(projectId, companyId);
        list.add(projectViewRulesDto);

        return list;
    }
}