package cn.bidlink.report.app.controller;

import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.auction.cloud.dto.ProjectQuoteRecodeSearchDto;
import cn.bidlink.procurement.auction.cloud.service.*;
import cn.bidlink.procurement.auction.cloud.vo.QuoteRecodeVo;
import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractProjectService;
import cn.bidlink.procurement.purchase.cloud.service.DealPriceSupplierDimensionRestService;
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
    AuctionRuleRestService auctionRuleRestService;

    @Autowired
    AuctionSupplierQuoteRecodeService auctionSupplierQuoteRecodeService;


    @RequestMapping(value = "success")
    @ResponseBody
    public String success(HttpServletRequest request, HttpServletResponse response) {
        //List<Map<String, String>> contractHeadAndTailData = contractProxyService.getContractHeadAndTailData(11113173803L, 329289751465033810L, 11113177048L)
        /*AuctionRuleVo rule = auctionRuleRestService.getRule(328533501085221452L, UserContext.getCompanyId());*/

        ProjectQuoteRecodeSearchDto projectQuoteRecodeSearchDto = new ProjectQuoteRecodeSearchDto();
        projectQuoteRecodeSearchDto.setProjectId(328533501085221452L);
        projectQuoteRecodeSearchDto.setCompanyId(UserContext.getCompanyId());
        TableData<QuoteRecodeVo> supplierQuoteRecode = auctionSupplierQuoteRecodeService.getSupplierQuoteRecode(projectQuoteRecodeSearchDto);
        List<QuoteRecodeVo> tableData = supplierQuoteRecode.getTableData();
        return "success";
    }

}
