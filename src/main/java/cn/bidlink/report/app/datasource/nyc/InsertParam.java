package cn.bidlink.report.app.datasource.nyc;

import com.fr.base.Parameter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;

public class InsertParam {
    public static List insert(String[] strings) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int j=0; j<6; j++){
            Map<String, Object> resultMap = new HashMap<>();
            for (int i = 0; i < strings.length; i++) {
                resultMap.put(strings[i], 123+j);
            }
            resultList.add(resultMap);
        }
        return resultList;
    }

//    public static boolean sel(Map<String, String> param) {
//        Boolean flag = true;
//        Set<String> strings = param.keySet();
//        for (String key : strings){
//            if (param.get(key) == null || "".equals(param.get(key))){
//                flag = false;
//            }
//        }
//        return flag;
//    }


    // 返回参数是否异常的方法
    public static boolean sel(Map<String, String> param, String... bitian){
        String errParam = getErrParam(param, bitian);
        return errParam.isEmpty();
    }

    // 返回有异常的参数
    public static String getErrParam(Map<String, String> param, String... bitian){
        String errParam = "";
        for (String key : bitian){
            if (StringUtils.isEmpty(param.get(key))){
                errParam = errParam + key+" ";
            }
        }
        return errParam;
    }

    // 包装了 判断异常 +  返回异常参数的方法  也叫 判断方法 因为方法返回判断结果
    public static boolean panduan(Map<String, String> param, String... bitian){
        boolean sel = sel(param, bitian);
        if( sel == Boolean.FALSE){
          //todo 随后将该返回改造成日志
            System.out.println(getErrParam(param,bitian));
        }
        return sel;
    }


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","");
        map.put("4","");
        map.put("5","");
        System.out.println(panduan(map,"1","2","3","4","5"));


    }
}






