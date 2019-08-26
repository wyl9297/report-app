package cn.bidlink.report.app;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.framework.common.entity.ResponseObj;
import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.approve.cloud.service.RestWorkflowApproveService;
import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeDto;
import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeParamDto;
import cn.bidlink.procurement.appset.dal.dto.AppsetPrivilegeControlDto;
import cn.bidlink.procurement.appset.dal.enums.PrivilegeEnum;
import cn.bidlink.procurement.appset.dal.service.DubboAppsetPrivilegeModuleNodeControlService;
import cn.bidlink.procurement.bidding.cloud.service.BidSubProjectRestService;
import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.procurement.bidding.cloud.service.BidViewRestService;
import cn.bidlink.procurement.bidding.dal.entity.BidSubProject;
import cn.bidlink.procurement.order.cloud.dto.OrderDetailDto;
import cn.bidlink.procurement.order.cloud.service.OrderCargoRestService;
import cn.bidlink.procurement.order.cloud.service.OrderListResultService;
import cn.bidlink.procurement.order.dal.dto.OrderCargoPrintDto;
import cn.bidlink.procurement.purchase.cloud.dto.*;
import cn.bidlink.procurement.purchase.cloud.service.*;
import cn.bidlink.procurement.purchase.cloud.vo.DealItemSupplierVo;
import cn.bidlink.procurement.purchase.dal.entity.PurchaseProject;
import cn.bidlink.procurement.purchase.dal.entity.PurchaseProjectExt;
import cn.bidlink.procurement.vendue.cloud.dto.ProjectViewQuoteRecodesDto;
import cn.bidlink.procurement.vendue.cloud.service.VendueProjectRestService;
import cn.bidlink.procurement.vendue.cloud.service.VendueProjectViewRestService;
import cn.bidlink.procurement.vendue.cloud.vo.ProjectPrintVO;
import cn.bidlink.procurement.vendue.dal.dto.ProjectViewRulesDto;
import cn.bidlink.report.app.model.vo.purchase.TotalItemsSeparatelyVo;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ,classes = Application.class)
public class TestReportService extends TestBase{


    @Autowired
    private QuotedPriceRestService quotedPriceRestService;

    @Autowired
    private ProjectSupplierRestService projectSupplierRestService;

    @Autowired
    private ProjectSupplierItemRestService projectSupplierItemRestService;

    @Autowired
    private DealPriceSupplierDimensionRestService dealPriceSupplierDimensionRestService;

    @Autowired
    private ProjectRestService projectRestService;

    @Autowired
    private ProjectItemRestService projectItemRestService;

    @Autowired
    private DealItemSupplierRestService dealItemSupplierRestService;

    @Autowired
    private BidViewRestService bidViewRestService;

    @Autowired
    private VendueProjectRestService vendueProjectRestService;

    @Autowired
    private VendueProjectViewRestService vendueProjectViewRestService;

    @Autowired
    private OrderListResultService orderListResultService;

    @Autowired
    private OrderCargoRestService orderCargoRestService;

    @Autowired
    private ProjectApprovalRestService projectApprovalRestService;

    @Autowired
    private RestWorkflowApproveService restWorkflowApproveService;

    @Autowired
    private BidSupplierItemRestService bidSupplierItemRestService;

    @Autowired
    private BidSubProjectRestService bidSubProjectRestService;

    @Autowired
    private DubboAppsetPrivilegeModuleNodeControlService dubboAppsetPrivilegeModuleNodeControlService;


    /**
     * 统计报价相关项  单元测试
     */
    @Test
    public void testGetQuotedInfoCount(){
        Map<String, Object> quotedInfoCount = quotedPriceRestService.getQuotedInfoCount(240497992098906112L, 11113173881L);
        System.out.println(quotedInfoCount);
    }

    /**
     * 按供应商维度查看供应商列表 单元测试
     */
    @Test
    public void testFindQuotedSupplierList(){
        Map<String, Object> quotedSupplierList = quotedPriceRestService.findQuotedSupplierList(240497992098906112L, 11113173881L, true, 0, 10);
        System.out.println(quotedSupplierList);
    }

    /**
     *  单个供应商报价详情  单元测试
     */
    @Test
    public void testSupplierQuotedItemlist(){
        SupplierQuotedItemDto supplierQuotedItemDto = projectSupplierItemRestService.supplierQuotedItemlist(240497992098906112L,240499310314127360L , 11113173881L);
        System.out.println(supplierQuotedItemDto);
    }

    /**
     * 供应商维度查询成交结果  单元测试
     * @RequestParam("projectId") Long projectId, @RequestParam("companyId") Long companyId, @RequestParam("showNoDeal") Boolean showNoDeal
     */
    @Test
    public void testFindSupplierDealResultList(){
        List<ProjectSupplierDimensionDealResultVO> supplierDealResultList = dealPriceSupplierDimensionRestService.findSupplierDealResultList(240497992098906112L, 11113173881L, true);
        System.out.println(supplierDealResultList);
    }

    /**
     * 查询供应商对应的采购品详细成交数据 单元测试
     * pageNum 及pageSize兼容移动端用
     * @return
     */
    @Test
    public void testFindSupplierDealDetail(){
        ProjectSupplierDimensionDetailVO supplierDealDetail = dealPriceSupplierDimensionRestService.findSupplierDealDetail(240497992098906112L, 11113173836L, 11113173881L, true, true, 1, 10);
        System.out.println(supplierDealDetail);
    }

    /**
     * 查询采购品维度采购品数据
     */
    @Test
    public void testFindItemSupplierQuoteInfo(){
        Map ap = new HashMap<>();
        TableData<ProjectItemSupplierQuoteDto> itemSupplierQuoteInfo = quotedPriceRestService.findItemSupplierQuoteInfo(11113173881L, 240497992098906112L, true, 0, 10);
        print(itemSupplierQuoteInfo);
    }

    /**
     * 查询采购品维度采购品数据对应的项目信息
     */
    @Test
    public void testGetProject(){
        Map ap = new HashMap<>();
        PurchaseProject project = projectRestService.getProject(240497992098906112L, 11113173881L);
        print(project);
    }

    /**
     * 采购品-供应商维度
     */
    /*@Test
    public void testFindDealItemSupplier(){
        Map ap = new HashMap<>();
        TableData<ProjectItemSupplierQuoteDto> itemSupplierQuoteInfo = dealItemSupplierRestService.findDealItemSupplier(315453103186903420L,11113173881L);
        print(itemSupplierQuoteInfo);
    }*/

    /**
     * 分项报价项
     */
    @Test
    public void testGetProjectExt(){
        PurchaseProjectExt projectExt = projectRestService.getProjectExt(310113138122949407L, 11113173881L);
        System.out.println(projectExt);
    }

    /**
     * 采购品信息cn.bidlink.procurement.purchase.cloud.service.ProjectItemRestService#projectItemList
     */
    @Test
    public void testGetProjectItemList(){
        List<ProjectItemDto> projectItemDtos = projectItemRestService.projectItemList(315453103186903420L, 11113173881L);
        System.out.println(projectItemDtos);
    }

    /**
     * 获取项目总项分项cn.bidlink.procurement.purchase.cloud.service.impl.ProjectRestServiceImpl#findPrintingSettingItem
     */
    @Test
    public void testGetFindPrintingSettingItem(){
        Map<String, Object> printingSettingItem = projectRestService.findPrintingSettingItem(315453103186903420L, 11113173881L);
        System.out.println(printingSettingItem);
    }
    @Test
    public void testMap(){
        Map<String, Object> map = new HashedMap();
        map.put("schemeSubentry","总项报价项");
        for (String obj : map.keySet()){
            TotalItemsSeparatelyVo totalItemsSeparatelyVo = new TotalItemsSeparatelyVo();
            if (obj.equals("schemeSubentry")){

                totalItemsSeparatelyVo.setSchemeSubentry(obj);
                System.out.println(totalItemsSeparatelyVo.getSchemeSubentry());
            }else {
                System.out.println("111111111111111111111111111");
            }

        }

    }

    @Test
    public void testfindTransactionResult(){
        Map<String, Object> transactionResult = projectSupplierRestService.findTransactionResult(314421611660837088L, 11113174124L);
        print(transactionResult);
    }
    /**
     * 开标一览表
     * bidViewRestService.getDecideBidView(subProjectId);  295521391136997484L   260763773621501952L  325306841863028771L
     */
    @Test
    public void testGetDecideBidView(){
        List<Map<String, Object>> decideBidView = bidViewRestService.getDecideBidView(336178103481008135L);
        System.out.println(decideBidView);

    }

    /**
     * 开标一览表多伦报价
     * bidViewRestService.getDecideBidView(subProjectId);  295521391136997484L   260763773621501952L  325306841863028771L
     */
    @Test
    public void testGetMultipleSupplierBidItemList(){
        List<Map<String, Object>> m = bidViewRestService.getMultipleQuoteBidView(336178103481008135L, 1);
        System.out.println(m);

    }

    /**
     * 中标结果
     * bidViewRestService.getDecideBidSupplierView(subProjectId); 295521391136997484L 260763773621501952L 202354321679974400L
     * 198361262256553984L  252751436570427392L
     */
    @Test
    public void testGetDecideBidSupplierView(){
        List<Map<String, Object>> decideBidView = bidViewRestService.getDecideBidSupplierView(318702057794043904L);
        System.out.println(decideBidView);

    }


    @Test
    public void testdealPriceSupplierDimensionRestService(){
        ResponseObj responseObj = dealPriceSupplierDimensionRestService.findPreDealSupplierItemList(288605705445835177L, 11113174124L);
        Map responseObjData = (Map)responseObj.getData();
        print(responseObjData);
    }

    /**
     * 拍卖项目 项目信息
     * vendueProjectRestService.getProjectPrintInfo(projectId, companyId)
     */
    @Test
    public void testGetProjectPrintInfo(){
        ProjectPrintVO projectPrintInfo = vendueProjectRestService.getProjectPrintInfo(285068377494388805L, 11113174155L);
        System.out.println(projectPrintInfo);

    }

    /**
     * 拍卖项目 规则信息
     * vendueProjectViewRestService.getProjectRules(projectId, companyId);
     */
    @Test
    public void testGetProjectRules(){
        ProjectViewRulesDto projectRules = vendueProjectViewRestService.getProjectRules(285068377494388805L, 11113174155L);
        System.out.println(projectRules);

    }

    /**
     * 拍卖项目 出价记录信息
     * vendueProjectViewRestService.getQuoteRecodes(projectId, companyId, pageNum, pageSize);
     * 多次出价记录285461155487416324L     一次出价记录285068377494388805L
     */
    @Test
    public void testGetQuoteRecodes(){
        ProjectViewQuoteRecodesDto quoteRecodes = vendueProjectViewRestService.getQuoteRecodes(285068377494388805L, 11113174155L, 1, 500);
        System.out.println(quoteRecodes);

    }


    /**
     * 订单项目 订单信息和收货人信息
     * orderListResultService.getOrderDetail(mainId, user.getCompanyId());
     *328534402063663126L   1561711770968L 330718300445081610L
     */
    @Test
    public void testGetOrderDetail(){
        OrderDetailDto orderDetail = orderListResultService.getOrderDetail(330303425990361236L, 11113174155L);
        System.out.println(orderDetail);

    }

    /**
     * 订单项目 发货单信息
     * orderCargoRestService.getOrderCargoPrintList(orderId, companyId);
     *30303425990361236
     */
    @Test
    public void testOrderCargoPrintList(){
        List<OrderCargoPrintDto> orderCargoPrintList = orderCargoRestService.getOrderCargoPrintList(330718300445081610L, 11113174155L);
        System.out.println(orderCargoPrintList);

    }

    /**
     * 采购项目：成交定价采购品-供应商维度
     * 雷阳阳 7-10 09:17:27
     * cn.bidlink.procurement.purchase.cloud.service.QuotedPriceRestService#getDealItemSupplierTitlePro 这个是查询供应商的接口
     *
     */
    @Test
    public void testSupplierTitlePro(){
        String aNull = quotedPriceRestService.getDealItemSupplierTitlePro(331038846915445344L, 11113174493L, 2, false, null, null);
        System.out.println(aNull);

    }

    /**
     * 采购项目：成交定价采购品-供应商维度
     * cn.bidlink.procurement.purchase.cloud.service.DealItemSupplierRestService#findItemSupplierVoList 这个是查询数据的接口
     *
     */
    @Test
    public void testfindItemSupplierVoList(){
        DealItemSupplierVo itemSupplierVoList = dealItemSupplierRestService.findItemSupplierVoList(331038846915445344L, 11113174493L, 11113178545L, 2, false, null, null);
        System.out.println(itemSupplierVoList);

    }

    /**
     * 采购项目：审批导出
     * cn.bidlink.procurement.purchase.cloud.service.ProjectApprovalRestService#findApproveRecord
     */
    @Test
    public void testfindApproveRecord(){
        Map<String, Object> approveRecord = projectApprovalRestService.findApproveRecord(331038846915444932L, 11113174493L);
        System.out.println(approveRecord);

    }

    /**
     * 采购项目：审批导出项目基本信息
     * cn.bidlink.procurement.purchase.cloud.service.ProjectRestService#getProjectBase
     */
    @Test
    public void testGetProjectBase(){
        ProjectDto projectBase = projectRestService.getProjectBase(331038846915445344L, 11113174493L);
        System.out.println(projectBase);

    }

    /**
     * 采购项目：审批导出项目基本信息
     * cn.bidlink.procurement.approve.cloud.service.RestWorkflowApproveService#findWorkflowApproveApproveRecodes
     */
    @Test
    public void testFindWorkflowApproveApproveRecodes(){
        ApproveRecodeParamDto approveRecodeParamDto = new ApproveRecodeParamDto();
        /*approveRecodeParamDto.setProjectId(Long.valueOf("331038846915444932"));
        approveRecodeParamDto.setCompanyId(Long.valueOf("11113174493"));
        //approveRecodeParamDto.setExecutor(UserContext.getUser().getLoginName());
        approveRecodeParamDto.setExecutor("testpk003");
        approveRecodeParamDto.setModule(3);*/
        approveRecodeParamDto.setProjectId(Long.valueOf("333555133205970950"));
        approveRecodeParamDto.setCompanyId(Long.valueOf("11113174218"));
        approveRecodeParamDto.setExecutor("lyy002");
        approveRecodeParamDto.setModule(3);
        ServiceResult<Map<String, List<List<ApproveRecodeDto>>>> workflowApproveApproveRecodes = restWorkflowApproveService.findWorkflowApproveApproveRecodes(approveRecodeParamDto);
        System.out.println(workflowApproveApproveRecodes);

    }

    /**
     * 采购项目：审批导出项目基本信息
     * cn.bidlink.procurement.purchase.cloud.service.ProjectRestService#getProjectBase
     */
    @Test
    public void testFindBidSubProjectByPk(){
        BidSubProject bidSubProjectByPk = bidSubProjectRestService.findBidSubProjectByPk(328920271245279269L);
        System.out.println(bidSubProjectByPk);

    }

    /**
     * 招标项目：审批导出项目基本信息
     * cn.bidlink.procurement.purchase.cloud.service.ProjectRestService#getProjectBase
     */
    @Test
    public void testGetBidReportDetail(){
        BidSubProject bidSubProjectByPk = bidSubProjectRestService.getBidReportDetail(216941622867263488L);
        System.out.println(bidSubProjectByPk);

    }

    /**
     * 采购项目：统一增加币种
     * cn.bidlink.procurement.purchase.cloud.service.ProjectRestService#getProjectBase
     */
    @Test
    public void testGetDubboAppsetPrivilegeModuleNodeControlService(){
        ServiceResult<List<AppsetPrivilegeControlDto>> controlServiceResult = dubboAppsetPrivilegeModuleNodeControlService.findPrivilegeListByModuleCode(PrivilegeEnum.PrivileModuleEnum.PURCHASE.getCode(), 11113174493L);
        System.out.println(controlServiceResult);

    }



}
