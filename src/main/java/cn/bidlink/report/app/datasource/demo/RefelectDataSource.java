
package cn.bidlink.report.app.datasource.demo;

import cn.bidlink.procurement.purchase.cloud.dto.SupplierQuotedItemDto;
import cn.bidlink.procurement.purchase.cloud.service.ProjectSupplierItemRestService;
import cn.bidlink.report.app.datasource.abstracts.AbstractRefelectReportDataHandler;
import com.fr.base.Parameter;
import com.fr.data.AbstractTableData;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 查看单个供应商报价情况[基础信息]  程序集
 */
@Deprecated
public class RefelectDataSource extends AbstractTableData {

    /**
     * 列名数组，保存程序数据集所有列名
     * */
    private String[] columnNames = null;

    /**
     * 保存查询表的实际列数量
     */
    private int colNum = 0;

    /**
     * 保存查询得到列值
     * */
    private ArrayList valueList = null;

    /**
     *  构造函数
     * */
    public RefelectDataSource(){
        //如果调用duboo或者mybatis 的方法查询时 参数没有@Param注解 那么以下参数顺序 必须与被调用dal方法的参数顺序一致 且一一对应
        setDefaultParameters(new Parameter[] {new Parameter("projectId"),new Parameter("projectSupplierId"),new Parameter("companyId")});
        //必须与sql结果集的表头一致！（若调用了ReportDataHandler.mybatisDataQuary 方法 则必须与返回结果的dto属性名对应）
        String[] columnNames = { "projectName","supplierName","quoteProjectItemCount","haveQuoteProjectItemCount"};
        colNum = columnNames.length;
        this.columnNames = columnNames;
    }

    /**
     *  准备数据
     * */
    public void init() {
        // 确保只被执行一次
        if (valueList != null) {
            return;
        }
        try {
            Object[] objects = parameters.get().toArray();
            AbstractRefelectReportDataHandler reportDataHandler = new AbstractRefelectReportDataHandler() {
                @Override
                protected Object resultHandler(Object object, Method method) {
                    SupplierQuotedItemDto supplierQuotedItemDto = (SupplierQuotedItemDto) object;
                    return supplierQuotedItemDto;
                }
            };
            valueList = reportDataHandler.reportDataQuary(ProjectSupplierItemRestService.class, "supplierQuotedItemlist",  objects, columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  实现ArrayTableData的其他四个方法，因为AbstractTableData已经实现了hasRow方法
     * */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    @Override
    public int getRowCount() {
        init();
        return valueList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        init();
        if (columnIndex >= colNum) {
            return null;
        }
        return ((Object[]) valueList.get(rowIndex))[columnIndex];
    }

    /**
     *  释放一些资源，因为可能会有重复调用，所以需释放valueList，将上次查询的结果释放掉
     * */
    @Override
    public void release() throws Exception {
        super.release();
        this.valueList = null;
    }
}
