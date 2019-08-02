package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierDealResultService;
import cn.bidlink.procurement.auction.cloud.vo.AuctionSupplierDealTotalVo;
import cn.bidlink.procurement.auction.cloud.vo.AuctionSupplierDealVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionSupplierDealResultDataSource
 * @Author Administrator
 * @Description 竞价成交供应商信息
 * @Date 2019/6/3 10:18
 * @Version 1.0
 **/
public class AuctionSupplierDealResultDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "quoteTotalPrice" , "dealTotalPrice" , "supplierId" , "supplierName" , "quoteTime" };
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        AuctionSupplierDealResultService auctionSupplierDealResultService
                = dataServiceFactory.getDataService(AuctionSupplierDealResultService.class);
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        AuctionSupplierDealTotalVo supplierDealResultList
                = auctionSupplierDealResultService.findSupplierDealResultList(projectId, companyId, false, null, null);
        List<AuctionSupplierDealVo> dealResultListTableData = supplierDealResultList.getTableData();
        return dealResultListTableData;
    }

}
