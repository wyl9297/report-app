package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.auction.cloud.dto.ProjectQuoteRecodeSearchDto;
import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierQuoteRecodeService;
import cn.bidlink.procurement.auction.cloud.vo.QuoteRecodeVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionProject
 * @Author Administrator
 * @Description //竞价项目数据源
 * @Date 2019/5/31 15:40
 * @Version 1.0
 **/
public class AuctionSupplierQuoteRecordDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"projectItemId", "quotePrice", "quoteTime","supplierName" , "itemName"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        AuctionSupplierQuoteRecodeService auctionSupplierQuoteRecodeService = dataServiceFactory.getDataService(AuctionSupplierQuoteRecodeService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = UserContext.getCompanyId();
        ProjectQuoteRecodeSearchDto projectQuoteRecodeSearchDto = new ProjectQuoteRecodeSearchDto();
        projectQuoteRecodeSearchDto.setProjectId(projectId);
        projectQuoteRecodeSearchDto.setCompanyId(companyId);
        TableData<QuoteRecodeVo> supplierQuoteRecode = auctionSupplierQuoteRecodeService.getSupplierQuoteRecode(projectQuoteRecodeSearchDto);
        List<QuoteRecodeVo> quoteRecodeVos = supplierQuoteRecode.getTableData();
        return quoteRecodeVos;
    }

}
