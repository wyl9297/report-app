package cn.bidlink.report.app.model.vo.purchase;

import java.math.BigDecimal;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:31 2019-07-10
 * @Description :   成交定价 供应商数据vo
 */

public class ProjectSupplierDealVo {

    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 采购品id
     */
    private Long projectItemId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 报价单价
     */
    private java.math.BigDecimal quoteUnitPrice;

    /**
     * 报价总价
     */
    private java.math.BigDecimal quoteTotalPrice;

    /**
     * 采购品自定义报项
     */
    private String quoteItem;

    /**
     * @描述:成交单价
     * @字段:deal_unit_price DECIMAL(12)
     */
    private java.math.BigDecimal dealUnitPrice;

    /**
     * 成交数量
     */
    private java.math.BigDecimal dealAmount;

    /**
     * 成交比例
     */
    private java.math.BigDecimal dealRation;

    /**
     * 成交总额
     */
    private java.math.BigDecimal dealTotalPrice;

    /**
     * 成交说明
     */
    private String dealDescription;

    /**
     *  成交总额(针对项目)
     */
    private java.math.BigDecimal dealTotalPriceWithProject;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Long projectItemId) {
        this.projectItemId = projectItemId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getQuoteUnitPrice() {
        return quoteUnitPrice;
    }

    public void setQuoteUnitPrice(BigDecimal quoteUnitPrice) {
        this.quoteUnitPrice = quoteUnitPrice;
    }

    public BigDecimal getQuoteTotalPrice() {
        return quoteTotalPrice;
    }

    public void setQuoteTotalPrice(BigDecimal quoteTotalPrice) {
        this.quoteTotalPrice = quoteTotalPrice;
    }

    public String getQuoteItem() {
        return quoteItem;
    }

    public void setQuoteItem(String quoteItem) {
        this.quoteItem = quoteItem;
    }

    public BigDecimal getDealUnitPrice() {
        return dealUnitPrice;
    }

    public void setDealUnitPrice(BigDecimal dealUnitPrice) {
        this.dealUnitPrice = dealUnitPrice;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    public BigDecimal getDealRation() {
        return dealRation;
    }

    public void setDealRation(BigDecimal dealRation) {
        this.dealRation = dealRation;
    }

    public BigDecimal getDealTotalPrice() {
        return dealTotalPrice;
    }

    public void setDealTotalPrice(BigDecimal dealTotalPrice) {
        this.dealTotalPrice = dealTotalPrice;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public BigDecimal getDealTotalPriceWithProject() {
        return dealTotalPriceWithProject;
    }

    public void setDealTotalPriceWithProject(BigDecimal dealTotalPriceWithProject) {
        this.dealTotalPriceWithProject = dealTotalPriceWithProject;
    }
}
