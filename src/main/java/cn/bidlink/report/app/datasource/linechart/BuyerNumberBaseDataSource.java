
package cn.bidlink.report.app.datasource.linechart;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.supplier_usage_statistics_cloud.response.ResponseResult;
import cn.bidlink.supplier_usage_statistics_cloud.service.cloud.LineDiagramCloudService;
import com.fr.base.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：成交定价采购品-供应商维度 项目基本信息
 * @Date 2019/7/12
 *
 */
public class BuyerNumberBaseDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("startTime"),
                new Parameter("endTime")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"creatTime", "timeYears", "timeQuarter", "timeMonth", "timeDay", "count"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {


        LineDiagramCloudService lineDiagramCloudService = dataServiceFactory.getDataService(LineDiagramCloudService.class);
        PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
        try {
            purchaseNewProjectWideTable.setStartCreateTime(StringUtils.isNotEmpty(param.get("startTime"))? DateUtils.parseDate(param.get("startTime"), "yyyy-MM-dd HH:mm:ss"):null);
            purchaseNewProjectWideTable.setEndCreateTime(StringUtils.isNotEmpty(param.get("endTime"))? DateUtils.parseDate(param.get("endTime"), "yyyy-MM-dd HH:mm:ss"):null);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResponseResult createBuyerCount = lineDiagramCloudService.getCreateBuyerCount(purchaseNewProjectWideTable.getStartCreateTime(), purchaseNewProjectWideTable.getEndCreateTime());
        List<Map<String, Object>> data = (List<Map<String, Object>>) createBuyerCount.getData();
        return data;

    }

}
