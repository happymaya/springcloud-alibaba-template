package cn.happymaya.sat.feign;

import cn.happymaya.sat.account.AddressInfo;
import cn.happymaya.sat.common.TableId;
import cn.happymaya.sat.feign.hystrix.AddressClientHystrix;
import cn.happymaya.sat.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <h1>用户账户服务 Feign 接口(安全的)</h1>
 *
 * @author superhsc
 */
@FeignClient(contextId = "AddressClient", value = "sat-account-service", fallback = AddressClientHystrix.class)
@Component
public interface AddressClient {

    /**
     * <h2>根据 id 查询地址信息</h2>
     */
    @RequestMapping(value = "/sat-account-service/address/address-info-by-table-id", method = RequestMethod.POST)
    CommonResponse<AddressInfo> getAddressInfoByTablesId(@RequestBody TableId tableId);
}
