package cn.bidlink.report.app.model.vo.purchase;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/5/23
 */
public class TotalItemsSeparatelyVo {

    /**
     * 总项报价项
     */
    private String schemeSubentry;
    /**
     *方案名称
     */
    private String name;
    /**
     *分项报价项
     */
    private String schemeSumup;

    public TotalItemsSeparatelyVo() {
    }

    public String getSchemeSubentry() {
        return schemeSubentry;
    }

    public void setSchemeSubentry(String schemeSubentry) {
        this.schemeSubentry = schemeSubentry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchemeSumup() {
        return schemeSumup;
    }

    public void setSchemeSumup(String schemeSumup) {
        this.schemeSumup = schemeSumup;
    }
}
