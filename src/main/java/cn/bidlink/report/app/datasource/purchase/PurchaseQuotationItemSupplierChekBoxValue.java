package cn.bidlink.report.app.datasource.purchase;

import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PurchaseQuotationItemSupplierChekBoxValue
 * @Author Administrator
 * @Description //TODO
 * @Date 2019/8/5 11:30
 * @Version 1.0
 **/
public class PurchaseQuotationItemSupplierChekBoxValue extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("Columns")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"value"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        List<String> list = new ArrayList();
        list.add("规格型号");
        list.add("数量");
        list.add("计量单位");
        String columns = param.get("Columns");
        if (StringUtils.isNotEmpty(columns)) {
            if ( columns.contains("市场参考价") ) {
                list.add("市场参考价");
            } else if ( columns.contains("计划单价") ) {
                list.add("计划单价");
            }
        }
        return list;
    }

}
