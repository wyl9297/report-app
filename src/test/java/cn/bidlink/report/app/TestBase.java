package cn.bidlink.report.app;

import com.google.gson.GsonBuilder;
import junit.framework.TestCase;

public abstract class TestBase extends TestCase {
    /**
    * 打印测试对象
    * @param obj
    */
    public void print(Object obj) {
        this.print(obj, true);
    }

    public void print(Object obj, boolean pretty) {
        GsonBuilder gb = new GsonBuilder();
        if (pretty)
            gb.setPrettyPrinting();
        gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(gb.create().toJson(obj));
    }
}