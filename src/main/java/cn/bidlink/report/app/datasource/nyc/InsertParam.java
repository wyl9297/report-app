package cn.bidlink.report.app.datasource.nyc;

import java.util.*;

public class InsertParam {
    public static List insert(String[] strings) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            resultMap.put(strings[i], 10086);
        }
        resultList.add(resultMap);
        return resultList;
    }
}
