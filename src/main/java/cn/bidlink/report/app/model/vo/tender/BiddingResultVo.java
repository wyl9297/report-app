package cn.bidlink.report.app.model.vo.tender;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/5/30
 */
public class BiddingResultVo {

    private Long orderID;

    private Long id;

    private Long supplierId;

    private Long projectId;

    private Long subProjectId;
    /**
     * 投标人名称
     */
    private String supplierName;
    /**
     *是否中标
     */
    private String winBidStatusName;
    /**
     *中标金额（元）
     */
    private String winBidTotalPrice;

    /**
     * 中标/未中标理由
     */
    private String winFallReason;

    /**
     * 中标/落标通知书
     */
    private String fileName;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(Long subProjectId) {
        this.subProjectId = subProjectId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWinBidStatusName() {
        return winBidStatusName;
    }

    public void setWinBidStatusName(String winBidStatusName) {
        this.winBidStatusName = winBidStatusName;
    }

    public String getWinBidTotalPrice() {
        return winBidTotalPrice;
    }

    public void setWinBidTotalPrice(String winBidTotalPrice) {
        this.winBidTotalPrice = winBidTotalPrice;
    }

    public String getWinFallReason() {
        return winFallReason;
    }

    public void setWinFallReason(String winFallReason) {
        this.winFallReason = winFallReason;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
