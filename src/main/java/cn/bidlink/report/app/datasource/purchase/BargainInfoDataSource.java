package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.purchase.cloud.dto.BargainAllListVo;
import cn.bidlink.procurement.purchase.cloud.service.ProjectBargainRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BargainInfoDataSource
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/7/24 14:23
 * @Version 1.0
 **/
public class BargainInfoDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"bargainTime",
                            "startMan",
                            "afterPrice",
                            "beforePrice",
                            "supplierItemId",
                            "response" ,
                            "bargainReason" ,
                            "bargainStatus",
                            "supplierConfirmTime",
                            "needConfirm",
                            "supplierId"
                            };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        PurchaseProxyService purchaseProxyService = dataServiceFactory.getDataService(PurchaseProxyService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = UserContext.getCompanyId();
        List<Map<String, String>> bargainInfoList = purchaseProxyService.getBargainInfoList(projectId, companyId);
        return bargainInfoList;
    }

}
