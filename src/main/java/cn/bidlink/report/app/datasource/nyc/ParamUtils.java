package cn.bidlink.report.app.datasource.nyc;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author : <a href="mailto:dingweixie@ebnew.com">dingweixie</a>
 * @version : v1.0
 * @date :  2019/11/15  11:47
 * @description :
 */
public class ParamUtils {
    private static Logger log = LoggerFactory.getLogger(ParamUtils.class);
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
            //todo 待E采供报表统一添加日志的时候 将该日志同时添加
            System.out.println(getErrParam(param,bitian));
        }
        return sel;
    }

}
