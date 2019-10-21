package cn.bidlink.report.app.service;

import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeParamDto;
import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierItemQuoteVo;
import cn.bidlink.procurement.purchase.cloud.vo.DealItemSupplierVo;
import cn.bidlink.report.app.model.vo.purchase.*;

import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:52 2019-05-31
 * @Description : 采购项目数据源组装逻辑代理接口
 */
public interface PurchaseProxyService {

    List<Map<String , Object>> getSupplierQuoteData(Long projectId, Long companyId);

    List<Map<String , Object>> getSupplierItemList(Long projectId, Long companyId);

    List<QuoteSeparatelyVo> findItemSupplierQuoteInfoWithTableDate(Long companyId, Long projectId, Boolean quoteResult, Integer pageNum, Integer pageSize);

    List<ProjectSupplierItemQuoteVo> findItemSupplierQuoteInfo(Long companyId, Long projectId, Boolean quoteResult, Integer pageNum, Integer pageSize);

    List<TotalItemsSeparatelyVo> findPrintingSettingItem(Long projectId, Long companyId);

    List<TotalItemsSeparatelyVo> findPrintingSettingItemWithSchemeSubentry(Long projectId, Long companyId);

    List<TotalItemsSeparatelyVo> findPrintingSettingItemWithSchemeSumup(Long projectId, Long companyId);

    List<Map> findTransactionResult(Long projectId, Long companyId,Boolean showUnconfirmed);

    List findPurchaseDealItem(Long projectId, Long companyId,Boolean showUnconfirmed);

    List<Map<String, Object>> findPurchaseDealItemWithPurchase(Long projectId, Long companyId,Boolean showUnconfirmed);

    List<ProjectSupplierDealVo> findProcessedDealList(Long projectId, Long companyId, Boolean showUnConfirmed);

    List<ApproveRecodeDtoVO> findWorkflowApproveProjectDetail(ApproveRecodeParamDto approveRecodeParamDto);

    List<TaskRecodeDtoVO> findWorkflowApproveProjectDetailTwo(ApproveRecodeParamDto approveRecodeParamDto);

    List<TaskRecodeDtoVO> findWorkflowApproveSerialNumber(ApproveRecodeParamDto approveRecodeParamDto);

    List<QuoteSeparatelyVo> getColSpanColumnsValue(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal);

    List<QuoteSeparatelyVo> getColSpanColumnsTitle(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal);

    DealItemSupplierVo quotationPricing(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal);

    DealItemSupplierVo quotationPricing(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag);

    List<QuoteSeparatelyVo> purQuoteTitle(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal);

    List<QuoteSeparatelyVo> purQuoteValue(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal);

    List<QuoteSeparatelyVo> priceSupplierTitle(Long projectId, Long companyId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal);

    List<Map<String,String>> getBargainItemList(Long projectId, Long companyId);

    List<Map<String,String>> getBargainInfoList(Long projectId, Long companyId);

    List<Map<String,String>> totalQuoteTitle(Long projectId, Boolean showNoDeal);

    List<Map<String,String>> totalQuoteTitle(Long projectId);

    List<Map> totalQuoteValue(Long projectId, Boolean showNoDeal);

    List<Map> totalQuoteValue(Long projectId);
}
