package cn.bidlink.report.app.model.vo.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:
 * @Date 2019/7/15
 */
public class TaskRecodeDtoVO {

    /**
     * 任务ID
     */
    @ApiModelProperty("任务ID")
    private     String  taskId;
    /**
     * The Login name.
     * 审批人登录名
     *
     */
    @ApiModelProperty("审批人登录名")
    private     String  loginName;
    /**
     * The User name.
     * 审批人名称
     */
    @ApiModelProperty("审批人名称")
    private     String  userName;
    /**
     * The Approve time.
     * 审批时间
     */
    @ApiModelProperty("审批时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private     Date    approveTime;
    /**
     * The Approve suggestion.
     * 审批意见
     */
    @ApiModelProperty("审批意见")
    private     String  approveSuggestion;
    /**
     * The Approve result.
     * 每个任务的审批结果
     */
    @ApiModelProperty("审批结果")
    private     String  taskApproveResult;
    /**
     * 查看状态 1 未查看 2 已查看
     */
    @ApiModelProperty("查看状态")
    private Integer viewStatus;

    @ApiModelProperty("查看时间")
    private Date viewTime;

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getApproveSuggestion() {
        return approveSuggestion;
    }

    public void setApproveSuggestion(String approveSuggestion) {
        this.approveSuggestion = approveSuggestion;
    }

    public String getTaskApproveResult() {
        return taskApproveResult;
    }

    public void setTaskApproveResult(String taskApproveResult) {
        this.taskApproveResult = taskApproveResult;
    }

    public Integer getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(Integer viewStatus) {
        this.viewStatus = viewStatus;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
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

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}
