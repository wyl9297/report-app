package cn.bidlink.report.app.datasource.abstracts;

import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;
import com.fr.data.AbstractTableData;
import com.fr.stable.ParameterProvider;
import org.springframework.util.ReflectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractBaseTableData extends AbstractTableData {
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
    public AbstractBaseTableData(){
        setDefaultParameters(getParameter());
        String[] columnNames = getColumn();
        this.columnNames = columnNames;
        this.colNum = columnNames.length;
    }

    /**
     *  准备数据
     * */
    public void init() {
        // 确保只被执行一次
        if (valueList != null) {
            return;
        }
        Map<String,String> param = paramToMap();
        List list = getQueryData(new DataServiceFactory(), param);
        //将结果转换成报表查询需要的二维数组
        if (list.size() != 0 && Map.class.isAssignableFrom(list.get(0).getClass())){
            valueList = this.saveData(list,columnNames);
        } else if ( list.size() != 0 && isBasicType(list.get(0).getClass().getName()) ) {
            if( columnNames.length != 1 ){
                throw new RuntimeException("返回结果是一个基本数据类型 只能设置一个列名");
            }
            valueList = this.saveBasicTypeData(list);
        } else {
            valueList = this.saveDataByReflect(list,columnNames);
        }
    }

    protected abstract List getQueryData(DataServiceFactory dataServiceFactory, Map<String,String> param);

    protected abstract Parameter[] getParameter();

    protected abstract String[] getColumn();

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

    private Map<String,String> paramToMap(){
        Object[] objects = parameters.get().toArray();
        Map map = new HashMap( (int) ((float) objects.length / 0.75F + 1.0F) );
        Arrays.stream(objects).forEach(object ->{
            String name = ((ParameterProvider) object).getName();
            String value = ((ParameterProvider) object).getValue().toString();
            map.put(name , value);
        });
        return map;
    }

    private ArrayList saveDataByReflect(List<Object> dataList, String[] columnNames) {
        ArrayList<Map<String, Object>> valueList = new ArrayList<>();
        HashMap hashMap = new HashMap((int) ((float) colNum / 0.75F + 1.0F));
        Arrays.stream(columnNames).forEach(columnName -> {
            hashMap.put(columnName,null);
        });
        dataList.forEach(o -> {
            Map map = new HashMap( (int) ((float) colNum / 0.75F + 1.0F) );
            ReflectionUtils.doWithFields(o.getClass(), field -> {
                field.setAccessible(true);
                String name = field.getName();
                Object dataObj = field.get(o);
                if( hashMap.containsKey(name) && null != dataObj ){
                    if("java.util.Date".equals(dataObj.getClass().getName())){
                        Date date = (Date) field.get(o);
                        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        map.put(name,dateStr);
                    } else {
                        map.put(name,field.get(o).toString());
                    }
                }
            });
            valueList.add(map);
        });
        return saveData(valueList,columnNames);
    }

    private ArrayList saveData(List<Map<String, Object>> list, String[] columnNames){
        ArrayList arrayList = new ArrayList();
        // 用对象保存数据
        list.forEach(map -> {
            Object[] objArray = new Object[colNum];
            Stream.iterate( 0 , i -> i+1 ).limit(colNum).forEach( i -> {
                objArray[i] = map.get(columnNames[i]);
            });
            arrayList.add(objArray);
        });
        return arrayList;
    }

    private ArrayList saveBasicTypeData(List<Object> list){
        ArrayList arrayList = new ArrayList();
        // 用对象保存数据
        list.forEach(object -> {
            Object[] objArray = new Object[1];
            objArray[0] = object;
            arrayList.add(objArray);
        });
        return arrayList;
    }

    private boolean isBasicType(String typeclass1){
        if( "java.lang.Long".equals(typeclass1) ){
            return true;
        } else if ( "java.lang.String".equals(typeclass1)){
            return true;
        } else if ( "java.lang.Integer".equals(typeclass1)   ){
            return true;
        } else if("int".equals(typeclass1)){
            return true;
        } else if ("long".equals(typeclass1)){
            return true;
        } else if ( "java.math.BigDecimal".equals(typeclass1)  ){
            return true;
        }
        return false;
    }
}
