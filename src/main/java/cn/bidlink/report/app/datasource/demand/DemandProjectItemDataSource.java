package cn.bidlink.report.app.datasource.demand;

import cn.bidlink.demand.cloud.service.DemandRestService;
import cn.bidlink.demand.dal.dto.DemandItemsPrintDto;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:liuqi@ebnew.com">liuqi</a>
 * @version : v1.0
 * @date :  2020-03-18 9:40
 * @description :
 */
public class DemandProjectItemDataSource extends AbstractBaseTableData {
    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        DemandRestService demandRestService = dataServiceFactory.getDataService(DemandRestService.class);
        //获取查询报表的参数
        Long demandId = Long.valueOf(param.get("demandId"));
        Long companyId = UserContext.getCompanyId();
        //查询数据并返回
        List<DemandItemsPrintDto> demandItemsList = demandRestService.getDemandItemsList(demandId, companyId);
        return demandItemsList;
    }

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("demandId"),
                new Parameter("companyId")};
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "spec", "techParameters", "amount", "unitName", "planPrice",
                            "appliedPersonAndPhone", "useDept", "remark", "purpose", "appliedEnterprise",
                            "needTime", "source", "createTime", "isDelete", "sourceStr"};
    }
}
