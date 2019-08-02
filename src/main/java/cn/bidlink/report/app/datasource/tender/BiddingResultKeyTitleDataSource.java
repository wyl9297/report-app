package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.report.app.model.vo.tender.BiddingResultDynamicChangeVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:招标项目->中标结果动态数据集表头
 * @Date 2019/5/30
 *
 */
public class BiddingResultKeyTitleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("subProjectId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"key","title"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        TenderProxyService tenderProxyService = dataServiceFactory.getDataService(TenderProxyService.class);
        List<BiddingResultDynamicChangeVo> biddingResultDynamicChangeVo = tenderProxyService.getDecideBidSupplierViewKeyTitle(Long.valueOf(param.get("subProjectId")));
        return biddingResultDynamicChangeVo;
    }

}
