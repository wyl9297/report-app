package cn.bidlink.report.app.datasource.tender;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.service.TenderProxyService;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SeperateBiddingTitleDataSource
 * @Author Administrator
 * @Description //招标项目分项报价表供应商表头数据源
 * @Date 2019/5/30 17:58
 * @Version 1.0
 **/
public class SeperateBiddingTitleDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("subProjectId"),
                new Parameter("supplierIds"),
                new Parameter("quoteTurn")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"supplierName", "supplierId"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        TenderProxyService tenderProxyService = dataServiceFactory.getDataService(TenderProxyService.class);
        //获取报表查询的参数
        Long subProjectId = Long.valueOf(param.get("subProjectId"));
        String supplierIds = param.get("supplierIds");
        String quoteTurn = param.get("quoteTurn");
        boolean isNumber = StringUtils.isNumeric(quoteTurn);
        if(!StringUtils.isEmpty(quoteTurn) && isNumber){
            // 如果quoteTurn 不为null，并且是数字 ， 多轮报价
            List<LinkedHashMap<String, String>> seperateBiddingTitDataSource = tenderProxyService.getSeperateBiddingTitDataSource(subProjectId, supplierIds, Integer.valueOf(quoteTurn));

            return seperateBiddingTitDataSource;
        }else{
            List<LinkedHashMap<String, String>> list = tenderProxyService.getSeperateBiddingTitleDataSource(subProjectId, supplierIds);
            return list;
        }

    }

}
