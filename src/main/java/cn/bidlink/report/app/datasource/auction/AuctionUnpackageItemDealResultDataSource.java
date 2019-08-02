package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierItemService;
import cn.bidlink.procurement.auction.dal.entity.AuctionSupplierProjectItem;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionUnpackageItemDealResultDataSource
 * @Author Administrator
 * @Description //竞价 非打包 成交采购品信息
 * @Date 2019/6/3 10:20
 * @Version 1.0
 **/
public class AuctionUnpackageItemDealResultDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"name", "code", "quoteAmount", "unitName" , "spec" , "techParameters" , "createTime" , "initialPrice" , "dealUnitPrice" , "dealAmount" , "dealRation" , "dealTotalPrice" , "dealDescription" , "appliedEnterprise", "appliedPersonAndPhone", "useDept", "purpose", "needTime", "comment" , "supplierId" , "dealStatus"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        AuctionSupplierItemService auctionSupplierItemService
                = dataServiceFactory.getDataService(AuctionSupplierItemService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        List<AuctionSupplierProjectItem> allDealItemlist = auctionSupplierItemService.getAllDealItemlist(projectId, companyId);
        return allDealItemlist;
    }

}
