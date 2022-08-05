package cn.happymaya.sat.feign;

import cn.happymaya.sat.account.BalanceInfo;
import cn.happymaya.sat.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <h1>用户账户服务 Feign 接口</h1>
 *
 * @author superhsc
 */
@FeignClient(contextId = "NotSecuredBalanceClient", value = "e-commerce-account-service")
public interface NotSecuredBalanceClient {

    @RequestMapping(value = "/sat-account-service/balance/deduct-balance", method = RequestMethod.PUT)
    CommonResponse<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo);
}
