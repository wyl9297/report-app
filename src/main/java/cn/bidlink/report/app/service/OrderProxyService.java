package cn.bidlink.report.app.service;

import cn.bidlink.procurement.order.cloud.dto.OrderDetailDto;
import cn.bidlink.procurement.order.dal.dto.OrderCargoPrintDto;
import cn.bidlink.report.app.model.vo.order.OrderCargoItemQuteItemVO;
import cn.bidlink.report.app.model.vo.order.OrderCargoItemVO;

import java.util.List;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目数据源代理接口
 * @Date 2019/7/5
 */
public interface OrderProxyService {

    List<OrderDetailDto> getOrderDetailData(Long mainId, Long companyId);

    List<OrderCargoPrintDto> getOrderCargoList(Long orderId, Long companyId);

    List<OrderCargoItemVO> getOrderCargoItems(Long orderId, Long companyId);

    List<OrderCargoItemQuteItemVO> getOrderCargoQuteItems(Long orderId, Long companyId);

    List<OrderCargoItemQuteItemVO> getOrderCargoQuteTitleItems(Long mainId, Long companyId);



}
