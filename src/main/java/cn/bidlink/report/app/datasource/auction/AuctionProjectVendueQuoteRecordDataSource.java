package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.vendue.cloud.dto.ProjectQuoteRecodeDto;
import cn.bidlink.procurement.vendue.cloud.dto.ProjectViewQuoteRecodesDto;
import cn.bidlink.procurement.vendue.cloud.service.VendueProjectViewRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:拍卖项目->出价记录信息 (基础数据集)
 * @Date 2019/6/4
 *
 */
public class AuctionProjectVendueQuoteRecordDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId"),
                new Parameter("pageNum"),
                new Parameter("pageSize")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "supplierId", "supplierName", "quotePrice", "quotePriceStr" ,"quoteTime","quoteTimeStr"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        VendueProjectViewRestService vendueProjectViewRestService = dataServiceFactory.getDataService(VendueProjectViewRestService.class);

        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        int pageNum = Integer.parseInt(param.get("pageNum"));
        int pageSize = Integer.parseInt(param.get("pageSize"));

        //保价记录为空时返回自定义空list
        List<ProjectQuoteRecodeDto> list = new ArrayList<>();
        ProjectViewQuoteRecodesDto quoteRecodes = vendueProjectViewRestService.getQuoteRecodes(projectId, companyId, pageNum, 3000);
        List<ProjectQuoteRecodeDto> tableData = quoteRecodes.getTableData();
        if (tableData != null && tableData.size()>0){
            return tableData;
        }else {
            return list;
        }


    }
}