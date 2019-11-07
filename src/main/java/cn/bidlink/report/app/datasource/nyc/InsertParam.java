package cn.bidlink.report.app.datasource.nyc;

import java.util.*;

public class InsertParam {
    public static List insert(String[] strings) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Random random = new Random();
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            // resultMap.put(strings[i], random.nextInt(10000)+1000);
            resultMap.put(strings[i], 123);
        }
        resultList.add(resultMap);
        return resultList;
    }
}
