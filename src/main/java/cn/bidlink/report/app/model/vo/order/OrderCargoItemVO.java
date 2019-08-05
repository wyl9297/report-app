package cn.bidlink.report.app.model.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/7/2
 */
public class OrderCargoItemVO implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * @描述:主键 自动增长
     * @字段:id BIGINT(19)
     */
    private Long id;

    /**
     * @描述:采购品名称
     * @字段:name VARCHAR(200)
     */
    private String name;

    /**
     * @描述:采购品编码
     * @字段:code VARCHAR(100)
     */
    private String code;

    /**
     * @描述:采购品规格
     * @字段:spec VARCHAR(768)
     */
    private String spec;

    /**
     * @描述:采购品单位（如 吨等）
     * @字段:unit_name VARCHAR(20)
     */
    private String unitName;

    /**
     * @描述:备注
     * @字段:comment VARCHAR(500)
     */
    private String comment;

    /**
     * @描述:采购品备注
     * @字段:item_comment VARCHAR(500)
     */
    private String itemComment;

    /**
     * @描述:使用单位
     * @字段:use_dept VARCHAR(256)
     */
    private String useDept;

    /**
     * @描述:技术参数
     * @字段:tech_parameters VARCHAR(500)
     */
    private String techParameters;

    /**
     * @描述:用途
     * @字段:purpose VARCHAR(500)
     */
    private String purpose;

    /**
     * @描述:申报企业
     * @字段:applied_enterprise VARCHAR(256)
     */
    private String appliedEnterprise;

    /**
     * @描述:申报人、电话
     * @字段:applied_person_and_phone VARCHAR(100)
     */
    private String appliedPersonAndPhone;

    /**
     * @描述:需求日期
     * @字段:need_time DATETIME(19)
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date needTime;


    /** 非数据库字段，查询时使用 */
    private Date needTimeBegin;

    /** 非数据库字段，查询时使用 */
    private Date needTimeEnd;
    /**
     * @描述:单价
     * @字段:quote_unit_price DECIMAL(20)
     */
    private BigDecimal quoteUnitPrice;

    /**
     * @描述:子订单发货数量
     * @字段:send_number DECIMAL(20)
     */
    private BigDecimal sendNumber;

    /**
     * @描述:货单的发货量
     * @字段:cargo_send_number DECIMAL(20)
     */
    private BigDecimal cargoSendNumber;

    /**
     * @描述:签收量
     * @字段:sign_number DECIMAL(20)
     */
    private BigDecimal signNumber;

    /**
     * @描述:退货数量
     * @字段:refund_number DECIMAL(20)
     */
    private BigDecimal refundNumber;

    /**
     * @描述:实际的价格
     * @字段:actual_price DECIMAL(20)
     */
    private BigDecimal actualPrice;

    /**
     * @描述:税率
     * @字段:tax_rate DECIMAL(20)
     */
    private BigDecimal taxRate;

    /**
     * @描述:生成订单的来源id
     * @字段:data_source_id BIGINT(19)
     */
    private Long dataSourceId;

    /**
     * @描述:来源项目名称
     * @字段:data_source_project_name VARCHAR(200)
     */
    private String dataSourceProjectName;

    /**
     * @描述:订单的来源id(同步使用)
     * @字段:source_id VARCHAR(100)
     */
    private String sourceId;

    /**
     * @描述:订单id
     * @字段:order_id BIGINT(19)
     */
    private Long orderId;

    /**
     * @描述:货单id
     * @字段:order_cargo_id BIGINT(19)
     */
    private Long orderCargoId;
    /**
     * @描述:组织id
     * @字段:org_id BIGINT(20)
     */
    private Long orgId;

    /**
     * @描述:组织名称
     * @字段:org_name VARCHAR(128)
     */
    private String orgName;

    /**
     * @描述:组织路径
     * @字段:org_path VARCHAR(128)
     */
    private String orgPath;
    /**
     * @描述:企业id
     * @字段:company_id BIGINT(19)
     */
    private Long companyId;

    /**
     * @描述:采购商名称
     * @字段:company_name VARCHAR(200)
     */
    private String companyName;

    /**
     * @描述:创建人id
     * @字段:create_user_id BIGINT(19)
     */
    private Long createUserId;

    /**
     * @描述:创建人姓名
     * @字段:create_user_name VARCHAR(100)
     */
    private String createUserName;

    /**
     * @描述:创建时间
     * @字段:create_time DATETIME(19)
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    /** 非数据库字段，查询时使用 */
    private Date createTimeBegin;

    /** 非数据库字段，查询时使用 */
    private Date createTimeEnd;
    /**
     * @描述:修改人id
     * @字段:update_user_id BIGINT(19)
     */
    private Long updateUserId;

    /**
     * @描述:修改人
     * @字段:update_user_name VARCHAR(100)
     */
    private String updateUserName;

    /**
     * @描述:修改时间
     * @字段:update_time DATETIME(19)
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    /** 非数据库字段，查询时使用 */
    private Date updateTimeBegin;

    /** 非数据库字段，查询时使用 */
    private Date updateTimeEnd;

    /**
     * @描述:是否删除，0否1是，默认0
     * @字段:is_delete TINYINT(4)
     */
    private Integer isDelete;

    /**
     * 分项报价项
     */
    private String quoteItem;

    /**
     * 单品总价(新添加)
     */
    private BigDecimal quoteTotalPrice;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getItemComment() {
        return itemComment;
    }

    public void setItemComment(String itemComment) {
        this.itemComment = itemComment;
    }

    public String getUseDept() {
        return useDept;
    }

    public void setUseDept(String useDept) {
        this.useDept = useDept;
    }

    public String getTechParameters() {
        return techParameters;
    }

    public void setTechParameters(String techParameters) {
        this.techParameters = techParameters;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAppliedEnterprise() {
        return appliedEnterprise;
    }

    public void setAppliedEnterprise(String appliedEnterprise) {
        this.appliedEnterprise = appliedEnterprise;
    }

    public String getAppliedPersonAndPhone() {
        return appliedPersonAndPhone;
    }

    public void setAppliedPersonAndPhone(String appliedPersonAndPhone) {
        this.appliedPersonAndPhone = appliedPersonAndPhone;
    }

    public Date getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Date needTime) {
        this.needTime = needTime;
    }

    public Date getNeedTimeBegin() {
        return needTimeBegin;
    }

    public void setNeedTimeBegin(Date needTimeBegin) {
        this.needTimeBegin = needTimeBegin;
    }

    public Date getNeedTimeEnd() {
        return needTimeEnd;
    }

    public void setNeedTimeEnd(Date needTimeEnd) {
        this.needTimeEnd = needTimeEnd;
    }

    public BigDecimal getQuoteUnitPrice() {
        return quoteUnitPrice;
    }

    public void setQuoteUnitPrice(BigDecimal quoteUnitPrice) {
        this.quoteUnitPrice = quoteUnitPrice;
    }

    public BigDecimal getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(BigDecimal sendNumber) {
        this.sendNumber = sendNumber;
    }

    public BigDecimal getCargoSendNumber() {
        return cargoSendNumber;
    }

    public void setCargoSendNumber(BigDecimal cargoSendNumber) {
        this.cargoSendNumber = cargoSendNumber;
    }

    public BigDecimal getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(BigDecimal signNumber) {
        this.signNumber = signNumber;
    }

    public BigDecimal getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(BigDecimal refundNumber) {
        this.refundNumber = refundNumber;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceProjectName() {
        return dataSourceProjectName;
    }

    public void setDataSourceProjectName(String dataSourceProjectName) {
        this.dataSourceProjectName = dataSourceProjectName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderCargoId() {
        return orderCargoId;
    }

    public void setOrderCargoId(Long orderCargoId) {
        this.orderCargoId = orderCargoId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTimeBegin() {
        return updateTimeBegin;
    }

    public void setUpdateTimeBegin(Date updateTimeBegin) {
        this.updateTimeBegin = updateTimeBegin;
    }

    public Date getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(Date updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getQuoteItem() {
        return quoteItem;
    }

    public void setQuoteItem(String quoteItem) {
        this.quoteItem = quoteItem;
    }

    public BigDecimal getQuoteTotalPrice() {
        return quoteTotalPrice;
    }

    public void setQuoteTotalPrice(BigDecimal quoteTotalPrice) {
        this.quoteTotalPrice = quoteTotalPrice;
    }
}
