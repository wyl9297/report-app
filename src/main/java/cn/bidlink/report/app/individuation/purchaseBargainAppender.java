package cn.bidlink.report.app.individuation;

import cn.bidlink.report.app.annotation.Individuation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Individuation("purchase.purchaseBargain")
public class purchaseBargainAppender implements ReportUrlAppender {

    @Override
    public boolean confirm(HttpServletRequest request, String confUrl, String templateId, StringBuilder urlBuilder) {
        return true;
    }

    @Override
    public StringBuilder urlPostProcesser(HttpServletRequest request, String confUrl, String templateId, StringBuilder urlBuilder) {
        return urlBuilder;
    }
}
