package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.dto.AuctionProjectItemDto;
import cn.bidlink.procurement.auction.cloud.service.AuctionProjectItemRestService;
import cn.bidlink.procurement.auction.cloud.vo.ResultTabListVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionPackageItemDealResultDataSource
 * @Author Administrator
 * @Description 竞价 打包 成交采购品信息
 * @Date 2019/6/3 10:36
 * @Version 1.0
 **/
public class AuctionPackageItemDealResultDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "purchaseAmount", "unitName" , "spec" , "techParameters" , "createTime", "appliedEnterprise", "appliedPersonAndPhone", "useDept", "purpose", "needTime", "comment"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        AuctionProjectItemRestService auctionProjectItemRestService
                = dataServiceFactory.getDataService(AuctionProjectItemRestService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        ResultTabListVo<AuctionProjectItemDto> auctionProjectItemDtoResultTabListVo
                = auctionProjectItemRestService.projectViewList(companyId, projectId, 0, 0);
        List<AuctionProjectItemDto> auctionProjectItemDtos = auctionProjectItemDtoResultTabListVo.getTableData();
        return auctionProjectItemDtos;
    }

}
