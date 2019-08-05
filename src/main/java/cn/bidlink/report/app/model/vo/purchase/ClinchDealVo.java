package cn.bidlink.report.app.model.vo.purchase;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/7/24
 */
public class ClinchDealVo {

    /**
     * 采购品目录ID
     */
    private Long projectItemId;
    /**
     * 采购品目录ID
     */
    private Long directoryId;
    /**
     * 规格型号/材质/...
     */
    private String spec;

    /**
     * 采购品名称
     */
    private String name;
    /**
     * 采购数量
     */
    private BigDecimal purchaseAmount;
    /**
     * 市场参考价格
     */
    private BigDecimal marketPrice;
    /**
     * 采购品计量单位
     */
    private String unitName;
    /**
     * 计划价格
     */
    private BigDecimal planPrice;

    public Long getProjectItemId() {
        return projectItemId;
    }

    public void setProjectItemId(Long projectItemId) {
        this.projectItemId = projectItemId;
    }

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(BigDecimal planPrice) {
        this.planPrice = planPrice;
    }
}
