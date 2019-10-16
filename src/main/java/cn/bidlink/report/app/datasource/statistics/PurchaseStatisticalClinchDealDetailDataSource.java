package cn.bidlink.report.app.datasource.statistics;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import cn.bidlink.report.server.ectityNewVO.PurchaseNewProjectWideTable;
import cn.bidlink.report.server.service.PurchaseStatisticalDealDetailService;
import com.fr.base.Parameter;
import com.fr.third.org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:查询采购成交明细统计列表数据集
 * @Date 2019/9/12
 *
 */
public class PurchaseStatisticalClinchDealDetailDataSource extends AbstractBaseTableData {

    private Logger logger = LoggerFactory.getLogger(PurchaseStatisticalClinchDealDetailDataSource.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("companyId"),
                new Parameter("departmentName"),
                new Parameter("catalogNamePath"),
                new Parameter("startCreateTime"),
                new Parameter("endCreateTime"),
                new Parameter("startArchiveTime"),
                new Parameter("endArchiveTime")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"id", "projectId", "companyId", "supplierId", "directoryId", "projectName", "departmentName", "catalogNamePath", "createUserName", "createTime", "archiveTime", "supplierName", "spec", "directoryName", "directoryCode", "unitName", "useDept", "bidPrice", "dealPrice", "dealAmount", "dealTotalPrice"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<PurchaseNewProjectWideTable> resultCheck = new ArrayList<>();
        String startCreateTime = param.get("startCreateTime");
        String endCreateTime = param.get("endCreateTime");
        String startArchiveTime = param.get("startArchiveTime");
        String endArchiveTime = param.get("endArchiveTime");

        Boolean flag = true;
        if (StringUtils.isNotBlank(startCreateTime) && StringUtils.isNotBlank(endCreateTime)){
            flag =true;
        }else if (StringUtils.isNotBlank(startArchiveTime) && StringUtils.isNotBlank(endArchiveTime)){
            flag =true;
        }else if (StringUtils.isBlank(startCreateTime) || StringUtils.isBlank(endCreateTime)){
            flag =false;
        }else if (StringUtils.isBlank(startArchiveTime) || StringUtils.isBlank(endArchiveTime)){
            flag =false;
        }

        if (flag){
            PurchaseStatisticalDealDetailService purchaseStatisticalDealDetailService = dataServiceFactory.getDataService(PurchaseStatisticalDealDetailService.class);
            PurchaseNewProjectWideTable purchaseNewProjectWideTable = new PurchaseNewProjectWideTable();
            purchaseNewProjectWideTable.setCompanyId(Long.valueOf(param.get("companyId")));
            purchaseNewProjectWideTable.setDepartmentName(param.get("departmentName"));
            purchaseNewProjectWideTable.setCatalogNamePath(param.get("catalogNamePath"));
            try {
                long l = System.currentTimeMillis();
                Date datetim = new Date(l);
                SimpleDateFormat dateStartDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = dateStartDefault.format(datetim);
                Date dateStartCreateTime = DateUtils.parseDate(format, "yyyy-MM-dd HH:mm:ss");

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, -1);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int date = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String dateEndDefault = year+"-"+(month<10?"0"+month:month)+"-"+(date<10?"0"+date:date)+" "+(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
                Date dateEndCreateTime = DateUtils.parseDate(dateEndDefault, "yyyy-MM-dd HH:mm:ss");

                purchaseNewProjectWideTable.setStartCreateTime(StringUtils.isNotEmpty(param.get("startCreateTime"))?DateUtils.parseDate(param.get("startCreateTime"), "yyyy-MM-dd HH:mm:ss"):dateStartCreateTime);
                purchaseNewProjectWideTable.setEndCreateTime(StringUtils.isNotEmpty(param.get("endCreateTime"))?DateUtils.parseDate(param.get("endCreateTime"), "yyyy-MM-dd HH:mm:ss"):dateEndCreateTime);
                purchaseNewProjectWideTable.setStartArchiveTime(StringUtils.isNotEmpty(param.get("startArchiveTime"))?DateUtils.parseDate(param.get("startArchiveTime"), "yyyy-MM-dd HH:mm:ss"):null);
                purchaseNewProjectWideTable.setEndArchiveTime(StringUtils.isNotEmpty(param.get("endArchiveTime"))?DateUtils.parseDate(param.get("endArchiveTime"), "yyyy-MM-dd HH:mm:ss"):null);
            }catch (Exception e){
                e.printStackTrace();
            }
            ServiceResult<List<PurchaseNewProjectWideTable>> purchaseDealStatistical = purchaseStatisticalDealDetailService.findPurchaseStatisticalDealDetail(purchaseNewProjectWideTable);
            List<PurchaseNewProjectWideTable> result = purchaseDealStatistical.getResult();
            if (result != null && result.size()>0){
                return result;
            }
            List<PurchaseNewProjectWideTable> resultNull = new ArrayList<>();
            return resultNull;
        }else {
            throw new RuntimeException("您好,请输入立项开始时间和结束时间或者归档开始时间和结束时间");
        }

    }
}