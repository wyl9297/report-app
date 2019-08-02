package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.report.app.model.vo.tender.BiddingResultDynamicChangeVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:招标项目->开标一览表结果 动态数据集value值
 * @Date 2019/5/31
 *
 */
public class BiddingOpeningDynamicChangeDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("subProjectId"),
                new Parameter("quoteTurn")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierId","projectId","subProjectId","key","value","title"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        TenderProxyService tenderProxyService = dataServiceFactory.getDataService(TenderProxyService.class);
        String quoteTurn = param.get("quoteTurn");
        boolean isNumber = StringUtils.isNumeric(quoteTurn);
        // 如果quoteTurn 不为null，并且是数字 ， 多轮报价
        if(!StringUtils.isEmpty(quoteTurn) && isNumber){
            List<BiddingResultDynamicChangeVo> multipleQuoteBidView = tenderProxyService.getMultipleQuoteBidView(Long.valueOf(param.get("subProjectId")), Integer.parseInt(param.get("quoteTurn")));
            return multipleQuoteBidView;
        }else{
            List<BiddingResultDynamicChangeVo> decideBidView = tenderProxyService.getDecideBidView(Long.valueOf(param.get("subProjectId")));
            return decideBidView;
        }


    }

}
