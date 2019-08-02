package cn.bidlink.report.app.datasource.order;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.model.vo.order.OrderCargoItemVO;
import cn.bidlink.report.app.service.OrderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单项目->发货单信息(采购品数据集)
 * @Date 2019/7/1
 *
 */
public class OrderPurchaseDetailDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("orderId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{ "id" ,"name" ,"code" ,"spec", "unitName", "comment", "itemComment",
                "useDept", "techParameters", "purpose", "appliedEnterprise", "appliedPersonAndPhone",
                "needTime", "quoteUnitPrice","sendNumber", "cargoSendNumber", "signNumber", "refundNumber",
                "actualPrice","taxRate", "dataSourceId", "dataSourceProjectName", "sourceId", "orderId",
                "orderCargoId","orgId", "orgName", "orgPath", "companyId", "companyName", "createUserId",
                "createUserName", "createTime", "updateUserId", "updateUserName", "updateTime", "isDelete", "quoteTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        OrderProxyService orderCargoRestService = dataServiceFactory.getDataService(OrderProxyService.class);
        Long orderId = Long.valueOf(param.get("orderId"));
        Long companyId = Long.valueOf(param.get("companyId"));
        List<OrderCargoItemVO> orderCargoItems = orderCargoRestService.getOrderCargoItems(orderId, companyId);

        return orderCargoItems;


    }
}