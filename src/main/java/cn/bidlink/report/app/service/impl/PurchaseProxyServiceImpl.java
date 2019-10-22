package cn.bidlink.report.app.service.impl;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.framework.common.entity.TableData;
import cn.bidlink.procurement.approve.cloud.service.RestWorkflowApproveService;
import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeDto;
import cn.bidlink.procurement.approve.dal.dto.ApproveRecodeParamDto;
import cn.bidlink.procurement.approve.dal.dto.TaskRecodeDto;
import cn.bidlink.procurement.purchase.cloud.dto.*;
import cn.bidlink.procurement.purchase.cloud.service.*;
import cn.bidlink.procurement.purchase.cloud.vo.DealItemSupplierVo;
import cn.bidlink.report.app.model.vo.purchase.*;
import cn.bidlink.report.app.service.PurchaseProxyService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:52 2019-05-31
 * @Description :
 */
@Service
public class PurchaseProxyServiceImpl implements PurchaseProxyService {

    @Autowired
    private QuotedPriceRestService quotedPriceRestService;

    @Autowired
    private ProjectRestService projectRestService;

    @Autowired
    private ProjectSupplierRestService projectSupplierRestService;

    @Autowired
    private DealPriceRestService dealPriceRestService;

    @Autowired
    private ProjectApprovalRestService projectApprovalRestService;

    @Autowired
    private RestWorkflowApproveService restWorkflowApproveService;

    private Logger logger = LoggerFactory.getLogger(TenderProxyServiceImpl.class);
    @Autowired
    private DealItemSupplierRestService dealItemSupplierRestService;

    @Autowired
    private ProjectBargainRestService projectBargainRestService;



    @Override
    public List<Map<String, Object>> getSupplierQuoteData(Long projectId, Long companyId) {
        Map<String, Object> supplierQuoteData = quotedPriceRestService.getSupplierQuoteData(Long.valueOf(projectId), Long.valueOf(companyId));
        String realQuoteStopTime = (String) supplierQuoteData.get("realQuoteStopTime");
        String supplierQuotedInfo = (String) supplierQuoteData.get("supplierQuotedInfo");
        List<Map<String , Object>> supplierList = (List<Map<String, Object>>) supplierQuoteData.get("supplierList");
        List<Map<String , Object>> supplierItemList = (List<Map<String, Object>>) supplierQuoteData.get("supplierItemList");
        supplierList.forEach(map ->{
            map.put("realQuoteStopTime", realQuoteStopTime);
            map.put("supplierQuotedInfo", supplierQuotedInfo);
            supplierItemList.forEach(item -> {
                String supplierName = (String) item.get("supplierName");
                String supplierListCode = (String) item.get("supplierListCode");
                String[] split = supplierName.split("：");
                if(map.get("supplierName").equals(split[1])){
                    map.put("supplierListCode", supplierListCode);
                }
            });
        });
        return supplierList;
    }

    @Override
    public List<Map<String, Object>> getSupplierItemList(Long projectId, Long companyId) {
        Map<String, Object> supplierQuoteData = quotedPriceRestService.getSupplierQuoteData(Long.valueOf(projectId), Long.valueOf(companyId));

        List<Map<String , Object>> supplierItemList = (List<Map<String, Object>>) supplierQuoteData.get("supplierItemList");
        List<Map<String , Object>> supplierList1 = (List<Map<String, Object>>) supplierQuoteData.get("supplierList");
        List<Map<String , Object>> temp  = new ArrayList<>();
        int i = 0;
        for (Map<String , Object> map:supplierItemList) {
            Map<String, Object> map1 = supplierList1.get(i);
            i++;
            String  supplierName = (String) map.get("supplierName");
            String supplierListCode = (String) map.get("supplierListCode");
            List<Map<String , Object>> supplierList = (List<Map<String, Object>>) supplierQuoteData.get(supplierListCode);
            supplierList.forEach( list -> {
                list.put("supplierName", supplierName);
                list.put("supplierListCode", supplierListCode);
                list.put("supplierId", map1.get("supplierId"));
                temp.add(list);
            });
        }
        return temp;
    }


    @Override
    public List<QuoteSeparatelyVo> findItemSupplierQuoteInfoWithTableDate(Long companyId, Long projectId, Boolean quoteResult, Integer pageNum, Integer pageSize) {
        TableData<ProjectItemSupplierQuoteDto> itemSupplierQuoteInfo = quotedPriceRestService.findItemSupplierQuoteInfo(companyId, projectId, quoteResult, pageNum, pageSize);
        List<ProjectItemSupplierQuoteDto> projectItemSupplierQuoteDto = itemSupplierQuoteInfo.getTableData();

        ArrayList<QuoteSeparatelyVo> list = new ArrayList<>();
        projectItemSupplierQuoteDto.forEach(projectItemSupplier ->{
            List<ProjectSupplierItemQuoteVo> supplierList = projectItemSupplier.getSupplierList();
            supplierList.forEach(supplier ->{
                String quoteItem = supplier.getQuoteItem();
                Map <String , Object> mapTypes = JSON.parseObject(quoteItem);
                for (String obj : mapTypes.keySet()){
                    QuoteSeparatelyVo quoteSeparatelyVo = new QuoteSeparatelyVo();
                    quoteSeparatelyVo.setKey(obj);
                    String o = String.valueOf(mapTypes.get(obj));
                    if ( !"".equals(o) && o != null){
                        quoteSeparatelyVo.setValue(o);
                    }else {
                        quoteSeparatelyVo.setValue("");
                    }
                    quoteSeparatelyVo.setProjectItemId(supplier.getProjectItemId());
                    quoteSeparatelyVo.setSupplierId(supplier.getSupplierId());
                    list.add(quoteSeparatelyVo);
                }
            });
        });

        return list;
    }

    @Override
    public List<ProjectSupplierItemQuoteVo> findItemSupplierQuoteInfo(Long companyId, Long projectId, Boolean quoteResult, Integer pageNum, Integer pageSize) {
        TableData<ProjectItemSupplierQuoteDto> itemSupplierQuoteInfo = quotedPriceRestService.findItemSupplierQuoteInfo(companyId, projectId, quoteResult, pageNum, pageSize);
        List<ProjectItemSupplierQuoteDto> projectItemSupplierQuoteDto = itemSupplierQuoteInfo.getTableData();

        ArrayList<ProjectSupplierItemQuoteVo> list = new ArrayList<>();
        projectItemSupplierQuoteDto.forEach(projectItemSupplier ->{
            List<ProjectSupplierItemQuoteVo> supplierList = projectItemSupplier.getSupplierList();
            supplierList.forEach(supplier ->{
                list.add(supplier);
            });
        });
        return list;
    }

    @Override
    public List<TotalItemsSeparatelyVo> findPrintingSettingItem(Long projectId, Long companyId) {
        Map<String, Object> totalItemsSeparately = projectRestService.findPrintingSettingItem(projectId, companyId);
        ArrayList<TotalItemsSeparatelyVo> list = new ArrayList<>();
        String ss = (String)totalItemsSeparately.get("name");
        TotalItemsSeparatelyVo totalItemsSeparatelyVo = new TotalItemsSeparatelyVo();
        totalItemsSeparatelyVo.setName(ss);
        list.add(totalItemsSeparatelyVo);
        return list;
    }

    @Override
    public List<TotalItemsSeparatelyVo> findPrintingSettingItemWithSchemeSubentry(Long projectId, Long companyId) {
        Map<String, Object> totalItemsSeparately = projectRestService.findPrintingSettingItem(projectId, companyId);

        ArrayList<TotalItemsSeparatelyVo> listnull = new ArrayList<>();
        ArrayList<TotalItemsSeparatelyVo> list = new ArrayList<>();
        String ss = (String)totalItemsSeparately.get("schemeSubentry");
        if (StringUtils.isNotBlank(ss)){
            Arrays.stream(ss.split(";")).forEach(s -> {
                TotalItemsSeparatelyVo totalItemsSeparatelyVo = new TotalItemsSeparatelyVo();
                totalItemsSeparatelyVo.setSchemeSubentry(s);
                list.add(totalItemsSeparatelyVo);
            } );
            return list;
        }else {
            return listnull;
        }
    }

    @Override
    public List<TotalItemsSeparatelyVo> findPrintingSettingItemWithSchemeSumup(Long projectId, Long companyId) {
        Map<String, Object> totalItemsSeparately = projectRestService.findPrintingSettingItem(projectId, companyId);
        ArrayList<TotalItemsSeparatelyVo> listnull = new ArrayList<>();
        ArrayList<TotalItemsSeparatelyVo> list = new ArrayList<>();
        String ss = (String)totalItemsSeparately.get("schemeSumup");
        if (StringUtils.isNotBlank(ss)){
            Arrays.stream(ss.split(";")).forEach( s -> {
                TotalItemsSeparatelyVo totalItemsSeparatelyVo = new TotalItemsSeparatelyVo();
                totalItemsSeparatelyVo.setSchemeSumup(s);
                list.add(totalItemsSeparatelyVo);
            } );
            return list;
        }else {
            return listnull;
        }
    }

    @Override
    public List<Map> findTransactionResult(Long projectId, Long companyId,Boolean showUnconfirmed) {
        List<Map> itemList = new ArrayList<>();
        Map<String, Object> transactionResult1 = projectSupplierRestService.findTransactionResult(projectId, companyId,showUnconfirmed);
        List<Map> supplierList = (List<Map>)transactionResult1.get("supplierList");
        supplierList.forEach( map -> {
            String supplierListCode = (String)map.get("supplierListCode");
            List<Map> itemMapList =  (List<Map>)transactionResult1.get(supplierListCode);
            itemMapList.forEach(itemMap -> {
                itemMap.put("supplierListCode",supplierListCode);
                itemList.add(itemMap);
            });
        });
        return itemList;
    }

    @Override
    public List<PurchasesWithSupplierItemVO> findPurchaseDealItem(Long projectId, Long companyId ,Boolean showUnconfirmed) {
        Map<String, Object> purchaseDealItem = dealPriceRestService.findPurchaseDealItem(projectId, companyId,showUnconfirmed);
        // 成交结果信息[采购品 + 供应商]
        List<Map<String, Object>> finalDealList = (List<Map<String, Object>>) purchaseDealItem.get("finalDealList");
        List list = new ArrayList();
        finalDealList.forEach(map -> {
            List<Map<String, Object>> supplierList = (List<Map<String, Object>>) map.get("supplierList");
            if ( null != supplierList ){
                supplierList.forEach(supMap -> {
                    PurchasesWithSupplierItemVO item = new PurchasesWithSupplierItemVO();
                    // 采购品信息
                    item.setMarketPrice((String) map.get("marketPrice"));
                    item.setCode((String) map.get("code"));
                    item.setUnitName((String) map.get("unitName"));
                    item.setPurpose((String) map.get("purpose"));
                    item.setPurpose((String) map.get("userDept"));
                    item.setProjectItemId((String) map.get("projectItemId"));
                    item.setSpec((String) map.get("spec"));
                    item.setPurchaseAmount((String) map.get("purchaseAmount"));
                    item.setTechParameters((String) map.get("techParameters"));
                    item.setName((String) map.get("name"));
                    item.setComment((String) map.get("comment"));
                    item.setPlanPrice((String) map.get("planPrice"));
                    item.setProjectId((String) map.get("projectId"));
                    item.setAppliedEnterprise((String) map.get("appliedEnterprise"));
                    item.setAppliedPersonAndPhone((String) map.get("appliedPersonAndPhone"));
                    item.setNeedTime((String) map.get("needTime"));
                    // 供应商数据
                    item.setDealUnitPrice((String) supMap.get("dealUnitPrice"));
                    item.setDealRation((String) supMap.get("dealRation"));
                    item.setQuoteAmount((String) supMap.get("quoteAmount"));
                    item.setDealTotalPrice((String) supMap.get("dealTotalPrice"));
                    item.setSupplierName((String) supMap.get("supplierName"));
                    item.setDealAmount((String) supMap.get("dealAmount"));
                    item.setQuoteUnitPrice((String) supMap.get("quoteUnitPrice"));
                    item.setQuoteTotalPrice((String) supMap.get("quoteTotalPrice"));
                    item.setSupplierId((String) supMap.get("supplierId"));
                    item.setDealDescription((String) supMap.get("dealDescription"));
                    item.setCurrency((String) supMap.get("currency"));
                    list.add(item);
                });
            }
        });
        return list;
    }

    /**
     *  成交结果  采购品信息
     * @param projectId
     * @param companyId
     * @return
     */
    @Override
    public List<Map<String, Object>> findPurchaseDealItemWithPurchase(Long projectId, Long companyId,Boolean showUnconfirmed) {
        Map<String, Object> purchaseDealItem = dealPriceRestService.findPurchaseDealItem(projectId, companyId,showUnconfirmed);
        // 成交结果信息[采购品 + 供应商]
        List<Map<String, Object>> finalDealList = (List<Map<String, Object>>) purchaseDealItem.get("finalDealList");
        List list = new ArrayList();
        finalDealList.forEach(map -> {
            PurchasesWithSupplierItemVO item = new PurchasesWithSupplierItemVO();
            // 采购品信息
            item.setMarketPrice((String) map.get("marketPrice"));
            item.setCode((String) map.get("code"));
            item.setUnitName((String) map.get("unitName"));
            item.setPurpose((String) map.get("purpose"));
            item.setPurpose((String) map.get("userDept"));
            item.setProjectItemId((String) map.get("projectItemId"));
            item.setSpec((String) map.get("spec"));
            item.setPurchaseAmount((String) map.get("purchaseAmount"));
            item.setTechParameters((String) map.get("techParameters"));
            item.setName((String) map.get("name"));
            item.setComment((String) map.get("comment"));
            item.setPlanPrice((String) map.get("planPrice"));
            item.setProjectId((String) map.get("projectId"));
            item.setAppliedEnterprise((String) map.get("appliedEnterprise"));
            item.setAppliedPersonAndPhone((String) map.get("appliedPersonAndPhone"));
            item.setNeedTime((String) map.get("needTime"));
            list.add(item);
        });
        return list;
    }

    @Override
    public List<ProjectSupplierDealVo> findProcessedDealList(Long projectId, Long companyId, Boolean showUnConfirmed) {
        //查询数据并返回
        ResultTabListVo<ProjectSupplierItemDealDto> processedDealList = dealPriceRestService.findProcessedDealList(projectId, companyId, showUnConfirmed , null , null);
        BigDecimal dealTotalPrice = processedDealList.getDealTotalPrice();
        List<ProjectSupplierItemDealDto> tableData = processedDealList.getTableData();
        List<ProjectSupplierDealVo> list = new ArrayList<>();

        tableData.forEach(supplier -> {
            List<ProjectSupplierDealDto> supplierList = supplier.getSupplierList();
            supplierList.forEach(dto -> {
                ProjectSupplierDealVo vo = new ProjectSupplierDealVo();
                try {
                    BeanUtils.copyProperties(vo, dto);
                    vo.setDealTotalPriceWithProject(dealTotalPrice);
                    list.add(vo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        });
        return list;
    }

    @Override
    public List<ApproveRecodeDtoVO> findWorkflowApproveProjectDetail(ApproveRecodeParamDto approveRecodeParamDto) {

        ServiceResult<Map<String, List<List<ApproveRecodeDto>>>> workflowApproveApproveRecodes = restWorkflowApproveService.findWorkflowApproveApproveRecodes(approveRecodeParamDto);
        List<ApproveRecodeDtoVO> approve = new ArrayList<>();

        Map<String, List<List<ApproveRecodeDto>>> result = workflowApproveApproveRecodes.getResult();
        if (null != result && result.size()>0){

            for (String key : result.keySet()) {
                int seq=0;
                List<List<ApproveRecodeDto>> k = result.get(key);

                for (int i = 0; i < k.size(); i++) {
                    seq++;
                    List<ApproveRecodeDto> approveRecodeDtos = k.get(i);
                    for (int j = 0; j < approveRecodeDtos.size(); j++) {
                        ApproveRecodeDto approveRecodeDto = approveRecodeDtos.get(j);
                        ApproveRecodeDtoVO approveRecodeDtoVO = new ApproveRecodeDtoVO();
                        approveRecodeDtoVO.setIndex(approveRecodeDto.getIndex());
                        approveRecodeDtoVO.setNodeType(approveRecodeDto.getNodeType());
                        approveRecodeDtoVO.setNodeTypeName(approveRecodeDto.getNodeTypeName());
                        approveRecodeDtoVO.setNodeApproveResult(approveRecodeDto.getNodeApproveResult());
                        approveRecodeDtoVO.setModuleName(approveRecodeDto.getModuleName());
                        approveRecodeDtoVO.setModuleNodeName(approveRecodeDto.getModuleNodeName());
                        approveRecodeDtoVO.setCompanyId(approveRecodeDto.getCompanyId());
                        approveRecodeDtoVO.setModule(approveRecodeDto.getModule());
                        approveRecodeDtoVO.setModuleNodeType(approveRecodeDto.getModuleNodeType());
                        approveRecodeDtoVO.setSerialNumber(seq);

                        approve.add(approveRecodeDtoVO);
                    }
                }
            }

        }
        return approve;

    }

    @Override
    public List<TaskRecodeDtoVO> findWorkflowApproveProjectDetailTwo(ApproveRecodeParamDto approveRecodeParamDto) {

        ServiceResult<Map<String, List<List<ApproveRecodeDto>>>> workflowApproveApproveRecodes = restWorkflowApproveService.findWorkflowApproveApproveRecodes(approveRecodeParamDto);

        List<TaskRecodeDtoVO> approve = new ArrayList<>();

        Map<String, List<List<ApproveRecodeDto>>> result = workflowApproveApproveRecodes.getResult();
        if (null != result && result.size()>0){

            for (String key : result.keySet()) {
                int seq=0;
                List<List<ApproveRecodeDto>> k = result.get(key);
                for (int i = 0; i < k.size(); i++) {
                    seq++;
                    List<ApproveRecodeDto> approveRecodeDtos = k.get(i);
                    for (int j = 0; j < approveRecodeDtos.size(); j++) {
                        ApproveRecodeDto approveRecodeDto = approveRecodeDtos.get(j);
                        List<TaskRecodeDto> taskRecodeDtos = approveRecodeDto.getTaskRecodeDtos();
                        for (int l = 0; l < taskRecodeDtos.size(); l++) {
                            TaskRecodeDto taskRecodeDto = taskRecodeDtos.get(l);
                            TaskRecodeDtoVO taskRecodeDtoVO = new TaskRecodeDtoVO();
                            taskRecodeDtoVO.setUserName(taskRecodeDto.getUserName());
                            taskRecodeDtoVO.setLoginName(taskRecodeDto.getLoginName());
                            taskRecodeDtoVO.setApproveTime(taskRecodeDto.getApproveTime());
                            taskRecodeDtoVO.setApproveSuggestion(taskRecodeDto.getApproveSuggestion());
                            taskRecodeDtoVO.setTaskApproveResult(taskRecodeDto.getTaskApproveResult());
                            taskRecodeDtoVO.setTaskId(taskRecodeDto.getTaskId());
                            taskRecodeDtoVO.setCompanyId(approveRecodeDto.getCompanyId());
                            taskRecodeDtoVO.setModule(approveRecodeDto.getModule());
                            taskRecodeDtoVO.setModuleNodeType(approveRecodeDto.getModuleNodeType());
                            taskRecodeDtoVO.setIndex(approveRecodeDto.getIndex());
                            taskRecodeDtoVO.setNodeType(approveRecodeDto.getNodeType());
                            taskRecodeDtoVO.setNodeTypeName(approveRecodeDto.getNodeTypeName());

                            taskRecodeDtoVO.setSerialNumber(seq);
                            approve.add(taskRecodeDtoVO);
                        }
                    }
                }
            }

        }
        return approve;
    }

    @Override
    public List<TaskRecodeDtoVO> findWorkflowApproveSerialNumber(ApproveRecodeParamDto approveRecodeParamDto) {

        ServiceResult<Map<String, List<List<ApproveRecodeDto>>>> workflowApproveApproveRecodes = restWorkflowApproveService.findWorkflowApproveApproveRecodes(approveRecodeParamDto);
        List<TaskRecodeDtoVO> approve = new ArrayList<>();

        Map<String, List<List<ApproveRecodeDto>>> result = workflowApproveApproveRecodes.getResult();
        if (null != result && result.size()>0){
            for (String key : result.keySet()) {
                int seq=0;
                List<List<ApproveRecodeDto>> k = result.get(key);

                for (int i = 0; i < k.size(); i++) {
                    seq++;
                    Integer moduleNodeType = k.get(i).get(0).getModuleNodeType();
                    TaskRecodeDtoVO taskRecodeDtoVO = new TaskRecodeDtoVO();
                    taskRecodeDtoVO.setSerialNumber(seq);
                    taskRecodeDtoVO.setModuleNodeType(moduleNodeType);
                    approve.add(taskRecodeDtoVO);

                }
            }

        }
        return approve;
    }

    @Override
    public List<QuoteSeparatelyVo> getColSpanColumnsValue(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal) {
        // 报价一览表    handStatus  1   viewFlag : true     supplierIds : null
        // 成交定价 handStatus  2       viewFlag : false    supplierIds : null
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, companyId, userId, handStatus, viewFlag, showNoDeal);
        String colSpanColumns = itemSupplierVoList.getColSpanColumns();

        List<QuoteSeparatelyVo> objects = new ArrayList<>();
        List<QuoteSeparatelyVo> objectsnull = new ArrayList<>();
        if(StringUtils.isNotBlank(colSpanColumns)){
            JSONArray jsonObject = JSONArray.fromObject(colSpanColumns);
            List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonObject;
            for (int i = 0; i < mapListJson.size(); i++) {
                Map<String, Object> json = mapListJson.get(i);
                for (String c : json.keySet()) {
                    //Map<String, Object> tableBody = (Map<String, Object>) json.get("tableBody");
                    String st = String.valueOf(json.get("tableBody"));
                    JSONArray jst = JSONArray.fromObject(st);
                    List<Map<String, Object>> tableBo = (List<Map<String, Object>>) jst;
                    for (int y = 0; y < tableBo.size(); y++) {
                        Map<String, Object> tableBody = tableBo.get(y);
                        for (String tab : tableBody.keySet()) {

                            QuoteSeparatelyVo quoteSeparatelyVo = new QuoteSeparatelyVo();
                            String key = String.valueOf(tableBody.get("key"));
                            quoteSeparatelyVo.setKey(key);
                            String value = String.valueOf(tableBody.get(key));
                            if(!"null".equals(value) &&  StringUtils.isNotEmpty(value) ){
                                quoteSeparatelyVo.setValue(value);
                            }
                            //key":"multi_supplier_11113173863(json内容)
                            if (key.contains("_")){
                                Long supplierId = Long.valueOf(key.substring(15));
                                quoteSeparatelyVo.setSupplierId(supplierId);
                            }
                            objects.add(quoteSeparatelyVo);

                        }
                    }
                }
            }
            return objects;
        }else {
            return objectsnull;
        }
    }

    @Override
    public List<QuoteSeparatelyVo> getColSpanColumnsTitle(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, companyId, userId, handStatus, viewFlag, showNoDeal);
        String colSpanColumns = itemSupplierVoList.getColSpanColumns();

        List<QuoteSeparatelyVo> objects = new ArrayList<>();
        List<QuoteSeparatelyVo> objectsnull = new ArrayList<>();
        if(StringUtils.isNotBlank(colSpanColumns)){
            JSONArray jsonObject = JSONArray.fromObject(colSpanColumns);
            List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonObject;
            for (int i = 0; i < mapListJson.size(); i++) {
                Map<String, Object> json = mapListJson.get(i);
                QuoteSeparatelyVo quoteSeparatelyVo = new QuoteSeparatelyVo();
                String title = String.valueOf(json.get("total"));
                quoteSeparatelyVo.setTitle(title);
                String key = String.valueOf(json.get("key"));
                quoteSeparatelyVo.setKey(key);
                objects.add(quoteSeparatelyVo);
            }
            return objects;

        }else {
            return objectsnull;
        }
    }

    // 报价一览表采购品-供应商  成交定价-采购品供应商  共用查询接口
    @Override
    public DealItemSupplierVo quotationPricing(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal) {
        DealItemSupplierVo itemSupplierVoList = dealItemSupplierRestService.findItemSupplierVoList(projectId, companyId, userId, handStatus, viewFlag, null, true, showNoDeal);
        return itemSupplierVoList;
    }

    @Override
    public DealItemSupplierVo quotationPricing(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag) {
        DealItemSupplierVo itemSupplierVoList = dealItemSupplierRestService.findItemSupplierVoList(projectId, companyId, userId, handStatus, viewFlag, null, true);
        return itemSupplierVoList;
    }

    @Override
    public List<QuoteSeparatelyVo> purQuoteTitle(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, companyId, userId, handStatus, viewFlag, showNoDeal);
        List<QuoteSeparatelyVo> bidding = new ArrayList<>();
        List<QuoteSeparatelyVo> biddingNull = new ArrayList<>();
        List<Map<String, Object>> tableData = itemSupplierVoList.getTableData();
        if ( null != tableData && !"null".equals(tableData) && tableData.size()>0){
            //得到每一个采购品
            tableData.forEach(table->{
                List<LinkedHashMap<String, Object>> supplier = (List<LinkedHashMap<String, Object>>) table.get("supplierChildrenColumns");
                for (int i = 0; i < supplier.size(); i++) {
                    LinkedHashMap<String, Object> supplierList = supplier.get(i);
                    if (null != supplierList && supplierList.size()>0){
                        //得到每一个采购品中所有供应商所有分项报价表头
                        for (String s : supplierList.keySet()){
                            QuoteSeparatelyVo quoteSeparatelyVo = new QuoteSeparatelyVo();

                            quoteSeparatelyVo.setKey(String.valueOf(supplierList.get("key")));
                            String title = String.valueOf(String.valueOf(supplierList.get("title")));
                            if(null != supplierList.get("unit")){
                                title = title + "(" + supplierList.get("unit").toString() + ")";
                            }
                            quoteSeparatelyVo.setTitle(title);
                            quoteSeparatelyVo.setProjectItemId(Long.valueOf(String.valueOf(table.get("projectItemId"))));
                            bidding.add(quoteSeparatelyVo);
                        }
                    }
                }
            });
            return bidding;
        }else {
            return biddingNull;
        }
    }

    @Override
    public List<QuoteSeparatelyVo> purQuoteValue(Long projectId, Long companyId, Long userId, Integer handStatus, Boolean viewFlag, Boolean showNoDeal) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, companyId, userId, handStatus, viewFlag, showNoDeal);
        List<QuoteSeparatelyVo> bidding = new ArrayList<>();
        List<QuoteSeparatelyVo> biddingNull = new ArrayList<>();
        List<Map<String, Object>> tableData = itemSupplierVoList.getTableData();
        if ( null != tableData && !"null".equals(tableData) && tableData.size()>0){
            //得到每一个采购品
            tableData.forEach(table->{
                List<LinkedHashMap<String, Object>> supplier = (List<LinkedHashMap<String, Object>>) table.get("supplierList");
                for (int i = 0; i < supplier.size(); i++) {
                    LinkedHashMap<String, Object> supplierList = supplier.get(i);
                    if (null != supplierList && supplierList.size()>0){
                        //得到每一个采购品中所有供应商所有报价
                        for (String s : supplierList.keySet()){
                            LinkedHashMap<String, Object> show = (LinkedHashMap<String, Object>) supplierList.get("show");
                            if (null != show && show.size()>0){
                                //得到每一个供应商报价11项
                                for (String sho : show.keySet()){
                                    QuoteSeparatelyVo quoteSeparatelyVo = new QuoteSeparatelyVo();
                                    quoteSeparatelyVo.setKey(sho);
                                    String o = String.valueOf(show.get(sho));
                                    if ( !"null".equals(o) && o != null){
                                        quoteSeparatelyVo.setValue(o);
                                    }else {
                                        quoteSeparatelyVo.setValue("");
                                    }
                                    quoteSeparatelyVo.setSupplierId(Long.valueOf(String.valueOf(supplierList.get("supplierId"))));
                                    quoteSeparatelyVo.setSupplierProjectItemId(Long.valueOf(String.valueOf(supplierList.get("supplierProjectItemId"))));
                                    if(table.get("directoryId") != null){
                                        quoteSeparatelyVo.setDirectoryId(Long.valueOf(String.valueOf(table.get("directoryId"))));
                                    }
                                    quoteSeparatelyVo.setProjectItemId(Long.valueOf(String.valueOf(table.get("projectItemId"))));

                                    bidding.add(quoteSeparatelyVo);
                                }
                            }
                        }
                    }
                }
            });
            return bidding;
        }else {
            return biddingNull;
        }
    }

    @Override
    public List<QuoteSeparatelyVo> priceSupplierTitle(Long projectId, Long companyId,Integer handStatus, Boolean viewFlag, Boolean showNoDeal) {
        String dealItemSupplierTitlePro = quotedPriceRestService.getDealItemSupplierTitlePro(projectId, companyId, handStatus, viewFlag, null, true, showNoDeal);
        List<QuoteSeparatelyVo> objects = new ArrayList<>();
        List<QuoteSeparatelyVo> objectsnull = new ArrayList<>();
        if (!"[]".equals(dealItemSupplierTitlePro)  && StringUtils.isNotBlank(dealItemSupplierTitlePro) ){

            JSONArray jsonObject = JSONArray.fromObject(dealItemSupplierTitlePro);
            List<Map<String, Object>> mapListJson = (List<Map<String, Object>>) jsonObject;
            for (int i = 0; i < mapListJson.size(); i++) {
                Map<String, Object> col = mapListJson.get(i);
                for (String c : col.keySet()) {
                    QuoteSeparatelyVo quoteSeparatelyVo = new QuoteSeparatelyVo();
                    String key = String.valueOf(col.get("key"));
                    quoteSeparatelyVo.setKey(key);
                    String title = String.valueOf(col.get("title"));
                    if(null != title && !" ".equals(title)){
                        quoteSeparatelyVo.setTitle(title);
                    }

                    if (key.contains("_")){
                        Long supplierId = Long.valueOf(key.substring(15));
                        quoteSeparatelyVo.setSupplierId(supplierId);
                    }

                    //增加供应商成交状态
                    String dealStatus = String.valueOf(col.get("dealStatus"));
                    if(null != dealStatus && !" ".equals(dealStatus)){
                        quoteSeparatelyVo.setDealStatus((Integer)(col.get("dealStatus")));
                    }

                    //增加供应商名称状态
                    if(null != title && !" ".equals(title)){
                        if (col.get("dealStatus") != null && col.get("dealStatus") != "") {
                            if((Integer)(col.get("dealStatus")) == 1){
                                quoteSeparatelyVo.setDealStatusStr(title+"(未成交)");
                            }else {
                                quoteSeparatelyVo.setDealStatusStr(title+"(已成交)");
                            }
                        }
                    }

                    objects.add(quoteSeparatelyVo);
                }
            }
            return objects;
        }else {
            return objectsnull;
        }
    }

    @Override
    public List<Map<String, String>> getBargainItemList(Long projectId, Long companyId) {
        List<Map<String,String>> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查询数据并返回
        List<BargainAllListVo> allBargainItemList = projectBargainRestService.findAllBargainItemList(projectId, companyId);
        allBargainItemList.forEach(bargainAllListVo -> {
            List<ProjectSupplierDimensionItemDetailVO> itemList = bargainAllListVo.getItemList();
            itemList.forEach( projectSupplierDimensionItemDetailVO -> {
                Map<String,String> map = new HashMap(13,1);
                map.put("supplierName" , bargainAllListVo.getSupplierName());
                map.put("itemName", projectSupplierDimensionItemDetailVO.getName());
                map.put("spec", projectSupplierDimensionItemDetailVO.getSpec());
                map.put("purchaseAmount", projectSupplierDimensionItemDetailVO.getPurchaseAmount().toString());
                map.put("unitName", projectSupplierDimensionItemDetailVO.getUnitName());
                map.put("quoteUnitPrice", projectSupplierDimensionItemDetailVO.getQuoteUnitPrice().toString());
                map.put("afterPrice", projectSupplierDimensionItemDetailVO.getAfterPrice().toString());
                map.put("purchaseRemark", projectSupplierDimensionItemDetailVO.getPurchaseRemark());
                map.put("bargainTime", simpleDateFormat.format(projectSupplierDimensionItemDetailVO.getCreateTime()));
                map.put("needConfirm", projectSupplierDimensionItemDetailVO.getNeedConfirm() == 1 ? "是" : "否" );
                map.put("supplierItemId" , projectSupplierDimensionItemDetailVO.getProjectItemId().toString() );
                map.put("supplierId" , projectSupplierDimensionItemDetailVO.getSupplierId().toString() );
                map.put("currency" , projectSupplierDimensionItemDetailVO.getCurrency() );
                if ( null != projectSupplierDimensionItemDetailVO.getSupplierConfirmTime() ) {
                    map.put("supplierConfirmTime",simpleDateFormat.format(projectSupplierDimensionItemDetailVO.getSupplierConfirmTime()));
                }
                list.add(map);
            });
        });
        return list;
    }

    @Override
    public List<Map<String, String>> getBargainInfoList(Long projectId, Long companyId) {
        List<Map<String,String>> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查询数据并返回
        List<BargainAllListVo> allBargainItemList = projectBargainRestService.findAllBargainItemList(projectId, companyId);
        allBargainItemList.forEach(bargainAllListVo -> {
            List<ProjectSupplierDimensionItemDetailVO> itemList = bargainAllListVo.getItemList();
            itemList.forEach( projectSupplierDimensionItemDetailVO -> {
                List<ProjectBargainVo> bargainList = projectSupplierDimensionItemDetailVO.getBargainList();
                bargainList.forEach(projectBargainVo -> {
                    Map<String,String> map = new HashMap(11,1);
                    map.put("supplierItemId" , projectSupplierDimensionItemDetailVO.getProjectItemId().toString() );
                    map.put("supplierId" , projectSupplierDimensionItemDetailVO.getSupplierId().toString() );
                    map.put("beforePrice", projectBargainVo.getBeforePrice().toString());
                    map.put("afterPrice", projectBargainVo.getAfterPrice().toString());
                    map.put("startMan", bargainAllListVo.getCreateUserName()); //发起人
                    map.put("bargainTime", simpleDateFormat.format(projectSupplierDimensionItemDetailVO.getCreateTime())); //议价时间
                    map.put("needConfirm", projectBargainVo.getNeedConfirm() == 1 ? "是" : "否" ); //是否要求确认
                    if ( null != projectBargainVo.getSupplierConfirmTime() ) {
                        map.put("supplierConfirmTime",simpleDateFormat.format(projectBargainVo.getSupplierConfirmTime()));//确认时间
                    }
                    Integer bargainStatus = projectBargainVo.getBargainStatus();
                    if ( null != bargainStatus ) {
                        switch(bargainStatus){
                            case 1 :
                                map.put("bargainStatus","待确认");
                                break;
                            case 2 :
                                map.put("bargainStatus","已确认");
                                break;
                            case 3 :
                                map.put("bargainStatus","已拒绝");
                                break;
                            case 4 :
                                map.put("bargainStatus","已撤回");
                                break;
                            case 5 :
                                map.put("bargainStatus","未确认");
                                break;
                        };
                    }
                    map.put("bargainReason" , projectBargainVo.getPurchaseRemark() ); //议价原因
                    map.put("response" , projectBargainVo.getSupplierRemark() ); //反馈备注
                    list.add(map);
                });
            });
        });
        return list;
    }

    @Override
    public List<Map<String,String>> totalQuoteTitle(Long projectId, Boolean showNoDeal) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, UserContext.getCompanyId(), UserContext.getUserId(), 1, true, showNoDeal);
        String quoteTotalItems = itemSupplierVoList.getQuoteTotalItems();
        List<Map> mapList = (List<Map>) JSONObject.parse(quoteTotalItems);
        if ( mapList.size() > 0 ) {
            Map map = mapList.get(0);
            Map tableBody = ((List<Map>) map.get("tableBody")).get(0);
            List<Map<String,String>> list = (List<Map<String,String>>) tableBody.get("show");
            list.forEach(m -> {
                String unit = m.get("unit");
                if ( StringUtils.isNotEmpty(unit) ) {
                    String title = m.get("title");
                    m.put("title" , title + "（" + unit + "）" );
                }
            });
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, String>> totalQuoteTitle(Long projectId) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, UserContext.getCompanyId(), UserContext.getUserId(), 1, true);
        String quoteTotalItems = itemSupplierVoList.getQuoteTotalItems();
        List<Map> mapList = (List<Map>) JSONObject.parse(quoteTotalItems);
        if ( mapList.size() > 0 ) {
            Map map = mapList.get(0);
            Map tableBody = ((List<Map>) map.get("tableBody")).get(0);
            List<Map<String,String>> list = (List<Map<String,String>>) tableBody.get("show");
            list.forEach(m -> {
                String unit = m.get("unit");
                if ( StringUtils.isNotEmpty(unit) ) {
                    String title = m.get("title");
                    m.put("title" , title + "（" + unit + "）" );
                }
            });
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Map> totalQuoteValue(Long projectId, Boolean showNoDeal) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, UserContext.getCompanyId(), UserContext.getUserId(), 1, true, showNoDeal);
        String quoteTotalItems = itemSupplierVoList.getQuoteTotalItems();
        List<Map> arrayList = new ArrayList<>();
        List<Map> mapList = (List<Map>) JSONObject.parse(quoteTotalItems);
        if ( mapList.size() > 0 ) {
            Map map1 = mapList.get(0);
            List<Map> list = (List<Map>) map1.get("tableBody");
            list.forEach( map -> {
                if ( !"supplierChildrenColumns".equals(map.get("key")) ) {
                    Map<String,String> show = (Map) map.get("show");
                    show.forEach((k,v) -> {
                        if ( !"supplierId".equals(k)) {
                            Map<String, String> m = new HashMap<>();
                            m.put("supplierId",show.get("supplierId"));
                            m.put("key",k);
                            m.put("value",v);
                            arrayList.add(m);
                        }
                    });
                }
            });
        }
        return arrayList;
    }

    @Override
    public List<Map> totalQuoteValue(Long projectId) {
        DealItemSupplierVo itemSupplierVoList = this.quotationPricing(projectId, UserContext.getCompanyId(), UserContext.getUserId(), 1, true);
        String quoteTotalItems = itemSupplierVoList.getQuoteTotalItems();
        List<Map> arrayList = new ArrayList<>();
        List<Map> mapList = (List<Map>) JSONObject.parse(quoteTotalItems);
        if ( mapList.size() > 0 ) {
            Map map1 = mapList.get(0);
            List<Map> list = (List<Map>) map1.get("tableBody");
            list.forEach( map -> {
                if ( !"supplierChildrenColumns".equals(map.get("key")) ) {
                    Map<String,String> show = (Map) map.get("show");
                    show.forEach((k,v) -> {
                        if ( !"supplierId".equals(k)) {
                            Map<String, String> m = new HashMap<>();
                            m.put("supplierId",show.get("supplierId"));
                            m.put("key",k);
                            m.put("value",v);
                            arrayList.add(m);
                        }
                    });
                }
            });
        }
        return arrayList;
    }
}
