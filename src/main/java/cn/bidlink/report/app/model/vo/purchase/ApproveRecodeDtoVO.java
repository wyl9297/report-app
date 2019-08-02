package cn.bidlink.report.app.model.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/7/15
 */
public class ApproveRecodeDtoVO {

    /**
     * The Index.
     * 序号
     */
    @ApiModelProperty("序号")
    private Integer index;
    /**
     * The Node type.
     * 节点类型
     */
    @ApiModelProperty("节点类型")
    private Integer nodeType;
    /**
     * The Node type name.
     * 节点类型名称
     */
    @ApiModelProperty("节点名称")
    private String nodeTypeName;

    /**
     * The Node approve result.
     * 节点级别的结果,如果每个节点有多条记录,则以最后一个为准
     */
    @ApiModelProperty("审批结果")
    private String nodeApproveResult;

    /**
     * 模块名称
     */
    @ApiModelProperty("模块名称")
    private String moduleName;

    /**
     * 业务节点名称
     */
    @ApiModelProperty("业务节点名称")
    private String moduleNodeName;

    /**
     * 公司ID
     */
    @ApiModelProperty("*公司ID")
    private Long companyId;

    /**
     * @描述:所属模块
     * @字段:module TINYINT(3)
     */
    @ApiModelProperty("*业务名称")
    private Integer module;

    /**
     * @描述:所属模块节点
     * @字段:module_node_type TINYINT(3)
     */
    @ApiModelProperty("*业务节点")
    private Integer moduleNodeType;

    /**
     * @描述:增加页面序号(第几次审批)
     * @字段:module_node_type TINYINT(3)
     */
    @ApiModelProperty("审批次数")
    private Integer serialNumber;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }

    public String getNodeApproveResult() {
        return nodeApproveResult;
    }

    public void setNodeApproveResult(String nodeApproveResult) {
        this.nodeApproveResult = nodeApproveResult;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleNodeName() {
        return moduleNodeName;
    }

    public void setModuleNodeName(String moduleNodeName) {
        this.moduleNodeName = moduleNodeName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Integer getModuleNodeType() {
        return moduleNodeType;
    }

    public void setModuleNodeType(Integer moduleNodeType) {
        this.moduleNodeType = moduleNodeType;
    }
}
