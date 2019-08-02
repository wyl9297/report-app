package cn.bidlink.report.app.service;

import java.util.List;
import java.util.Map;


public interface ContractProxyService {

    List<Map<String,String>> getContractHeadAndTailData(Long companyId,Long projectId,Long userId);

    List<Map<String,Object>> getContractQuoteItemData(Long companyId,Long projectId);

}
