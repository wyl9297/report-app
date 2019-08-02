package cn.bidlink.report.app.model.vo.tender;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  11:14 2019-06-28
 * @Description :   分项报价表  采购品维度  [分项报价项实体]
 */
public class SubentryQuotationFormQuoteItemVO {

    private String key;

    private String value;

    private String bidProjectItemId;

    private String supplierId;

    private String name;

    private String code;

    private String directoryId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBidProjectItemId() {
        return bidProjectItemId;
    }

    public void setBidProjectItemId(String bidProjectItemId) {
        this.bidProjectItemId = bidProjectItemId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }
}
