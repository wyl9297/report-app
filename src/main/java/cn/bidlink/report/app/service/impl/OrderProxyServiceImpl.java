package cn.bidlink.report.app.service.impl;

import cn.bidlink.procurement.order.cloud.dto.OrderDetailDto;
import cn.bidlink.procurement.order.cloud.service.OrderCargoRestService;
import cn.bidlink.procurement.order.cloud.service.OrderListResultService;
import cn.bidlink.procurement.order.dal.dto.OrderCargoPrintDto;
import cn.bidlink.procurement.order.dal.entity.OrderCargoItem;
import cn.bidlink.report.app.model.vo.order.OrderCargoItemQuteItemVO;
import cn.bidlink.report.app.model.vo.order.OrderCargoItemVO;
import cn.bidlink.report.app.service.OrderProxyService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/7/5
 */
@Service
public class OrderProxyServiceImpl implements OrderProxyService {

    @Autowired
    private OrderListResultService orderListResultService;
    @Autowired
    private OrderCargoRestService  orderCargoRestService;


    @Override
    public List<OrderDetailDto> getOrderDetailData(Long mainId, Long companyId) {
        return null;
    }

    @Override
    public List<OrderCargoPrintDto> getOrderCargoList(Long orderId, Long companyId) {

        List<OrderCargoPrintDto> orderCargoListNull = new ArrayList<>();
        List<OrderCargoPrintDto> orderCargoList = new ArrayList<>();
        List<OrderCargoPrintDto> orderCargoPrintList = orderCargoRestService.getOrderCargoPrintList(orderId, companyId);
        if (null != orderCargoPrintList && orderCargoPrintList.size() > 0){
            if (orderCargoPrintList.size() == 1){
                orderCargoPrintList.forEach(orderList ->{
                    OrderCargoPrintDto orderCargoPrintDto = new OrderCargoPrintDto();
                    orderCargoPrintDto.setCargoId(orderList.getCargoId());
                    orderCargoPrintDto.setCargoStatus(orderList.getCargoStatus());

                    String cargoCode = "货单("+orderList.getCargoCode()+")"+"("+orderList.getCargoStatusStr()+")";
                    orderCargoPrintDto.setCargoCode(cargoCode);

                    orderCargoPrintDto.setCargoStatusStr(orderList.getCargoStatusStr());
                    orderCargoPrintDto.setLogistics(orderList.getLogistics());
                    orderCargoPrintDto.setUploadFile(orderList.getUploadFile());
                    orderCargoList.add(orderCargoPrintDto);

                });

                return orderCargoList;
            }else {

                int seq = 0;
                for (int i = 0; i < orderCargoPrintList.size() ; i++) {
                    seq++ ;
                    OrderCargoPrintDto orderList = orderCargoPrintList.get(i);
                    OrderCargoPrintDto orderCargoPrintDto = new OrderCargoPrintDto();
                    orderCargoPrintDto.setCargoId(orderList.getCargoId());
                    orderCargoPrintDto.setCargoStatus(orderList.getCargoStatus());
                    String cargoCode = "货单"+ seq +"("+orderList.getCargoCode()+")"+"("+orderList.getCargoStatusStr()+")";
                    orderCargoPrintDto.setCargoCode(cargoCode);

                    orderCargoPrintDto.setCargoStatusStr(orderList.getCargoStatusStr());
                    orderCargoPrintDto.setLogistics(orderList.getLogistics());
                    orderCargoPrintDto.setUploadFile(orderList.getUploadFile());
                    orderCargoList.add(orderCargoPrintDto);

                }

                return orderCargoList;

                /*orderCargoPrintList.forEach(orderList ->{
                    OrderCargoPrintDto orderCargoPrintDto = new OrderCargoPrintDto();
                    orderCargoPrintDto.setCargoId(orderList.getCargoId());
                    orderCargoPrintDto.setCargoStatus(orderList.getCargoStatus());

                    int seq = 0;
                    int seq2 = seq++ ;
                    System.out.println(seq2);
                    String cargoCode = "发货单"+ seq2 +"("+orderList.getCargoCode()+")";
                    orderCargoPrintDto.setCargoCode(cargoCode);

                    orderCargoPrintDto.setCargoStatusStr(orderList.getCargoStatusStr());
                    orderCargoPrintDto.setLogistics(orderList.getLogistics());
                    orderCargoPrintDto.setUploadFile(orderList.getUploadFile());
                    orderCargoList.add(orderCargoPrintDto);

                });

                return orderCargoList;*/

            }

        }else {
            return orderCargoListNull;
        }
    }

    @Override
    public List<OrderCargoItemVO> getOrderCargoItems(Long orderId, Long companyId) {

        List<OrderCargoItemVO> orderDetailDataNull = new ArrayList<>();
        List<OrderCargoItemVO> orderCargoItems = new ArrayList<>();
        List<OrderCargoPrintDto> orderCargoPrintList = orderCargoRestService.getOrderCargoPrintList(orderId, companyId);
        if (null != orderCargoPrintList && orderCargoPrintList.size() > 0){
            orderCargoPrintList.forEach(orderList ->{
                List<OrderCargoItem> orderItems = orderList.getOrderCargoItems();
                orderItems.forEach(items ->{

                    OrderCargoItemVO orderCargoItem = new OrderCargoItemVO();
                    orderCargoItem.setId(items.getId());
                    orderCargoItem.setName(items.getName());
                    orderCargoItem.setCode(items.getCode());
                    orderCargoItem.setSpec(items.getSpec());
                    orderCargoItem.setUnitName(items.getUnitName());
                    orderCargoItem.setComment(items.getItemComment());
                    orderCargoItem.setItemComment(items.getItemComment());
                    orderCargoItem.setUseDept(items.getUseDept());
                    orderCargoItem.setTechParameters(items.getTechParameters());
                    orderCargoItem.setPurpose(items.getPurpose());
                    orderCargoItem.setAppliedEnterprise(items.getAppliedEnterprise());
                    orderCargoItem.setAppliedPersonAndPhone(items.getAppliedPersonAndPhone());
                    orderCargoItem.setNeedTime(items.getNeedTime());
                    orderCargoItem.setQuoteUnitPrice(items.getQuoteUnitPrice());
                    orderCargoItem.setSendNumber(items.getSendNumber());
                    orderCargoItem.setCargoSendNumber(items.getCargoSendNumber());
                    orderCargoItem.setSignNumber(items.getSignNumber());
                    orderCargoItem.setRefundNumber(items.getRefundNumber());
                    orderCargoItem.setActualPrice(items.getActualPrice());
                    orderCargoItem.setTaxRate(items.getTaxRate());
                    orderCargoItem.setDataSourceId(items.getDataSourceId());
                    orderCargoItem.setDataSourceProjectName(items.getDataSourceProjectName());
                    orderCargoItem.setSourceId(items.getSourceId());
                    orderCargoItem.setOrderId(items.getOrderId());
                    orderCargoItem.setOrderCargoId(items.getOrderCargoId());
                    orderCargoItem.setOrgId(items.getOrgId());
                    orderCargoItem.setOrgName(items.getOrgName());
                    orderCargoItem.setOrgPath(items.getOrgPath());
                    orderCargoItem.setCompanyId(items.getCompanyId());
                    orderCargoItem.setCompanyName(items.getCompanyName());
                    orderCargoItem.setCreateUserId(items.getCreateUserId());
                    orderCargoItem.setCreateUserName(items.getCreateUserName());
                    orderCargoItem.setCreateTime(items.getCreateTime());
                    orderCargoItem.setUpdateUserId(items.getUpdateUserId());
                    orderCargoItem.setUpdateUserName(items.getUpdateUserName());
                    orderCargoItem.setUpdateTime(items.getUpdateTime());
                    orderCargoItem.setIsDelete(items.getIsDelete());
                    //计算单品总价（单价*货单的发货量）
                    BigDecimal quoteUnitPrice = items.getQuoteUnitPrice();
                    BigDecimal cargoSendNumber = items.getCargoSendNumber();
                    if( quoteUnitPrice != null){
                        BigDecimal multiply = quoteUnitPrice.multiply(cargoSendNumber);
                        BigDecimal bigDecimal = multiply.setScale(2, BigDecimal.ROUND_HALF_UP);
                        orderCargoItem.setQuoteTotalPrice(bigDecimal);
                    }

                    orderCargoItems.add(orderCargoItem);

                });

            });

            return orderCargoItems;
        }else {
            return orderDetailDataNull;
        }
    }

    @Override
    public List<OrderCargoItemQuteItemVO> getOrderCargoQuteItems(Long orderId, Long companyId) {

        List<OrderCargoItemQuteItemVO> orderDetailDataNull = new ArrayList<>();
        List<OrderCargoItemQuteItemVO> orderCargoItems = new ArrayList<>();
        List<OrderCargoPrintDto> orderCargoPrintList = orderCargoRestService.getOrderCargoPrintList(orderId, companyId);
        if (null != orderCargoPrintList && orderCargoPrintList.size() > 0){

            orderCargoPrintList.forEach(orderList ->{

                List<OrderCargoItem> orderItems = orderList.getOrderCargoItems();
                orderItems.forEach(items ->{

                    String quoteItem = items.getQuoteItem();
                    if ( !"null".equals(quoteItem) && StringUtils.isNotEmpty(quoteItem)){
                        LinkedHashMap<String , Object> quote = JSON.parseObject(String.valueOf(quoteItem) , LinkedHashMap.class);
                        for (String s : quote.keySet()){
                            OrderCargoItemQuteItemVO orderCargoItem = new OrderCargoItemQuteItemVO();
                            orderCargoItem.setCargoId(orderList.getCargoId());
                            orderCargoItem.setCargoCode(orderList.getCargoCode());
                            orderCargoItem.setId(items.getId());
                            orderCargoItem.setCode(items.getCode());
                            orderCargoItem.setKey(s);
                            String quteValue = String.valueOf(quote.get(s));
                            orderCargoItem.setValue(quteValue);
                            orderCargoItems.add(orderCargoItem);
                        }
                    }

                });

            });

            return orderCargoItems;
        }else {
            return orderDetailDataNull;
        }
    }

    @Override
    public List<OrderCargoItemQuteItemVO> getOrderCargoQuteTitleItems(Long mainId, Long companyId) {
        List<OrderCargoItemQuteItemVO> orderCargoSubNull = new ArrayList<>();
        List<OrderCargoItemQuteItemVO> orderCargoItems = new ArrayList<>();
        OrderDetailDto orderDetail = orderListResultService.getOrderDetail(mainId, companyId);
        String quoteSubItem = orderDetail.getQuoteSubItem();
        if (null != quoteSubItem && !"null".equals(quoteSubItem) && !quoteSubItem.isEmpty() && !" ".equals(quoteSubItem)) {
            LinkedHashMap<String, Object> quote = JSON.parseObject(quoteSubItem, LinkedHashMap.class);
            for (String s : quote.keySet()) {
                LinkedHashMap <String , Object> it = JSON.parseObject(String.valueOf(quote.get(s)) , LinkedHashMap.class);
                OrderCargoItemQuteItemVO orderCargoItem = new OrderCargoItemQuteItemVO();
                String k = String.valueOf(it.get("key"));
                String t = String.valueOf(it.get("title"));
                if (null != it.get("unit")) {
                    t = t + "(" + it.get("unit").toString() + ")";
                }
                orderCargoItem.setKey(k);
                orderCargoItem.setTitle(t);
                if (t.equals("交货地点")) {
                    String foItValue = String.valueOf(it.get("totalDeliveryPlaceareaString"));
                    orderCargoItem.setValue(foItValue);

                } else {
                    String foItValue = String.valueOf(it.get("value"));
                    if (!"null".equals(foItValue) && StringUtils.isNotEmpty(foItValue)) {
                        orderCargoItem.setValue(foItValue);
                    }

                }
                orderCargoItems.add(orderCargoItem);
            }
            return orderCargoItems;
        }else {
            return orderCargoSubNull;
        }
    }


}
