package cn.bidlink.report.app.datasource.auction;

import cn.bidlink.procurement.auction.cloud.dto.AuctionProjectItemDto;
import cn.bidlink.procurement.auction.cloud.service.AuctionProjectItemRestService;
import cn.bidlink.procurement.auction.cloud.vo.ResultTabListVo;
import cn.bidlink.report.app.datasource.abstracts.AbstractBaseTableData;
import cn.bidlink.report.app.utils.DataServiceFactory;
import com.fr.base.Parameter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AuctionItemListDataSource
 * @Author Administrator
 * @Description //竞价项目采购品数据源
 * @Date 2019/5/31 15:26
 * @Version 1.0
 **/
public class AuctionItemListDataSource extends AbstractBaseTableData {

    @Override
    protected Parameter[] getParameter() {
        return new Parameter[]{
                new Parameter("projectId"),
                new Parameter("companyId")
        };
    }

    @Override
    protected String[] getColumn() {
        return new String[]{"code", "name", "unitName", "spec", "initialPrice" ,"purchaseAmount", "techParameters", "needTime", "appliedEnterprise", "appliedPersonAndPhone", "useDept", "purpose", "comment", "planPrice", "sourceName" , "createTime"};
    }

    @Override
    protected List getQueryData(DataServiceFactory dataServiceFactory, Map<String, String> param) {
        //获取服务
        AuctionProjectItemRestService auctionProjectItemRestService = dataServiceFactory.getDataService(AuctionProjectItemRestService.class);
        //获取报表查询的参数
        Long projectId = Long.valueOf(param.get("projectId"));
        Long companyId = Long.valueOf(param.get("companyId"));

        ResultTabListVo<AuctionProjectItemDto> auctionProjectItemDtoResultTabListVo
                = auctionProjectItemRestService.projectViewList(companyId, projectId, 0, 0);
        List<AuctionProjectItemDto> list = auctionProjectItemDtoResultTabListVo.getTableData();
        return list;
    }
}