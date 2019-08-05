package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.purchase.cloud.service.ProjectSupplierRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InvitedSupplierDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/7/24 10:06
 * @Version 1.0
 **/
public class InvitedSupplierDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName", "supplierLoginName", "linkMan" , "linkPhone" , "supplierSource" , "assignedItemCount" , "procurementName" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        ProjectSupplierRestService projectSupplierRestService = dataServiceFactory.getDataService(ProjectSupplierRestService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = UserContext.getCompanyId();
        //查询数据并返回
        Map<String, Object> map = projectSupplierRestService.findSupplierList(projectId, companyId);
        List dataList = (List)map.get("dataList");
        return dataList;
    }

}
