package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.report.app.model.vo.tender.BiddingResultVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:招标项目->开标一览表结果 (基础数据集)
 * @Date 2019/5/31
 *
 */
public class BiddingOpeningProjectDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("subProjectId"),
                new Parameter("quoteTurn")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id","supplierId","projectId","subProjectId","supplierName"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        TenderProxyService tenderProxyService = dataServiceFactory.getDataService(TenderProxyService.class);
        List<BiddingResultVo> biddingResultVo = tenderProxyService.getDecideBidViewProject(Long.valueOf(param.get("subProjectId")),param.get("quoteTurn"));
        return biddingResultVo;
    }

}
