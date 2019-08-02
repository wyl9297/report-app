package cn.bidlink.report.app.datasource.order;

import cn.bidlink.procurement.order.dal.dto.OrderCargoPrintDto;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.OrderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->发货单信息(货单信息，表头编号)
 * @Date 2019/7/1
 *
 */
public class OrderInvoiceDetailDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("orderId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "cargoId" ,"cargoStatus" ,"cargoCode" ,"cargoStatusStr", "logistics", "uploadFile"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        OrderProxyService orderCargoRestService = dataServiceFactory.getDataService(OrderProxyService.class);
        Long orderId = Long.valueOf(param.get("orderId"));
        Long companyId = Long.valueOf(param.get("companyId"));

        List<OrderCargoPrintDto> orderCargoPrintList = orderCargoRestService.getOrderCargoList(orderId, companyId);

        return orderCargoPrintList;

    }
}