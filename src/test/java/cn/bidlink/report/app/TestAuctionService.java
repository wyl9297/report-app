package cn.bidlink.report.app;

import cn.bidlink.procurement.auction.cloud.dto.AuctionProjectItemDto;
import cn.bidlink.procurement.auction.cloud.service.AuctionProjectItemRestService;
import cn.bidlink.procurement.auction.cloud.service.AuctionQuotedPriceRestService;
import cn.bidlink.procurement.auction.cloud.service.AuctionSupplierItemService;
import cn.bidlink.procurement.auction.cloud.service.ProjectRestService;
import cn.bidlink.procurement.auction.cloud.vo.ProjectVo;
import cn.bidlink.procurement.auction.cloud.vo.ResultTabListVo;
import cn.bidlink.procurement.auction.dal.entity.AuctionSupplierProjectItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:46 2019-06-03
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ,classes = Application.class)
public class TestAuctionService extends TestBase {

    @Autowired
    private ProjectRestService projectRestService;

    @Autowired
    private AuctionQuotedPriceRestService auctionQuotedPriceRestService;

    @Autowired
    private AuctionProjectItemRestService auctionProjectItemRestService;

    @Autowired
    private AuctionSupplierItemService auctionSupplierItemService;
    @Test
    public void test1(){
        ProjectVo projectDetail = projectRestService.getProjectDetail(319114548193788381L, 11113173881L);
        Map<String, Object> quotedSupplierList = auctionQuotedPriceRestService.findQuotedSupplierList(319114548193788381L, 11113173881L, false, 1, 20);
        ResultTabListVo<AuctionProjectItemDto> auctionProjectItemDtoResultTabListVo = auctionProjectItemRestService.projectViewList(11113173881L, 319114548193788381L, 1, 20);
        print(projectDetail);
    }

    @Test
    public void test2(){
        List<AuctionSupplierProjectItem> allDealItemlist = auctionSupplierItemService.getAllDealItemlist(320245554162434120L, 11113173881L);
        print(allDealItemlist);
    }
}
