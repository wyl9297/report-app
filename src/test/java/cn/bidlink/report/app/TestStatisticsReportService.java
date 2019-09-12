package cn.bidlink.report.app;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.report.server.service.PurchaseDealStatisticalService;
import cn.bidlink.report.server.service.PurchaseStatisticalDealDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/9/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ,classes = Application.class)
public class TestStatisticsReportService extends TestBase{


    @Autowired
    private PurchaseDealStatisticalService       testPurchaseService;
    @Autowired
    private PurchaseStatisticalDealDetailService purchaseStatisticalDealDetailService;

     /***
     * 采购成交采购品统计列表
     * 11113173798L
     */
    @Test
    public void testSelApprovingByProjId() {
        PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
        purchaseNewProjectWideTable.setCompanyId(Long.valueOf("11113174493"));
        purchaseNewProjectWideTable.setDirectoryName("苹果");
        purchaseNewProjectWideTable.setDirectoryCode("CGP190505000015");
        purchaseNewProjectWideTable.setSpec("");
        purchaseNewProjectWideTable.setCatalogNamePath("/采购目录/食品");
        /*purchaseNewProjectWideTable.setStartCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-15"));
        purchaseNewProjectWideTable.setEndCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-28"));*/
        ServiceResult<List<PurchaseNewProjectWideTable>> result = testPurchaseService.findPurchaseDealStatistical(purchaseNewProjectWideTable);
        List<PurchaseNewProjectWideTable> approvings = result.getResult();
        for (PurchaseNewProjectWideTable approving : approvings) {
            System.out.println(approving.getCatalogNamePath());
            System.out.println(approving.getDirectoryCode());
            System.out.println(approving.getDirectoryName());
            print(approving);
        }
    }

    /***
     * 采购成交采购品统计钻取单一采购品数据
     * 11113173798L
     */
    @Test
    public void findPurchaseDealStatisticalOne() {
        ServiceResult<List<PurchaseNewProjectWideTable>> result = testPurchaseService.findPurchaseDealStatisticalOne(174923316547551232L);
        List<PurchaseNewProjectWideTable> approvings = result.getResult();
        for (PurchaseNewProjectWideTable approving : approvings) {
            print(approving);
        }
    }

    /***
     * 采购成交采购品统计钻取项目数据
     * 11113173798L
     */
    @Test
    public void findPurchaseDealStatisticalDetailProject() {
        ServiceResult<List<PurchaseNewProjectWideTable>> result = testPurchaseService.findPurchaseDealStatisticalDetailProject(174923316547551232L);
        List<PurchaseNewProjectWideTable> approvings = result.getResult();
        if (approvings != null && approvings.size()>0){
            for (PurchaseNewProjectWideTable approving : approvings) {
                print(approving);
            }
        }else {
            System.out.println(111111);
        }
    }

    /**
     *采购成交明细统计
     */
    @Test
    public void findPurchaseStatisticalDealDetail() {
        PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
        purchaseNewProjectWideTable.setCompanyId(Long.valueOf("11113174153"));
        //purchaseNewProjectWideTable.setCatalogNamePath("/采购目录/食品");
        ServiceResult<List<PurchaseNewProjectWideTable>> result = purchaseStatisticalDealDetailService.findPurchaseStatisticalDealDetail(purchaseNewProjectWideTable);
        List<PurchaseNewProjectWideTable> approvings = result.getResult();
        for (PurchaseNewProjectWideTable approving : approvings) {
            print(approving);
        }
    }
}
