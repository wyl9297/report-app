package cn.bidlink.report.app.controller;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.auction.cloud.service.AuctionProjectItemRestService;
import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierDealResultService;
import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierItemService;
import cn.bidlink.procurement.auction.cloud.service.ProjectRestService;
import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractProjectService;
import cn.bidlink.procurement.purchase.cloud.dto.BargainAllListVo;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
import cn.bidlink.procurement.purchase.cloud.service.ProjectBargainRestService;
import cn.bidlink.report.app.service.ContractProxyService;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.usercenter.server.service.DubboTRegCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    ProjectBargainRestService projectBargainRestService;


    @RequestMapping(value = "success")
    @ResponseBody
    public String success(HttpServletRequest request, HttpServletResponse response) {
        List<BargainAllListVo> allBargainItemList
                = projectBargainRestService.findAllBargainItemList(321219646428545050L, UserContext.getCompanyId());
        return "success";
    }

    @RequestMapping(value = "contractMainPrint")
    public String contractMainPrint(HttpServletRequest request, HttpServletResponse response) {
        return "success";
    }

}
