package cn.bidlink.report.app.controller;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.procurement.auction.cloud.service.*;
import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractProjectService;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
import cn.bidlink.report.app.service.ContractProxyService;
import cn.bidlink.report.app.service.PurchaseProxyService;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.server.entity.ProjectWideTable;
import cn.bidlink.report.server.entity.SupplierPurchasesDto;
import cn.bidlink.report.server.service.SupplierPurchaseStatisticsService;
import cn.bidlink.usercenter.server.service.DubboTRegCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FOR TEST
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-05-13 15:35:59
 */
@RestController
@RequestMapping("reportApp")
public class ReportController {

    @Autowired
    DubboTRegCompanyService dubboTRegCompanyService;

    @Autowired
    private DealPriceSupplierDimensionRestService dealPriceSupplierDimensionRestService;

    @Autowired
    BidSupplierItemRestService bidSupplierItemRestService;

    @Autowired
    TenderProxyService tenderProxyService;

    @Autowired
    AuctionProjectItemRestService auctionProjectItemRestService;

    @Autowired
    ProjectRestService projectRestService;

    @Autowired
    AuctionSupplierDealResultService auctionSupplierDealResultService;

    @Autowired
    AuctionSupplierItemService auctionSupplierItemService;

    @Autowired
    ContractProxyService contractProxyService;

    @Autowired
    ContractItemRestService contractItemRestService;
    @Autowired
    ContractProjectService contractProjectService;

    @Autowired
    AuctionRuleRestService auctionRuleRestService;

    @Autowired
    AuctionSupplierQuoteRecodeService auctionSupplierQuoteRecodeService;

    @Autowired
    PurchaseProxyService purchaseProxyService;

    @Autowired
    SupplierPurchaseStatisticsService supplierPurchaseStatisticsService;

    @RequestMapping(value = "success")
    @ResponseBody
    public String success(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,String>> mapList = purchaseProxyService.totalQuoteTitle(335362391413891452L);
        List<Map> mapList1 = purchaseProxyService.totalQuoteValue(335362391413891452L);
        return "success";
    }

    @RequestMapping("/test1")
    public void test1(){
        SupplierPurchasesDto supplierPurchasesDto = new SupplierPurchasesDto();
        supplierPurchasesDto.setCompanyId(11113174493L);
        ServiceResult<List<SupplierPurchasesDto>> allByCondition = supplierPurchaseStatisticsService.findAllByCondition(supplierPurchasesDto);
        System.out.println(allByCondition);
    }

}
