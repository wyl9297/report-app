package cn.bidlink.report.app.model.vo.line;

import cn.bidlink.usage.supplier.service.vo.HomePageVo;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/10/24
 */
public class LineTimeChange extends HomePageVo {

    private String timeMonthDay;

    public String getTimeMonthDay() {
        return timeMonthDay;
    }

    public void setTimeMonthDay(String timeMonthDay) {
        this.timeMonthDay = timeMonthDay;
    }
}
