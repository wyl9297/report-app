package cn.bidlink.report.app.datasource.demand;

import cn.bidlink.demand.cloud.service.DemandRestService;
import cn.bidlink.demand.dal.dto.DemandPrintDto;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:liuqi@ebnew.com">liuqi</a>
 * @version : v1.0
 * @date :  2020-03-16 16:26
 * @description :
 */
public class DemandProjectDataSource extends AbstractBaseTableData {

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        DemandRestService demandRestService = dataServiceFactory.getDataService(DemandRestService.class);
        //获取查询报表的参数
        Long demandId = Long.valueOf(param.get("demandId"));
        Long companyId = UserContext.getCompanyId();
        //查询数据并返回
        DemandPrintDto demandPrintList = demandRestService.getDemandPrintList(demandId, companyId);
        List<Object> list = Lists.newArrayList();
        list.add(demandPrintList);
        return list;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("demandId"),
                new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "demandCycle", "createTimeStr", "demandOrgName",
                            "createUserName", "demandTotalPrice", };
    }
}
