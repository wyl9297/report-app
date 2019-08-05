package cn.bidlink.report.app.service;

import cn.bidlink.report.app.model.vo.tender.BiddingResultDynamicChangeVo;
import cn.bidlink.report.app.model.vo.tender.BiddingResultVo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 招标数据源组装逻辑代理接口
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-05-31 09:23:33
 */
public interface TenderProxyService {

    List<Map<String,Object>> getSeperateBiddingSupplierDataSource(Long subProjectId, String supplierIds);

    List<Map<String,Object>> getMultipleBidItemSupplierDataSource(Long subProjectId, String supplierIds, Integer quoteTurn);

    List<Map<String,Object>> getSeperateBiddingTotalDataSource(Long subProjectId, String supplierIds);

    List<Map<String,Object>> getMultipleBiddingTotalDataSource(Long subProjectId, String supplierIds, Integer quoteTurn);

    List<LinkedHashMap<String, String>> getSeperateBiddingTitleDataSource(Long subProjectId, String supplierIds);

    List<LinkedHashMap<String, String>> getSeperateBiddingTitDataSource(Long subProjectId, String supplierIds, Integer quoteTurn);

    List getDecideBidItemView(Long subProjectId, Integer reserveResult);

    List<BiddingResultDynamicChangeVo> getDecideBidView(Long subProjectId);

    List<BiddingResultDynamicChangeVo> getMultipleQuoteBidView(Long subProjectId, Integer quoteTurn);

    List<BiddingResultDynamicChangeVo> getDecideBidViewKeyTitle(Long subProjectId);

    List<BiddingResultVo> getDecideBidViewProject(Long subProjectId, String quoteTurn);

    List<BiddingResultDynamicChangeVo> getDecideBidSupplierView(Long subProjectId);

    List<BiddingResultDynamicChangeVo> getDecideBidSupplierViewKeyTitle(Long subProjectId);

    List<BiddingResultVo> getDecideBidSupplierViewProject(Long subProjectId);

    List<Map<String , Object>>  getSupplierBidItemList(Long subProjectId);

    List<Map<String , Object>>  getMultipleSupplierBidItemList(Long subProjectId, Integer quoteTurn);

    List<Map<String , Object>>  findQuoteItem(Long subProjectId);

    List<Map<String , Object>>  findMultipleQuoteItem(Long subProjectId, Integer quoteTurn);


}