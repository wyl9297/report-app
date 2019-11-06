package cn.bidlink.report.app.datasource.nyc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertParam {
    public List insert(String[] strings) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            resultMap.put(strings[i], 555);
        }
        resultList.add(resultMap);
        return resultList;
    }
}
