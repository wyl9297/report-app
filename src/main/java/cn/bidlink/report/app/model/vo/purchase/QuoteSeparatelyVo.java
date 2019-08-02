package cn.bidlink.report.app.model.vo.purchase;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/5/22
 */
public class QuoteSeparatelyVo {

    /**
     * 分项报价key
     */
    private String key;
    /**
     *分项报价value
     */
    private String value;
    /**
     *分项报价标题
     */
    private String title;

    /**
     * 采购品id
     */
    private Long projectItemId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 供应商报价采购品id(采购品-供应商维度：成交定价增加)
     */
    private Long supplierProjectItemId;

    /**
     *(采购品-供应商维度：成交定价增加)
     */
    private Long directoryId;

    public QuoteSeparatelyVo() {
    }

    public Long getSupplierProjectItemId() {
        return supplierProjectItemId;
    }

    public void setSupplierProjectItemId(Long supplierProjectItemId) {
        this.supplierProjectItemId = supplierProjectItemId;
    }

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Long projectItemId) {
        this.projectItemId = projectItemId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
