package cn.bidlink.report.app.datasource.order;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.order.OrderCargoItemQuteItemVO;
import cn.bidlink.report.app.service.OrderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->发货单信息(分项报价title)
 * @Date 2019/7/3
 *
 */
public class OrderPurchaseQuteItemTitleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("mainId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "key", "title", "value"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        OrderProxyService orderCargoRestService = dataServiceFactory.getDataService(OrderProxyService.class);
        Long mainId = Long.valueOf(param.get("mainId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        List<OrderCargoItemQuteItemVO> orderCargoQuteTitleItems = orderCargoRestService.getOrderCargoQuteTitleItems(mainId, companyId);
        return orderCargoQuteTitleItems;


    }
}