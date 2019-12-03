package cn.bidlink.report.app.service.impl;

import cn.bidlink.procurement.bidding.cloud.service.BidSupplierItemRestService;
import cn.bidlink.procurement.bidding.cloud.service.BidViewRestService;
import cn.bidlink.report.app.model.vo.tender.*;
import cn.bidlink.report.app.service.TenderProxyService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 招标项目数据源组装逻辑代理类
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-05-31 09:19:07
 */
@Component
public class TenderProxyServiceImpl implements TenderProxyService {

    @Autowired
    BidSupplierItemRestService bidSupplierItemRestService;

    @Autowired
    private BidViewRestService bidViewRestService;

    private Logger logger = LoggerFactory.getLogger(TenderProxyServiceImpl.class);

    @Override
    public List<Map<String, Object>> getSeperateBiddingSupplierDataSource(Long subProjectId, String supplierIds) {
        Map<String, Object> data = bidSupplierItemRestService.getBidItemSupplierColumnList(subProjectId, supplierIds);
        return getSeperateBiddingExtract(data);
    }

    private List<Map<String, Object>> getSeperateBiddingExtract(Map<String, Object> data) {
        List<Map<String,Object>> dataList = (List<Map<String,Object>>)data.get("dataList");
        logger.info("招标项目分项报价接口查出的数据：{}" , dataList);
        List<Map<String, Object>> mapList = buildSeperateData(dataList);
        logger.info("招标项目分项报价转换后的数据：{}" , mapList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getMultipleBidItemSupplierDataSource(Long subProjectId, String supplierIds, Integer quoteTurn) {
        Map<String, Object> multipleBidItemSupplierColumnList = bidSupplierItemRestService.getMultipleBidItemSupplierColumnList(subProjectId, supplierIds, quoteTurn);
        return this.getSeperateBiddingExtract(multipleBidItemSupplierColumnList);
    }

    @Override
    public List<Map<String, Object>> getSeperateBiddingTotalDataSource(Long subProjectId, String supplierIds) {
        Map<String, Object> data = bidSupplierItemRestService.getBidItemSupplierColumnList(subProjectId, supplierIds);
        return getSeperateBiddingTotalExtract(data);
    }

    @Override
    public List<Map<String, Object>> getMultipleBiddingTotalDataSource(Long subProjectId, String supplierIds, Integer quoteTurn) {
        Map<String, Object> multipleBidItemSupplierColumnList = bidSupplierItemRestService.getMultipleBidItemSupplierColumnList(subProjectId, supplierIds, quoteTurn);
        return this.getSeperateBiddingTotalExtract(multipleBidItemSupplierColumnList);
    }

    private List<Map<String, Object>> getSeperateBiddingTotalExtract(Map<String, Object> data) {
        List<Map<String,Object>> totalList = (List<Map<String,Object>>) data.get("totalList");
        List<Map<String,Object>> tableList = (List)totalList.get(0).get("tableBody");
        List<Map<String, Object>> seperateData = new ArrayList<>();
        tableList.forEach(map -> {
            String key = (String)map.get("key");
            if ( StringUtils.isNumeric(key) ) {
                Map<String,Object> show = (Map)map.get("show");
                show.put("supplierId",key);
                seperateData.add(show);
            }
        });
        return seperateData;
    }

    @Override
    public List<LinkedHashMap<String, String>> getSeperateBiddingTitleDataSource(Long subProjectId, String supplierIds) {
        //查询数据并返回
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>)bidSupplierItemRestService.getBidItemSupplierColumnList(subProjectId, supplierIds);
        List<LinkedHashMap<String, Object>> dataTitleList = (List)data.get("dataTitleList");
        List<LinkedHashMap<String, String>> list = new ArrayList<>();
        dataTitleList.forEach( map ->{
            String key = (String)map.get("key");
            if ( StringUtils.isNumeric(key) ) {
                LinkedHashMap<String, String> m = new LinkedHashMap<>();
                m.put("supplierId",key);
                m.put("supplierName" , (String)map.get("title") );
                list.add(m);
            }
        });
        return list;
    }

    @Override
    public List<LinkedHashMap<String, String>> getSeperateBiddingTitDataSource(Long subProjectId, String supplierIds, Integer quoteTurn) {
        //查询数据并返回
        LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>)bidSupplierItemRestService.getMultipleBidItemSupplierColumnList(subProjectId, supplierIds, quoteTurn);
        List<LinkedHashMap<String, Object>> dataTitleList = (List)data.get("dataTitleList");
        List<LinkedHashMap<String, String>> list = new ArrayList<>();
        dataTitleList.forEach( map ->{
            String key = (String)map.get("key");
            if ( StringUtils.isNumeric(key) ) {
                LinkedHashMap<String, String> m = new LinkedHashMap<>();
                m.put("supplierId",key);
                m.put("supplierName" , (String)map.get("title") );
                list.add(m);
            }
        });
        return list;
    }

    @Override
    public List getDecideBidItemView(Long subProjectId, Integer reserveResult) {
        // todo reserveResult：值默认为1
        Map<String, Object> decideBidItemView = bidViewRestService.getDecideBidItemView(subProjectId , reserveResult);
        List<Map<String , Object>> tableData = (List<Map<String, Object>>) decideBidItemView.get("tableData");
        List result = new ArrayList();
        tableData.forEach(map -> {
            List<Map<String , Object>> supplierList = (List<Map<String, Object>>) map.get("supplierList");
            supplierList.forEach( supplier ->{
                WinPurchasesVO vo = new WinPurchasesVO();
                // 采购品数据
                vo.setName((String) map.get("name"));
                vo.setCode((String) map.get("code"));
                vo.setSpec((String) map.get("spec"));
                vo.setTechParameters((String) map.get("techParameters"));
                vo.setQuoteAmount((String) map.get("quoteAmount"));
                vo.setUnitName((String) map.get("unitName"));
                vo.setAppliedPersonAndPhone((String) map.get("appliedPersonAndPhone"));
                vo.setAppliedEnterprise((String) map.get("appliedEnterprise"));
                vo.setUseDept((String) map.get("useDept"));
                vo.setPurpose((String) map.get("purpose"));
                vo.setNeedTime((String) map.get("needTime"));
                vo.setComment((String) map.get("comment"));
                vo.setSupplierId(Long.valueOf(String.valueOf(map.get("supplierId"))));
                vo.setBidProjectItemId(Long.valueOf(String.valueOf(map.get("bidProjectItemId"))));

                // 供应商数据
                vo.setSupplierName((String) supplier.get("supplierName"));
                vo.setQuoteUnitPrice((String) supplier.get("quoteUnitPrice"));
                vo.setListSupplierId(Long.valueOf(String.valueOf(supplier.get("supplierId"))));
                vo.setQuoteTotalPrice((String) supplier.get("quoteTotalPrice"));
                vo.setDealNumber((String) supplier.get("dealNumber"));
                vo.setDealRatio((String) supplier.get("dealRatio"));
                vo.setDealPrice((String) supplier.get("dealPrice"));
                vo.setDealTotalPrice((String) supplier.get("dealTotalPrice"));
                vo.setProjectItemId(Long.valueOf(String.valueOf(supplier.get("projectItemId"))));
                vo.setDealInstruction(String.valueOf(supplier.get("dealInstruction")));
                result.add(vo);
            });
        });
        return result;
    }

    @Override
    public List<BiddingResultDynamicChangeVo> getDecideBidView(Long subProjectIds) {

        List<Map<String, Object>> decideBidSupplierView = bidViewRestService.getDecideBidView(subProjectIds);

        List<BiddingResultDynamicChangeVo> bidding = new ArrayList<>();
        BiddingResultDynamicChangeVo biddingResultDynamicChangeVonull = new BiddingResultDynamicChangeVo();
        decideBidSupplierView.forEach(decideBidSupplier ->{

            LinkedHashMap<String , Object>  item = (LinkedHashMap<String, Object>)decideBidSupplier.get("bidSupplier");
            String totalItems = String.valueOf(item.get("totalItems"));
            if ( !"null".equals(totalItems) && StringUtils.isNotEmpty(totalItems)){
                LinkedHashMap <String , Object> total = JSON.parseObject(String.valueOf(item.get("totalItems")) , LinkedHashMap.class);

                for (String s : total.keySet()){
                    BiddingResultDynamicChangeVo biddingResultDynamicChangeVo = new BiddingResultDynamicChangeVo();
                    LinkedHashMap <String , Object> it = JSON.parseObject(String.valueOf(total.get(s)) , LinkedHashMap.class);
                    LinkedHashMap <String , Object> formItem = JSON.parseObject(String.valueOf(it.get("formItemClass")) , LinkedHashMap.class);
                   /* Map<String , Object>  it = (Map<String, Object>)total.get(s);
                    Map<String , Object>  formItem = (Map<String, Object>)it.get("formItemClass");*/
                    String k = String.valueOf(it.get("key"));
                    String t = String.valueOf(it.get("title"));
                    String typeValue = String.valueOf(it.get("typeValue"));
                    if ( "2".equals(typeValue) ){
                        String foItValue = String.valueOf(formItem.get(k + "areaString"));
                        biddingResultDynamicChangeVo.setValue(foItValue);
                    } else if ( "5".equals(typeValue) ){
                        biddingResultDynamicChangeVo.setValue(String.valueOf(formItem.get("value")));
                    } else if (t.equals("交货地点")){
                        String foItValue = String.valueOf(formItem.get("totalDeliveryPlaceareaString"));
                        biddingResultDynamicChangeVo.setValue(foItValue);

                    }else {
                        String foItValue = String.valueOf(formItem.get("value"));
                        if ( !"null".equals(foItValue) && StringUtils.isNotEmpty(foItValue)){
                            if("金额".equals(it.get("typeName"))){
                                DecimalFormat df2=new DecimalFormat("#,##0.0000"); //保留四位小数
                                String str = ObjectUtils.toString(foItValue, "");
                                boolean isNull = StringUtils.isNotBlank(str);
                                if(!StringUtils.isEmpty(str)){
                                    BigDecimal bigDecimal = new BigDecimal(str);
                                    biddingResultDynamicChangeVo.setValue(isNull ? df2.format(bigDecimal) : "");
                                }else{
                                    biddingResultDynamicChangeVo.setValue("");
                                }
                            }else{
                                biddingResultDynamicChangeVo.setValue(foItValue);
                            }
                        }

                    }

                    biddingResultDynamicChangeVo.setKey(k);
                    biddingResultDynamicChangeVo.setTitle(t);
                    String supplierId = String.valueOf(item.get("supplierId"));
                    if ( !"".equals(supplierId) && supplierId != null){
                        biddingResultDynamicChangeVo.setSupplierId(Long.valueOf(supplierId));
                    }

                    String projectId = String.valueOf(item.get("projectId"));
                    if ( !"".equals(projectId) && projectId != null && !"null".equals(projectId)){
                        biddingResultDynamicChangeVo.setProjectId(Long.valueOf(projectId));
                    }

                    String subProjectId = String.valueOf(item.get("subProjectId"));
                    if ( !"".equals(subProjectId) && subProjectId != null && !"null".equals(subProjectId) ){
                        biddingResultDynamicChangeVo.setSubProjectId(Long.valueOf(subProjectId));
                    }

                    bidding.add(biddingResultDynamicChangeVo);

                }

            }else {
                bidding.add(biddingResultDynamicChangeVonull);
            }


        });

        return bidding;

    }

    @Override
    public List<BiddingResultDynamicChangeVo> getMultipleQuoteBidView(Long subProjectId, Integer quoteTurn) {

        List<Map<String, Object>> decideBidSupplierView = bidViewRestService.getMultipleQuoteBidView(subProjectId, quoteTurn);

        List<BiddingResultDynamicChangeVo> bidding = new ArrayList<>();
        BiddingResultDynamicChangeVo biddingResultDynamicChangeVonull = new BiddingResultDynamicChangeVo();
        decideBidSupplierView.forEach(decideBidSupplier ->{

            LinkedHashMap<String , Object>  item = (LinkedHashMap<String, Object>)decideBidSupplier.get("bidSupplier");
            String totalItems = String.valueOf(item.get("totalItems"));
            if ( !"null".equals(totalItems) && StringUtils.isNotEmpty(totalItems)){
                LinkedHashMap <String , Object> total = JSON.parseObject(String.valueOf(item.get("totalItems")) , LinkedHashMap.class);

                for (String s : total.keySet()){
                    BiddingResultDynamicChangeVo biddingResultDynamicChangeVo = new BiddingResultDynamicChangeVo();
                    LinkedHashMap <String , Object> it = JSON.parseObject(String.valueOf(total.get(s)) , LinkedHashMap.class);
                    LinkedHashMap <String , Object> formItem = JSON.parseObject(String.valueOf(it.get("formItemClass")) , LinkedHashMap.class);

                    String k = String.valueOf(it.get("key"));
                    String t = String.valueOf(it.get("title"));
                    String typeValue = String.valueOf(it.get("typeValue"));
                    if ( "2".equals(typeValue) ){
                        String foItValue = String.valueOf(formItem.get(k + "areaString"));
                        biddingResultDynamicChangeVo.setValue(foItValue);
                    } else if ( "5".equals(typeValue) ){
                        biddingResultDynamicChangeVo.setValue(String.valueOf(formItem.get("value")));
                    } else if (t.equals("交货地点")){
                        String foItValue = String.valueOf(formItem.get("totalDeliveryPlaceareaString"));
                        biddingResultDynamicChangeVo.setValue(foItValue);

                    }else {
                        String foItValue = String.valueOf(formItem.get("value"));
                        if ( !"null".equals(foItValue) && StringUtils.isNotEmpty(foItValue)){
                            if("金额".equals(it.get("typeName"))){
                                DecimalFormat df2=new DecimalFormat("#,##0.0000"); //保留四位小数
                                String str = ObjectUtils.toString(foItValue, "");
                                boolean isNull = StringUtils.isNotBlank(str);
                                if(!StringUtils.isEmpty(str)){
                                    BigDecimal bigDecimal = new BigDecimal(str);
                                    biddingResultDynamicChangeVo.setValue(isNull ? df2.format(bigDecimal) : "");
                                }else{
                                    biddingResultDynamicChangeVo.setValue("");
                                }
                            }else{
                                biddingResultDynamicChangeVo.setValue(foItValue);
                            }
                        }

                    }

                    biddingResultDynamicChangeVo.setKey(k);
                    biddingResultDynamicChangeVo.setTitle(t);
                    String supplierId = String.valueOf(item.get("supplierId"));
                    if ( !"".equals(supplierId) && supplierId != null){
                        biddingResultDynamicChangeVo.setSupplierId(Long.valueOf(supplierId));
                    }

                    /*String projectId = String.valueOf(item.get("projectId"));
                    if ( !"".equals(projectId) && projectId != null){
                        biddingResultDynamicChangeVo.setProjectId(Long.valueOf(projectId));
                    }*/

                    if ( !"".equals(String.valueOf(item.get("subProjectId"))) && String.valueOf(item.get("subProjectId")) != null){
                        biddingResultDynamicChangeVo.setSubProjectId(Long.valueOf(subProjectId));
                    }

                    bidding.add(biddingResultDynamicChangeVo);

                }

            }else {
                bidding.add(biddingResultDynamicChangeVonull);
            }


        });

        return bidding;
    }

    @Override
    public List<BiddingResultDynamicChangeVo> getDecideBidViewKeyTitle(Long subProjectId) {

        List<Map<String, Object>> decideBidSupplierView = bidViewRestService.getDecideBidView(subProjectId);

        List<BiddingResultDynamicChangeVo> bidding = new ArrayList<>();
        Map<String, Object> decideBid = decideBidSupplierView.get(0);
        LinkedHashMap<String , Object>  item = (LinkedHashMap<String, Object>)decideBid.get("bidSupplier");

        String totalItems = String.valueOf(item.get("totalItems"));
        List<BiddingResultDynamicChangeVo> biddingnull = new ArrayList<>();
        if ( !"null".equals(totalItems) && StringUtils.isNotEmpty(totalItems)){
            //Map <String , Object> total = JSON.parseObject(String.valueOf(item.get("totalItems")));
            LinkedHashMap <String , Object> total = JSON.parseObject(String.valueOf(item.get("totalItems")) , LinkedHashMap.class);
            for (String s : total.keySet()){
                //Map<String , Object>  it = (Map<String, Object>)total.get(s);
                LinkedHashMap <String , Object> it = JSON.parseObject(String.valueOf(total.get(s)) , LinkedHashMap.class);
                BiddingResultDynamicChangeVo vo = new BiddingResultDynamicChangeVo();
                String k = String.valueOf(it.get("key"));
                String t = String.valueOf(it.get("title"));
                if(null != it.get("unit")){
                    t = t + "（" + it.get("unit").toString() + "）";
                }
                vo.setKey(k);
                vo.setTitle(t);
                bidding.add(vo);

            }
            return bidding;
        }else {
            return biddingnull;
        }


    }

    @Override
    public List<BiddingResultVo> getDecideBidViewProject(Long subProjectIds,String quoteTurn) {

        List<Map<String, Object>> decideBidSupplierView ;
        if( StringUtils.isEmpty(quoteTurn) ){
            decideBidSupplierView = bidViewRestService.getDecideBidView(subProjectIds);
        } else {
            decideBidSupplierView = bidViewRestService.getMultipleQuoteBidView( subProjectIds , Integer.valueOf(quoteTurn));
        }

        List<BiddingResultVo> bidding = new ArrayList<>();
        decideBidSupplierView.forEach(decideBidSupplier ->{

            Map<String , Object> decideBid = (Map<String, Object>) decideBidSupplier.get("bidSupplier");

            BiddingResultVo biddingResultVo = new BiddingResultVo();

            String id = String.valueOf(decideBid.get("id"));

            if ( !"".equals(id) && id != null && !"null".equals(id)){
                biddingResultVo.setId(Long.valueOf(id));
            }

            String supplierId = String.valueOf(decideBid.get("supplierId"));

            if ( !"".equals(supplierId) && supplierId != null && !"null".equals(supplierId)){
                biddingResultVo.setSupplierId(Long.valueOf(supplierId));
            }
            String projectId = String.valueOf(decideBid.get("projectId"));

            if ( !"".equals(projectId) && projectId != null && !"null".equals(projectId)){
                biddingResultVo.setProjectId(Long.valueOf(projectId));
            }
            String subProjectId = String.valueOf(decideBid.get("subProjectId"));
            if ( !"".equals(subProjectId) && subProjectId != null && !"null".equals(subProjectId)){
                biddingResultVo.setSubProjectId(Long.valueOf(subProjectId));
            }

            String supplierName = String.valueOf(decideBid.get("supplierName"));
            if ( !"".equals(supplierName) && supplierName != null && !"null".equals(supplierName)){
                biddingResultVo.setSupplierName(supplierName);
            }

            bidding.add(biddingResultVo);


        });

        return bidding;
    }

    @Override
    public List<BiddingResultDynamicChangeVo> getDecideBidSupplierView(Long subProjectIds) {
        List<Map<String, Object>> decideBidSupplierView = bidViewRestService.getDecideBidSupplierView(subProjectIds);

        List<BiddingResultDynamicChangeVo> bidding = new ArrayList<>();
        BiddingResultDynamicChangeVo biddingResultDynamicChangeVonull = new BiddingResultDynamicChangeVo();
        decideBidSupplierView.forEach(decideBidSupplier ->{

            Map<String , Object>  item = (Map<String, Object>)decideBidSupplier.get("bidSupplier");
            String totalItems = String.valueOf(item.get("totalItems"));
            if ( !"null".equals(totalItems) && StringUtils.isNotEmpty(totalItems)){
                Map <String , Object> total = JSON.parseObject(String.valueOf(item.get("totalItems")));

                for (String s : total.keySet()){
                    BiddingResultDynamicChangeVo biddingResultDynamicChangeVo = new BiddingResultDynamicChangeVo();
                    Map<String , Object>  it = (Map<String, Object>)total.get(s);
                    Map<String , Object>  formItem = (Map<String, Object>)it.get("formItemClass");
                    String k = String.valueOf(it.get("key"));
                    String t = String.valueOf(it.get("title"));
                    //String foItValue = String.valueOf(formItem.get("value"));

                    String typeValue = String.valueOf(it.get("typeValue"));
                    if ( "2".equals(typeValue) ){
                        String foItValue = String.valueOf(formItem.get(k + "areaString"));
                        biddingResultDynamicChangeVo.setValue(foItValue);
                    } else if ( "5".equals(typeValue) ){
                        biddingResultDynamicChangeVo.setValue(String.valueOf(formItem.get("value")));
                    } else if (t.equals("交货地点")){
                        String foItValue = String.valueOf(formItem.get("totalDeliveryPlaceareaString"));
                        if ( null != foItValue && "null".equals(foItValue) ) {
                            foItValue = String.valueOf(formItem.get("value"));
                        }
                        biddingResultDynamicChangeVo.setValue(foItValue);
                    }else {
                        String foItValue = String.valueOf(formItem.get("value"));
                        if ( !"null".equals(foItValue) && StringUtils.isNotEmpty(foItValue)){
                            if("金额".equals(it.get("typeName"))){
                                DecimalFormat df2=new DecimalFormat("#,##0.0000"); //保留四位小数
                                String str = ObjectUtils.toString(foItValue, "");
                                boolean isNull = StringUtils.isNotBlank(str);
                                if(!StringUtils.isEmpty(str)){
                                    BigDecimal bigDecimal = new BigDecimal(str);
                                    biddingResultDynamicChangeVo.setValue(isNull ? df2.format(bigDecimal) : "");
                                }else{
                                    biddingResultDynamicChangeVo.setValue("");
                                }
                            }else{
                                biddingResultDynamicChangeVo.setValue(foItValue);
                            }
                        }
                    }

                    biddingResultDynamicChangeVo.setKey(k);
                    //biddingResultDynamicChangeVo.setValue(foItValue);
                    biddingResultDynamicChangeVo.setTitle(t);

                    String supplierId = String.valueOf(item.get("supplierId"));
                    if ( !"".equals(supplierId) && supplierId != null){
                        biddingResultDynamicChangeVo.setSupplierId(Long.valueOf(supplierId));
                    }

                    String projectId = String.valueOf(item.get("projectId"));
                    if ( !"".equals(projectId) && projectId != null && !"null".equals(projectId)){
                        biddingResultDynamicChangeVo.setProjectId(Long.valueOf(projectId));
                    }

                    String subProjectId = String.valueOf(item.get("subProjectId"));
                    if ( !"".equals(subProjectId) && subProjectId != null && !"null".equals(subProjectId)){
                        biddingResultDynamicChangeVo.setSubProjectId(Long.valueOf(subProjectId));
                    }

                    bidding.add(biddingResultDynamicChangeVo);

                }

            }else {
                bidding.add(biddingResultDynamicChangeVonull);
            }


        });

        return bidding;
    }

    @Override
    public List<BiddingResultDynamicChangeVo> getDecideBidSupplierViewKeyTitle(Long subProjectId) {
        List<Map<String, Object>> decideBidSupplierView = bidViewRestService.getDecideBidSupplierView(subProjectId);

        List<BiddingResultDynamicChangeVo> bidding = new ArrayList<>();
        Map<String, Object> decideBid = decideBidSupplierView.get(0);
        LinkedHashMap<String , Object>  item = (LinkedHashMap<String, Object>)decideBid.get("bidSupplier");

        String totalItems = String.valueOf(item.get("totalItems"));
        List<BiddingResultDynamicChangeVo> biddingnull = new ArrayList<>();
        if ( !"null".equals(totalItems) && StringUtils.isNotEmpty(totalItems)){
            //转化为有序
            LinkedHashMap <String , Object> total = JSON.parseObject(String.valueOf(item.get("totalItems")) , LinkedHashMap.class);
            for (String s : total.keySet()){
                LinkedHashMap <String , Object> it = JSON.parseObject(String.valueOf(total.get(s)) , LinkedHashMap.class);
                BiddingResultDynamicChangeVo vo = new BiddingResultDynamicChangeVo();
                String k = String.valueOf(it.get("key"));
                String t = String.valueOf(it.get("title"));
                if(null != it.get("unit")){
                    t = t + "（" + it.get("unit").toString() + "）";
                }
                vo.setKey(k);
                vo.setTitle(t);
                bidding.add(vo);

            }
            return bidding;
        }else {
            return biddingnull;
        }
    }

    @Override
    public List<BiddingResultVo> getDecideBidSupplierViewProject(Long subProjectIds) {
        List<Map<String, Object>> decideBidSupplierView = bidViewRestService.getDecideBidSupplierView(subProjectIds);

        List<BiddingResultVo> bidding = new ArrayList<>();
        decideBidSupplierView.forEach(decideBidSupplier ->{

            Map<String , Object> decideBid = (Map<String, Object>) decideBidSupplier.get("bidSupplier");

            for (String obj : decideBid.keySet()){
                BiddingResultVo biddingResultVo = new BiddingResultVo();

                String orderId = String.valueOf(decideBid.get("orderId"));
                if ( !"".equals(orderId) && orderId != null){
                    biddingResultVo.setOrderID(Long.valueOf(orderId));
                }
                String id = String.valueOf(decideBid.get("id"));

                if ( !"".equals(id) && id != null){
                    biddingResultVo.setId(Long.valueOf(id));
                }

                String supplierId = String.valueOf(decideBid.get("supplierId"));

                if ( !"".equals(supplierId) && supplierId != null){
                    biddingResultVo.setSupplierId(Long.valueOf(supplierId));
                }
                String projectId = String.valueOf(decideBid.get("projectId"));

                if ( !"".equals(projectId) && projectId != null && !"null".equals(projectId)){
                    biddingResultVo.setProjectId(Long.valueOf(projectId));
                }
                String subProjectId = String.valueOf(decideBid.get("subProjectId"));
                if ( !"".equals(subProjectId) && subProjectId != null && !"null".equals(subProjectId)){
                    biddingResultVo.setSubProjectId(Long.valueOf(subProjectId));
                }

                String supplierName = String.valueOf(decideBid.get("supplierName"));
                if ( !"".equals(supplierName) && supplierName != null){
                    biddingResultVo.setSupplierName(supplierName);
                }

                String winBidStatusName = String.valueOf(decideBid.get("winBidStatusName"));
                if ( !"".equals(winBidStatusName) && winBidStatusName != null){
                    biddingResultVo.setWinBidStatusName(winBidStatusName);
                }

                String winBidTotalPrice = String.valueOf(decideBid.get("winBidTotalPrice"));
                if ( !"".equals(winBidTotalPrice) && winBidTotalPrice != null){
                    biddingResultVo.setWinBidTotalPrice(winBidTotalPrice);
                }

                String winFallReason = String.valueOf(decideBid.get("winFallReason"));
                if ( !"".equals(winFallReason) && winFallReason != null){
                    biddingResultVo.setWinFallReason(winFallReason);
                }

                bidding.add(biddingResultVo);

            }

        });

        return bidding;
    }

    // 展示所有报价供应商信息
    @Override
    public List<Map<String, Object>> getSupplierBidItemList(Long subProjectId) {
        Map<String, Object> supplierBidItemList = bidSupplierItemRestService.getSupplierBidItemList(subProjectId);
        return this.subentrySupplier(supplierBidItemList);
    }

    @Override
    public List<Map<String, Object>> getMultipleSupplierBidItemList(Long subProjectId, Integer quoteTurn) {
        Map<String, Object> multipleSupplierBidItemList = bidSupplierItemRestService.getMultipleSupplierBidItemList(subProjectId, quoteTurn);
        return subentrySupplier(multipleSupplierBidItemList);
    }

    @Override
    public List<Map<String, Object>> findQuoteItem(Long subProjectId) {
        Map<String, Object> supplierBidItemList = bidSupplierItemRestService.getSupplierBidItemList(subProjectId);
        return getQuoteItem(supplierBidItemList);
    }

    private List<Map<String, Object>> getQuoteItem(Map<String, Object> supplierBidItemList) {
        List<Map<String, Object>> tableData = (List<Map<String, Object>>) supplierBidItemList.get("tableData");
        List list = new ArrayList();
        tableData.forEach(data -> {
            List<Map<String, String>> supplierList = (List<Map<String, String>>) data.get("supplierList");
            supplierList.forEach(map -> {
                Map<String, Object> quoteItem = JSON.parseObject(String.valueOf(map.get("quoteItem")));
                if (!CollectionUtils.isEmpty(quoteItem)) {
                    // 添加分项key,value
                    for (String key : quoteItem.keySet()) {
                        SubentryQuotationFormQuoteItemVO vo = new SubentryQuotationFormQuoteItemVO();
                        vo.setKey(key);
                        if(key.equals("subLabourCost") || key.equals("subMaterialCost") || key.equals("subCombinedPrice") || key.equals("subMachineryCost") ||
                                key.equals("subManagementCost") || key.equals("subProfit")){
                            DecimalFormat df2=new DecimalFormat("#,##0.0000"); //保留四位小数  
                            Object keyValue = quoteItem.get(key);
                            String str = ObjectUtils.toString(keyValue, "");
                            boolean isNull = StringUtils.isNotBlank(str);
                            if(!StringUtils.isEmpty(str)){
                                BigDecimal bigDecimal = new BigDecimal(str);
                                vo.setValue(isNull ? df2.format(bigDecimal) : "");
                            }else{
                                vo.setValue("");
                            }
                        }else{
                            vo.setValue((String) quoteItem.get(key));
                        }
                        vo.setSupplierId(map.get("supplierId"));
                        vo.setBidProjectItemId(map.get("bidProjectItemId"));
                        vo.setName((String) data.get("name"));
                        vo.setCode((String) data.get("code"));
                        vo.setDirectoryId((String) data.get("directoryId"));
                        list.add(vo);
                    }
                }
            });
        });
        return list;
    }

    @Override
    public List<Map<String, Object>> findMultipleQuoteItem(Long subProjectId , Integer quoteTurn) {
        Map<String, Object> multipleSupplierBidItemList = bidSupplierItemRestService.getMultipleSupplierBidItemList(subProjectId, quoteTurn);
        return getQuoteItem(multipleSupplierBidItemList);
    }

    private List<Map<String, Object>> subentrySupplier(Map<String, Object> supplierBidItemList) {
        List<Map<String, Object>> tableData = (List<Map<String, Object>>) supplierBidItemList.get("tableData");
        List list = new ArrayList();
        tableData.forEach(data -> {
            List<Map<String, String>> supplierList = (List<Map<String, String>>) data.get("supplierList");
            supplierList.forEach(map -> {
                if(!CollectionUtils.isEmpty(map)){
                    //分项报价项为空
                    SubentryQuotationFormSupplierVO vo = new SubentryQuotationFormSupplierVO();
                    vo.setSupplierName(map.get("supplierName"));
                    vo.setSupplierId(map.get("supplierId"));
                    vo.setBidProjectItemId(map.get("bidProjectItemId"));
                    vo.setQuoteUnitPrice(map.get("quoteUnitPrice"));
                    vo.setQuoteTotalPrice(map.get("quoteTotalPrice"));
                    vo.setDifferenceAmount(map.get("differenceAmount"));
                    vo.setDifferenceRatio(map.get("differenceRatio"));
                    vo.setQuoteAmount(map.get("quoteAmount"));
                    vo.setName((String) data.get("name"));
                    vo.setCode((String) data.get("code"));
                    list.add(vo);
                }
            });
        });
        return list;
    }

    private List<Map<String,Object>> buildSeperateData(List<Map<String,Object>> originDataList){
        List<Map<String,Object>> reportDataList = new ArrayList<Map<String,Object>>();
        if( null != originDataList && originDataList.size() > 0 ){
            originDataList.forEach( map ->{
                List<Map<String,Object>> supplierList = (List)map.get("supplierList");
                supplierList.forEach(m ->{
                    String supplierId = (String) m.get("supplierId");
                    Map<String,Object> show = (Map<String,Object>) m.get("show");
                    show.put("supplierId",supplierId);
                    show.put("itemId",map.get("itemId"));
                    reportDataList.add(show);
                });
            });
        }
        return reportDataList;
    }
}