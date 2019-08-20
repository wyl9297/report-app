package cn.bidlink.report.app.individuation;

import javax.servlet.http.HttpServletRequest;

/**
 * 报表url定制化参数接口
 * 此接口实现类必须声明 cn.bidlink.report.app.annotation.Individuation注解 value属性是route.properties中的要定制的目标url
 * 例如 @Individuation("purchase.purchaseBargain")
 * 每一个url只能有一个定制类
 *
 * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
 * @date :2019-08-20 14:19:59
 */
public interface ReportUrlAppender {

    /**
     * 如果需要更细粒度的定制条件 则需要重写confirm方法 自定义需要处理的条件 如果没有额外条件 则直接返回true
     *
     * @param request    the request
     * @param confUrl    the conf url
     * @param templateId the template id
     * @param urlBuilder 经过统一处理后的目标url
     * @return 是否需要后置处理
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2019-08-20 14:19:59
     */
    boolean confirm(HttpServletRequest request,String confUrl,String templateId,StringBuilder urlBuilder);

    /**
     * 在访问报表时 如果有声明了此报表url的ReportUrlAppender实现类 就会在最后执行定制类的 urlPostProcesser 方法 对目标url进行后置处理
     *
     * @param request    the request
     * @param confUrl    the conf url
     * @param templateId the template id
     * @param urlBuilder 经过统一处理后的目标url
     * @return 返回定制处理后的url
     * @author :<a href="mailto:yanlinwang@ebnew.com">王炎林</a>
     * @date :2019-08-20 14:19:59
     */
    StringBuilder urlPostProcesser(HttpServletRequest request,String confUrl,String templateId,StringBuilder urlBuilder);

}