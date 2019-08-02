package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.vendue.cloud.service.VendueProjectRestService;
import cn.bidlink.procurement.vendue.cloud.vo.ProjectPrintVO;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:拍卖项目->项目信息 (基础数据集)
 * @Date 2019/6/4
 *
 */
public class AuctionProjectDetailsVendueDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code", "name", "vendueStartTime", "vendueEndTime", "createUserName" ,"createTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        VendueProjectRestService vendueProjectRestService = dataServiceFactory.getDataService(VendueProjectRestService.class);

        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));

        List<ProjectPrintVO> list = new ArrayList<>();
        ProjectPrintVO projectPrintInfo = vendueProjectRestService.getProjectPrintInfo(projectId, companyId);
        list.add(projectPrintInfo);

        return list;
    }
}