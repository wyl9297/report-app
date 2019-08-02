package cn.bidlink.report.app.datasource.abstracts;

import cn.bidlink.report.app.utils.ApplicationContextHandler;
import com.alibaba.dubbo.common.utils.Assert;
import com.fr.stable.ParameterProvider;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.*;

@Deprecated
public abstract class AbstractRefelectReportDataHandler {

    /**
     * 非标准返回类型（List<Map<String, Object>>）
     * */
    public ArrayList reportDataQuary(Class mapperClass , String queryMethodName , Object[] reportParams , String[] columNames ) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return dataQuary(mapperClass,queryMethodName,reportParams,columNames,columNames.length,false);
    }

    /**
     * 此方法通过各类数据源查询数据 为保证数据的完整性 参数和列名的定义要与实体类一致
     *
     * @param mapperClass          查询mapper的类对象
     * @param queryMethodName      查询方法的方法名
     * @param reportParams         报表传入的参数
     * @param columNames           列名的数组
     * @param colNum               列数
     * @param isStandardReturnType 如果mybatis查询返回类型是 List<Map<String, Object>> list 这种标准格式传true 默认是false
     * @return the list
     * @throws InvocationTargetException the invocation target exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws InstantiationException    the instantiation exception
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2019-04-01 15:50:02
     */
    private ArrayList dataQuary( Class mapperClass , String queryMethodName  , Object[] reportParams , String[] columNames , int colNum , Boolean isStandardReturnType ) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        //先获取对应的查询方法
        Object queryMapper = ApplicationContextHandler.getHandler().getBean(mapperClass);
        Method method = findMethod(mapperClass, queryMethodName);
        Parameter[] parameters = method.getParameters();
        Object[] objects = null;
        Map reportMap = paramToMap(reportParams);
        Object queryDto = null;
        //准备参数
        //检查是否是基本参数类型
        Class<?> paramType = parameters[0].getType();
        boolean isBasicType = checkIsBasicType(paramType.getName());
        if( isBasicType ){
            objects = arrayParamResolverByReflect(reportMap, parameters , reportParams);
        } else {
            //创建参数dto的实例
            queryDto = paramType.newInstance();
            dtoParamResolverByReflect(reportMap,queryDto);
        }
        //执行查询方法
        Object object ;
        if( objects != null ){
            object = method.invoke(queryMapper, objects);
        } else {
            object = method.invoke(queryMapper, queryDto);
        }
        //返回值不是集合类型 则添加到list中
        List list ;
        //此处做拓展 用于解决返回的结果五花八门的情况 使用方法： 重写resultHandler方法 入参1是被调用的方法返回的结果 在重写的方法中把外边的包装去掉 返回最终的数据结果
        object = resultHandler(object,method);
        if ( !Collection.class.isAssignableFrom(object.getClass()) ){
            list = new ArrayList();
            list.add(object);
        } else {
            list = (List) object;
        }
        //返回值类型
        if( list.size() != 0 && Map.class.isAssignableFrom(list.get(0).getClass()) ){
            isStandardReturnType = true;
        }
        //isStandardReturnType 此参数的含义是:如果mybatis查询返回类型是 List<Map<String, Object>> list 这种标准格式 则直接走saveData()逻辑 不需要通过反射
        if( isStandardReturnType ){
            return saveData(list,colNum,columNames);
        }
        return saveDataByReflect(list, columNames, colNum);
    }

    protected abstract Object resultHandler(Object object , Method method);

    /**
     * 如果调用此方法 那么columnNames数组的值必须与dto的属性名一致
     *
     * @param oriDataList the ori data list
     * @param columnNames the column names
     * @param colNum      the col num
     * @return the array list
     * @throws IllegalAccessException the illegal access exception
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2019-04-01 15:50:02
     */
    public  ArrayList saveDataByReflect( List<Object> oriDataList , String[] columnNames , int colNum )  {
        ArrayList<Map<String, Object>> mapList = new ArrayList();
        String simpleName = oriDataList.get(0).getClass().getSimpleName();
        Boolean isBasicParam = "Integer".equals(simpleName) || "int".equals(simpleName) || "Long".equals(simpleName) || "long".equals(simpleName) || "String".equals(simpleName);
        for(  Object obj : oriDataList ){
            Map map = new HashMap((int) ((float) oriDataList.size() / 0.75F + 1.0F));
            //如果返回值是int、long这种基本类型 就不需要从dto取值的逻辑了
            if( colNum == 1 && isBasicParam ){
                map.put(columnNames[0],obj);
            } else {
                Class requiredType = obj.getClass();
                Field[] fields = requiredType.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object o = null;
                    try {
                        o = field.get(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    map.put(name,o);
                }
            }
            mapList.add(map);
        }
        return saveData(mapList,colNum,columnNames);
    }

    /**
     * 如果调用mybatis查询返回的结果是 List<Map<String, Object>> list 格式 则直接调用此方法转换为报表需要的格式
     * 如果返回的是一个dto的集合 则调用 【saveDataByReflect】 转换成报表需要的格式
     *
     * @param list        the list
     * @param colNum      the col num
     * @param columnNames the column names
     * @return the array list
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2019-04-01 15:50:02
     */
    ArrayList saveData(List<Map<String, Object>> list, int colNum, String[] columnNames){
        ArrayList arrayList = new ArrayList();
        // 用对象保存数据
        Object[] objArray = null;
        for ( Map map : list) {
            objArray = new Object[colNum];
            for (int i = 0 ; i < colNum ; i ++ ){
                objArray[i] = map.get(columnNames[i]);
            }
            arrayList.add(objArray);
        }
        return arrayList;
    }

    /**
     * Param resolver string [ ].
     *
     * @param params the params
     * @return the string [ ]
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2019-04-01 15:50:02
     */

    String[] paramResolver( Object[] params ){
        String[] StrParam = new String[params.length];
        for ( int i = 0 ; i < params.length ; i ++ ) {
            if( null == params[i] ){
                StrParam[i] = null;
                continue;
            }
            StrParam[i] = ((ParameterProvider) params[i]).getValue().toString();
        }
        return StrParam;
    }



    /**
     * 如果调用mybatis查询 入参是一个实体类 则调用此方法将报表中的参数赋值给实体类
     * */
    void dtoParamResolverByReflect(Map map , Object dto) throws IllegalAccessException {
        Class requiredType = dto.getClass();
        Field[] fields = requiredType.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            Object o = map.get(name);
            if( null != o ){
                field.setAccessible(true);
                String typeclass = field.getType().getName();
                Object convert = typeConvert(typeclass, o );
                field.set(dto,convert);
            }
        }
    }

    Object[] arrayParamResolverByReflect( Map map , Parameter[] parameters ,Object[] reportParams){
        Object[] paramArray = new Object[parameters.length];
        int i = 0;
        for ( Parameter parameter : parameters ){
            String typeName = parameter.getType().getName();
            String paramName = ((ParameterProvider) reportParams[i]).getName().toString();
            Object o = map.get(paramName);
            if( null != o ){
                Object param = typeConvert(typeName, o);
                paramArray[i] = param;
            } else {
                paramArray[i] = null;
            }
            i ++;
        }
        return paramArray;
    }


    Object typeConvert(String typeclass , Object o ){
        boolean empty = StringUtils.isEmpty((String) o);
        if( "java.lang.Long".equals(typeclass) ){
            return empty ? null : Long.valueOf(o.toString());
        } else if ("long".equals(typeclass)){
            return empty ? 0L : Long.valueOf(o.toString());
        } else if ( "java.lang.String".equals(typeclass)){
            return empty ? null : o.toString();
        } else if ( "java.lang.Integer".equals(typeclass)   ){
            return empty ? null :Integer.valueOf(o.toString());
        } else if("int".equals(typeclass)){
            return empty ? 0 : Integer.valueOf(o.toString());
        } else if ( "java.math.BigDecimal".equals(typeclass)  ){
            return empty ? null : new BigDecimal(o.toString());
        }else if ( "java.lang.Boolean".equals(typeclass)   ){
            return empty ? null : Boolean.valueOf(o.toString());
        }else if ( "boolean".equals(typeclass)   ){
            return empty ? 0 : Boolean.valueOf(o.toString());
        }
        return o;
    }

    protected boolean checkIsBasicType(String typeclass){
        if( "java.lang.Long".equals(typeclass) ){
            return true;
        } else if ("long".equals(typeclass)){
            return true;
        } else if ( "java.lang.String".equals(typeclass)){
            return true;
        } else if ( "java.lang.Integer".equals(typeclass)   ){
            return true;
        } else if("int".equals(typeclass)){
            return true;
        } else if ( "java.math.BigDecimal".equals(typeclass)  ){
            return true;
        }
        return false;
    }

    protected Method findMethod(Class<?> clazz, String name){
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull(name, "Method name must not be null");
        Class<?> searchType = clazz;
        //方法名+参数 数组
        String[] np = name.split("#");
        //方法名
        String mname = np[0];
        //参数的长度
        int paramLength = np.length - 1;
        while (searchType != null) {
            Method[] methods = searchType.getDeclaredMethods();
            A:for (Method method : methods) {
                //如果方法名一致 则比对参数
                if (mname.equals(method.getName())) {
                    //如果没有包含参数 则默认该方法没有重载 直接返回第一个查询到的方法
                    if( paramLength == 0 ){
                        return method;
                    } else { //如果有参数
                        int parameterCount = method.getParameterCount();
                        if ( parameterCount == paramLength ){
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            for( int i = 0 ; i < parameterCount ; i ++  ){
                                if( !parameterTypes[ i ].getSimpleName().equals(np[ i + 1 ]) ){
                                    continue A;
                                }
                            }
                            return method;
                        } else {
                            continue;
                        }
                    }
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    protected Map paramToMap(Object[] params){
        Map map = new HashMap( (int) ((float) params.length / 0.75F + 1.0F) );
        for ( int i = 0 ; i < params.length ; i ++ ) {
            String name = ((ParameterProvider) params[i]).getName().toString();
            String value = ((ParameterProvider) params[i]).getValue().toString();
            map.put(name , value);
        }
        return map;
    }

}
