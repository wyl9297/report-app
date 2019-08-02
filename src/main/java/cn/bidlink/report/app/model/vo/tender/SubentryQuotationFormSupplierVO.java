package cn.bidlink.report.app.model.vo.tender;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  11:14 2019-06-28
 * @Description :   分项报价表  采购品维度  [供应商数据]
 */
public class SubentryQuotationFormSupplierVO {

    private String key;

    private String value;

    private String bidProjectItemId;

    private String supplierId;

    private String name;

    private String code;

    private String supplierName;

    private String quoteUnitPrice;

    private String quoteTotalPrice;

    private String differenceAmount;

    private String differenceRatio;

    private String quoteAmount;

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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getQuoteUnitPrice() {
        return quoteUnitPrice;
    }

    public void setQuoteUnitPrice(String quoteUnitPrice) {
        this.quoteUnitPrice = quoteUnitPrice;
    }

    public String getQuoteTotalPrice() {
        return quoteTotalPrice;
    }

    public void setQuoteTotalPrice(String quoteTotalPrice) {
        this.quoteTotalPrice = quoteTotalPrice;
    }

    public String getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(String differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    public String getDifferenceRatio() {
        return differenceRatio;
    }

    public void setDifferenceRatio(String differenceRatio) {
        this.differenceRatio = differenceRatio;
    }

    public String getQuoteAmount() {
        return quoteAmount;
    }

    public void setQuoteAmount(String quoteAmount) {
        this.quoteAmount = quoteAmount;
    }
}
