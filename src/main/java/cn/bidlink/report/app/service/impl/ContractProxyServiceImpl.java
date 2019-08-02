package cn.bidlink.report.app.service.impl;

import cn.bidlink.procurement.contract.cloud.service.ContractInitiateRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractItemRestService;
import cn.bidlink.procurement.contract.cloud.service.ContractProjectService;
import cn.bidlink.procurement.contract.cloud.service.ContractQuoteItemService;
import cn.bidlink.report.app.service.ContractProxyService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContractProxyServiceImpl implements ContractProxyService {

    @Autowired
    ContractInitiateRestService contractInitiateRestService;

    @Autowired
    ContractProjectService contractProjectService;

    @Autowired
    ContractQuoteItemService contractQuoteItemService;

    @Autowired
    ContractItemRestService contractItemRestService;

    @Override
    public List<Map<String, String>> getContractHeadAndTailData(Long companyId, Long projectId,Long userId) {
        List<Map<String,String>> list = new ArrayList<>();
        // todo  暂时注释
      /*  ContractMainVo contractMainVo = contractInitiateRestService.get(projectId, companyId, userId);
        ResponseObj responseObj = contractProjectService.getFileNames(projectId, companyId);
        //合同名称
        String name = contractMainVo.getHead().getName();
        if( StringUtils.isNotEmpty(name) ){
            Map<String, String> nameMap = new HashMap<>(4);
            nameMap.put("value",name);
            nameMap.put("position","-2");
            list.add(nameMap);
        }
        //合同有效期
        String effectiveStartAndEnd = contractMainVo.getHead().getEffectiveStartAndEnd();
        if( StringUtils.isNotEmpty(effectiveStartAndEnd) ){
            String[] split = effectiveStartAndEnd.split(",");
            if( split.length == 2){
                Map<String, String> effectiveMap = new HashMap<>(4);
                effectiveMap.put("value",split[0] + " 至 " + split[1]);
                effectiveMap.put("position","-1");
                list.add(effectiveMap);
            }
        }
        //head left
        ContractMainHeadVo head = contractMainVo.getHead();
        if( StringUtils.isNotEmpty( head.getHeadNameA()) ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","甲方名称：");
            data.put("value" , head.getHeadNameA());
            data.put("position","1");
            list.add(data);
        }
        if( StringUtils.isNotEmpty(contractMainVo.getSeriaNumber()) ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","合同流水号：" );
            data.put("value",contractMainVo.getSeriaNumber());
            data.put("position","1");
            list.add(data);
        }
        if( null != contractMainVo.getSignTime() ){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, String> data = new HashMap<>(4);
            data.put("title","签订时间：" );
            data.put("value",simpleDateFormat.format(contractMainVo.getSignTime()));
            data.put("position","1");
            list.add(data);
        }
        if( StringUtils.isNotEmpty(contractMainVo.getSigningPlace()) ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","签订地点：" );
            data.put("value",contractMainVo.getSigningPlace());
            data.put("position","1");
            list.add(data);
        }
        String isShowTotalItem = "false";
        if( null != responseObj && null != responseObj.getData() ){
            Map map = (Map) responseObj.getData();
            if( null != map.get("uploadFile") && StringUtils.isNotEmpty(map.get("uploadFile").toString())){
                Map<String, String> data = new HashMap<>(4);
                data.put("title", "附件："  );
                data.put("value", map.get("uploadFile").toString() );
                data.put("position","1");
                list.add(data);
            }
            //条款信息
            Map<String, String> articleContentMap = new HashMap<>(4);
            articleContentMap.put("value",contractMainVo.getArticleContent());
            if( null != map.get("contractStyleCode") ){
                if( "A".equals(map.get("contractStyleCode").toString()) ){
                    articleContentMap.put("position","down");
                } else if ( "C".equals(map.get("contractStyleCode").toString()) ){
                    articleContentMap.put("position","up");
                    Map<String, String> atta = new HashMap<>(4);
                    atta.put("value","采购品信息见附件："+ name + ".pdf");
                    atta.put("position","bottom");
                    list.add(atta);
                } else {
                    articleContentMap.put("position","up");
                }
            }
            list.add(articleContentMap);
            //是否展示总项
            if( null != map.get("showQuoteTotalItem") ){
                isShowTotalItem = map.get("showQuoteTotalItem").toString();
            }
        }

        //head right
        if( StringUtils.isNotEmpty( head.getHeadNameB()) ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","乙方名称：" );
            data.put("value",head.getHeadNameB());
            data.put("position","2");
            list.add(data);
        }
        if( null != contractMainVo.getIsFramework() && contractMainVo.getIsFramework() == 2 ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","合同类型：");
            data.put("value","框架协议合同");
            data.put("position","2");
            list.add(data);
        } else {
            Map<String, String> data = new HashMap<>(4);
            data.put("title","合同类型：");
            data.put("value","普通合同");
            data.put("position","2");
            list.add(data);
        }
        if( StringUtils.isNotEmpty( head.getCode()) ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","合同编码：" );
            data.put("value", head.getCode());
            data.put("position","2");
            list.add(data);
        }
        if( StringUtils.isNotEmpty( head.getOpenRemarks() ) ){
            Map<String, String> data = new HashMap<>(4);
            data.put("title","合同备注：" );
            data.put("value", head.getOpenRemarks());
            data.put("position","2");
            list.add(data);
        }
        if( null != responseObj && null != responseObj.getData() ){
            Map map = (Map) responseObj.getData();
            if( null != map.get("supplyFile") && StringUtils.isNotEmpty(map.get("supplyFile").toString())){
                Map<String, String> data = new HashMap<>(4);
                data.put("title","补充附件：" );
                data.put("value", map.get("supplyFile").toString() );
                data.put("position","2");
                list.add(data);
            }
        }
        responseObj = null;
        head = null;

        AppsetContractTemplateVo template = contractMainVo.getTemplate();
        //tail left
        String tailAJsonString = contractMainVo.getTailAJsonString();
        Map tailAValueMap = (Map)JSON.parseObject(tailAJsonString);
        String footAStr = template.getFootA();
        Map footAMap = JSON.parseObject(footAStr);
        List<Map> footAList = (List<Map>)footAMap.get("typeData");
        footAList.forEach( map -> {
            if( null !=  map.get("title") && null != map.get("key") ){
                Map<String, String> data = new HashMap<>(4);
                if(null != tailAValueMap.get(map.get("key"))){
                    data.put("title" , map.get("title").toString() + "：" );
                    data.put("value" , tailAValueMap.get(map.get("key")).toString() );
                } else {
                    data.put("title" , map.get("title").toString() + "：" );
                }
                data.put("position","3");
                list.add(data);
            }
        });
        //tail right
        String tailBJsonString = contractMainVo.getTailBJsonString();
        Map tailBValueMap = (Map)JSON.parseObject(tailBJsonString);
        String footBStr = template.getFootB();
        Map footBMap = JSON.parseObject(footBStr);
        List<Map> footBList = (List<Map>)footBMap.get("typeData");
        footBList.forEach(map -> {
            if( null !=  map.get("title") && null != map.get("key") ){
                Map<String, String> data = new HashMap<>();
                if(null != tailBValueMap.get(map.get("key"))){
                    data.put("title" , map.get("title").toString() + "："  );
                    data.put("value" , tailBValueMap.get(map.get("key")).toString() );
                } else {
                    data.put("title" , map.get("title").toString() + "：" );
                }
                data.put("position","4");
                list.add(data);
            }
        });
        contractMainVo = null;

        //总报价项
        JSONObject showTotalQuoteItem = contractQuoteItemService.getShowTotalQuoteItem(projectId, companyId, true);
        if( null != showTotalQuoteItem && "true".equals(isShowTotalItem) ){
            Map item = showTotalQuoteItem;
            AtomicInteger atomicInteger = new AtomicInteger(0);
            item.forEach( (k,v) -> {
                Map m = (Map) v;
                String title = m.get("title").toString();
                String value = m.get("typeValue").toString();
                if( null != m.get("unit") ){
                    title = title + "（" + m.get("unit").toString() + "）";
                }
                title = title + "：";
                Map<String,String> map = new HashMap();
                map.put("title",title);
                if( null != value ){
                    map.put("value",value);
                }

                int i = atomicInteger.getAndIncrement();
                if( i % 2 == 0 ){
                    map.put("position","5");
                } else {
                    map.put("position","6");
                }
                list.add(map);
            });
        }
        showTotalQuoteItem = null;*/
        return list;
    }

    @Override
    public List<Map<String, Object>> getContractQuoteItemData(Long companyId, Long projectId) {
        Map map = contractItemRestService.list(projectId, (short) 0, 0, 1000, companyId);
        Object tableData = map.get("tableData");
        List<Map<String,Object>> list = new ArrayList<>();
        if( null != tableData ){
            List<Map<String,Object>> dataLis = (List) tableData;
            dataLis.forEach( m ->{
                Object quoteItem = m.get("quoteItem");
                Object itemId = m.get("itemId");
                if( null != quoteItem && null != itemId ){
                    Map m1 = (Map) JSONObject.parseObject(quoteItem.toString());
                    m1.forEach((k,v) ->{
                        Map<String, Object> maps = new HashMap<>(4);
                        maps.put("key",k);
                        maps.put("value",v);
                        maps.put("itemId",itemId.toString());
                        list.add(maps);
                    });
                }
            });
        }
        return list;
    }
}