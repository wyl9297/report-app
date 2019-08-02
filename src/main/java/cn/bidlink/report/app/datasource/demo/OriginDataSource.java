
package cn.bidlink.report.app.datasource.demo;

import cn.bidlink.procurement.purchase.cloud.dto.ProjectSupplierItemVo;
import cn.bidlink.procurement.purchase.cloud.dto.SupplierQuotedItemDto;
import cn.bidlink.procurement.purchase.cloud.service.ProjectSupplierItemRestService;
import cn.bidlink.report.app.utils.ApplicationContextHandler;
import com.fr.base.Parameter;
import com.fr.data.AbstractTableData;
import com.fr.stable.ParameterProvider;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
/**
 * 原生报表查询数据集
 * */
public class OriginDataSource extends AbstractTableData {

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
    public OriginDataSource(){
        setDefaultParameters(new Parameter[] {new Parameter("projectId"),new Parameter("projectSupplierId"),new Parameter("companyId")});
        String[] columnNames = { "name" ,"code","unitName","purchaseAmount","projectItemId","quoteStatusStr"};
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
        valueList = new ArrayList();
        //通过spring获取接口实例
        ApplicationContext handler = ApplicationContextHandler.getHandler();
        ProjectSupplierItemRestService projectSupplierItemRestService = handler.getBean(ProjectSupplierItemRestService.class);
        //获取查询参数
        Object[] param = parameters.get().toArray();
        String projectId = ((ParameterProvider) param[0]).getValue().toString();
        String projectSupplierId = ((ParameterProvider) param[1]).getValue().toString();
        String companyId = ((ParameterProvider) param[2]).getValue().toString();
        //调用接口查询
        SupplierQuotedItemDto supplierQuotedItemDto = projectSupplierItemRestService.supplierQuotedItemlist(Long.valueOf(projectId),Long.valueOf(projectSupplierId),Long.valueOf(companyId));
        List<ProjectSupplierItemVo> supplierItemVos = supplierQuotedItemDto.getTableData();
        //给数据源设置参数
        for (int i = 0; i < supplierItemVos.size(); i++) {
            Object[] objArray = new Object[colNum];
            objArray[0] = supplierItemVos.get(i).getName();
            objArray[1] = supplierItemVos.get(i).getCode();
            objArray[2] = supplierItemVos.get(i).getUnitName();
            objArray[3] = supplierItemVos.get(i).getPurchaseAmount();
            objArray[4] = supplierItemVos.get(i).getProjectItemId();
            objArray[5] = supplierItemVos.get(i).getQuoteStatusStr();
            valueList.add(objArray);
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
