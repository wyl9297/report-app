package cn.bidlink.report.app.datasource.nyc;

import cn.bidlink.nyc.core.dto.ResultDto;
import cn.bidlink.nyc.core.enums.CommonIntSF;
import cn.bidlink.nyc.saas.workflow.server.dto.ApproveRecodeDto;
import cn.bidlink.nyc.saas.workflow.server.dto.ApproveRecodeParamDto;
import cn.bidlink.nyc.saas.workflow.server.dto.TaskRecodeDto;
import cn.bidlink.nyc.workflow.service.workflow.WorkflowApproveDubboService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author : <a href="mailto:liuqi@ebnew.com">liuqi</a>
 * @version : v1.0
 * @date :  2019-12-16 16:35
 * @description :
 */
public class ApprovalCommon {

    private static Logger log = LoggerFactory.getLogger(ApprovalCommon.class);

    public List<Map<String, Object>> processItemAndItemApproval(WorkflowApproveDubboService dataService, String projectId, String companyId) {
        // 保存查询得到列值
        List valueList = Lists.newArrayList();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ApproveRecodeParamDto approveRecodeParamDto = new ApproveRecodeParamDto();
            approveRecodeParamDto.setProjectId(Long.valueOf(projectId));
            approveRecodeParamDto.setCompanyId(Long.valueOf(companyId));
            //采购项目对应的枚举  source:WorkflowEnums.ModuleEnum.PURCHASE
            approveRecodeParamDto.setModule(3);
            //采购结果处理的枚举  source:WorkflowEnums.ModuleNodeEnum.PURCHASE_CGJGCL_SP
            approveRecodeParamDto.setModuleNodeType(33);
            ResultDto approveRecodesResult = dataService.findApproveRecodes(approveRecodeParamDto);
            if (null != approveRecodesResult && approveRecodesResult.getResCode() == CommonIntSF.SUCCESS.getCode()) {
                List<List<ApproveRecodeDto>> resultApproveRecodeDtos = (List<List<ApproveRecodeDto>>) approveRecodesResult.getData();
                Integer approvCount =   resultApproveRecodeDtos.size();
                for (List<ApproveRecodeDto> approveRecodeList : resultApproveRecodeDtos ) {
                    for (ApproveRecodeDto dto : approveRecodeList) {
                        List<TaskRecodeDto> taskList = dto.getTaskRecodeDtos();
                        for (TaskRecodeDto task : taskList) {
                            Map<String, Object> map = Maps.newLinkedHashMap();
                            map.put("approveCount",this.getIndex(approvCount));
                            map.put("index",dto.getIndex());
                            map.put("approve_user_name",task.getUserName());
                            map.put("approve_opition",task.getApproveSuggestion());
                            if (task.getApproveTime() == null) {
                                continue;
                            }else {
                                map.put("create_tm",sdf.format(task.getApproveTime()));
                            }
                            map.put("approve_result",task.getTaskApproveResult());
                            valueList.add(map);
                        }
                    }
                    approvCount--;
                }
            }
        } catch (Exception e) {
            log.error("调用{}方法 异常", "[cn.bidlink.report.app.datasource.nyc.ApprovalCommon.processItemAndItemApproval]");
            log.error("方法使用参数：[projectId:{}, companyId:{}]", projectId, companyId);
            log.error("异常信息：{}", e);
        }
        return valueList;
    }

    public String getIndex(Integer index){
        String[] arr = {"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十"};
        return arr[index-1];
    }
}
