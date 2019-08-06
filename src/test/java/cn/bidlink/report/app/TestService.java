package cn.bidlink.report.app;

import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.bidding.cloud.dto.SaveSubProjectVo;
import cn.bidlink.procurement.bidding.cloud.service.BidSubProjectRestService;
import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.procurement.bidding.cloud.service.BidViewRestService;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectItemSupplierQuoteDto;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierDimensionDealResultVO;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierDimensionDetailVO;
import cn.bidlink.procurement.purchase.cloud.dto.SupplierQuotedItemDto;
import cn.bidlink.procurement.purchase.cloud.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  8:32 2019-05-06
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ,classes = Application.class)
public class TestService extends TestBase{

    @Autowired
    private QuotedPriceRestService quotedPriceRestService;

    @Autowired
    private ProjectSupplierItemRestService projectSupplierItemRestService;

    @Autowired
    private DealPriceSupplierDimensionRestService dealPriceSupplierDimensionRestService;

    @Autowired
    private ProjectRestService projectRestService;

    @Autowired
    private BidViewRestService bidViewRestService;

    @Autowired
    private DealPriceRestService dealPriceRestService;

    @Autowired
    private BidSupplierItemRestService bidSupplierItemRestService;

    @Autowired
    private BidSubProjectRestService bidSubProjectRestService;

    /**
     * 统计报价相关项  单元测试
     */
    @Test
    public void testGetQuotedInfoCount(){
        Map<String, Object> quotedInfoCount = quotedPriceRestService.getQuotedInfoCount(315453103186903420L, 11113173881L);
        print(quotedInfoCount);
    }

    /**
     * 按供应商维度查看供应商列表 单元测试
     */
    @Test
    public void testFindQuotedSupplierList(){
        Map<String, Object> quotedSupplierList = quotedPriceRestService.findQuotedSupplierList(315453103186903420L, 11113173881L, false, 0, 10);
        print(quotedSupplierList);
    }

    /**
     *  单个供应商报价详情  单元测试
     */
    @Test
    public void testSupplierQuotedItemlist(){
        SupplierQuotedItemDto supplierQuotedItemDto = projectSupplierItemRestService.supplierQuotedItemlist(315453103186903420L,315453103186903442L , 11113173881L);
        print(supplierQuotedItemDto);
    }

    /**
     * 供应商维度查询成交结果  单元测试
     * @RequestParam("projectId") Long projectId, @RequestParam("companyId") Long companyId, @RequestParam("showNoDeal") Boolean showNoDeal
     */
    @Test
    public void testFindSupplierDealResultList(){
        List<ProjectSupplierDimensionDealResultVO> supplierDealResultList = dealPriceSupplierDimensionRestService.findSupplierDealResultList(315453103186903420L, 11113173881L, false);
        print(supplierDealResultList);
    }

    /**
     * 查询供应商对应的采购品详细成交数据 单元测试
     * pageNum 及pageSize兼容移动端用
     * @return
     */
    @Test
    public void testFindSupplierDealDetail(){
        ProjectSupplierDimensionDetailVO supplierDealDetail = dealPriceSupplierDimensionRestService.findSupplierDealDetail(315453103186903420L, 1112798881L, 11113173881L, true, true, 1, 10);
        print(supplierDealDetail);
    }

    /**
     * 报价一览表查询采购品维度报价详情 单元测试
     */
    @Test
    public void testFindItemSupplierQuoteInfo(){
        TableData<ProjectItemSupplierQuoteDto> itemSupplierQuoteInfo = quotedPriceRestService.findItemSupplierQuoteInfo(11113173851L,315453103186903420L,false,1,10);
        print(itemSupplierQuoteInfo);
    }

    @Test
    public void testGetProjectExt(){
        /*PurchaseProjectExt projectExt = projectRestService.getProjectExt(310113138122949407L, 11113173881L);
        System.out.println(projectExt);*/
    }

    @Test
    public void testFindProjectExt(){
        Map<String, Object> projectExt = projectRestService.findProjectExt(315453103186903420L, 11113173881L);
        print(projectExt);
    }

    @Test
    public void testGetSupplierQuoteData(){
        Map<String, Object> supplierQuoteData = quotedPriceRestService.getSupplierQuoteData(315453103186903420L,11113173881L);
        // 实际截止时间：realQuoteStopTime
        print(supplierQuoteData);
    }

    @Test
    public void testGetDecideBidItemView(){
        Map<String, Object> decideBidItemView = bidViewRestService.getDecideBidItemView(202354321679974400L, 1);
        print(decideBidItemView);
    }

    // 采购项目 成交结果 采购品维度查看
    @Test
    public void testFindPurchaseDealItem(){
        Map<String, Object> purchaseDealItem = dealPriceRestService.findPurchaseDealItem(315453103186903420L, 11113173881L);
        print(purchaseDealItem);
    }

    // 分项报价表  按采购品维度查看
    @Test
    public void testGetSupplierBidItemList(){
        Map<String, Object> supplierBidItemList = bidSupplierItemRestService.getSupplierBidItemList(328920271245279253L);
        print(supplierBidItemList);
    }

    // 采购项目分项报价项
    @Test
    public void test2(){
        List<Map<String, Object>> printingSubItem = projectRestService.findPrintingSubItem(315453103186903660L, 11113173881L);
        print(printingSubItem);
    }

    // 招标项目  分项报价项
    @Test
    public void test3(){
        List<Map<String, Object>> quoteItem = bidSubProjectRestService.findQuoteItem(326719515641512069L);
        print(quoteItem);
    }

    // 招标项目 项目基本信息
    @Test
    public void testFindBidSubProjectByPk(){
        SaveSubProjectVo bidSubDetail = bidSubProjectRestService.getBidSubDetail(11113174093L, 328920271245279269L);
        print(bidSubDetail);
    }
}
