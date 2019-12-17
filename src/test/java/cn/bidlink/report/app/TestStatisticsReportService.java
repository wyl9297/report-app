package cn.bidlink.report.app;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.report.server.service.PurchaseDealStatisticalService;
import cn.bidlink.report.server.service.PurchaseStatisticalDealDetailService;
import cn.bidlink.supplier_usage_statistics_cloud.response.ResponseResult;
import cn.bidlink.supplier_usage_statistics_cloud.service.cloud.LineDiagramCloudService;
import cn.bidlink.usage.supplier.service.vo.HomePageVo;
import com.fr.third.org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    @Resource
    private LineDiagramCloudService              lineDiagramService;


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
    /*@Test
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
    }*/

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

    /**
     *查詢平台采购商数据
     */
    @Test
    public void findCreateBuyerCount() throws Exception {
        //得到long类型当前时间
//        long l = System.currentTimeMillis();
//        //new日期对
//        Date datetim = new Date(l);
//        //转换提日期输出格式
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String startTime = dateFormat.format(datetim);
//        System.out.println(startTime);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -1);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH)+1;
//        int date = calendar.get(Calendar.DATE);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        int second = calendar.get(Calendar.SECOND);
//        String endTime = year+"-"+(month<10?"0"+month:month)+"-"+(date<10?"0"+date:date)+" "+(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
//        System.out.println(endTime);


        /*try {*/
//            Date startTimeTest = DateUtils.parseDate( "2019-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//            Date endTimeTest = DateUtils.parseDate( "2020-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-01-01 00:00:00");
        System.out.println(startTime);
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-01 00:00:00");
        System.out.println(endTime);

        /*}catch (Exception e){
            e.printStackTrace();
        }*/
        //ResponseResult createBuyerCount = lineDiagramService.getCreateBuyerCount(startTime, endTime);
        //ResponseResult createProjectCount = lineDiagramService.getCreateProjectCount(endTimeTest, startTimeTest);
        ResponseResult createSupplierCount = lineDiagramService.getCreateSupplierCount(startTime, endTime);
        Object data = createSupplierCount.getData();
        System.out.println(1111);

    }

    /**
     *查詢創建的項目数据
     */
    /*@Test
    public void findCreateBuyerCount() {
        List<HomePageVo> createProjectCount = lineDiagramService.getCreateProjectCount();

    }*/

    /**
     *查詢平台供应商数据
     */
    /*@Test
    public void findCreateBuyerCount() {
        List<HomePageVo> createProjectCount = lineDiagramService.getCreateSupplierCount();

    }*/
}
