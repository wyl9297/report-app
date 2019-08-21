package cn.bidlink.report.app.utils;

import cn.bidlink.base.ServiceResult;
import cn.bidlink.framework.boot.web.context.UserContext;
import cn.bidlink.procurement.appset.dal.dto.AppsetPrivilegeControlDto;
import cn.bidlink.procurement.appset.dal.enums.PrivilegeEnum;
import cn.bidlink.procurement.appset.dal.service.DubboAppsetPrivilegeModuleNodeControlService;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import com.fr.base.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:采购项目：币种校验工具类，为true展示币种
 * @Date 2019/8/21
 */
public class CurrencyFlagUtil extends AbstractBaseTableData {



    private Logger logger = LoggerFactory.getLogger(CurrencyFlagUtil.class);

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("module")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"flag"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {

        DubboAppsetPrivilegeModuleNodeControlService dubboAppsetPrivilegeModuleNodeControlService = dataServiceFactory.getDataService(DubboAppsetPrivilegeModuleNodeControlService.class);

        ServiceResult<List<AppsetPrivilegeControlDto>> controlServiceResult = dubboAppsetPrivilegeModuleNodeControlService.findPrivilegeListByModuleCode(PrivilegeEnum.PrivileModuleEnum.PURCHASE.getCode(), UserContext.getCompanyId());
        if (!controlServiceResult.getSuccess()){
            logger.error("调用dubboAppsetPrivilegeModuleNodeControlService.findPrivilegeListByModuleCode接口时发生异常");
            throw new RuntimeException("err_code"+controlServiceResult.getCode()+",err_msg"+controlServiceResult.getMessage());
        }
        List<AppsetPrivilegeControlDto> result = controlServiceResult.getResult();
        List<Boolean> newFlag = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < result.size() ; i++) {
            if (result.get(i).getModuleNodeCode().equals(35)){

                flag = result.get(i).getIsChoose().equals(1);
            }
        }
        newFlag.add(flag);

        return newFlag;

    }

}
