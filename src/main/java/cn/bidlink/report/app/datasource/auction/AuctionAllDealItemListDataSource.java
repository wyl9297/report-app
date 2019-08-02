package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierItemService;
import cn.bidlink.procurement.auction.dal.entity.AuctionSupplierProjectItem;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionAllDealItemListDataSource
 * @Author 从尧
 * @Description 采购品的列表  程序集
 * @Date 2019-06-03 17:22
 * @Version 1.0
 **/
public class AuctionAllDealItemListDataSource extends AbstractBaseTableData {
    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code", "name", "unitName", "spec", "initialPrice" ,"quoteAmount", "techParameters", "needTime", "appliedEnterprise", "appliedPersonAndPhone",
                "useDept", "purpose", "comment", "planPrice", "sourceName" , "createTime", "quoteUnitPrice" , "quoteTotalPrice" , "quoteStatus" , "initialPrice" , "supplierName" ,
                "supplierId" , "projectId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        AuctionSupplierItemService auctionSupplierItemService = dataServiceFactory.getDataService(AuctionSupplierItemService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));

        List<AuctionSupplierProjectItem> allDealItemlist = auctionSupplierItemService.getAllDealItemlist(projectId, companyId);
        return allDealItemlist;
    }
}
