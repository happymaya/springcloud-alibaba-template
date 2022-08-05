package cn.happymaya.sat.feign.hystrix;

import cn.happymaya.sat.account.AddressInfo;
import cn.happymaya.sat.common.TableId;
import cn.happymaya.sat.feign.AddressClient;
import cn.happymaya.sat.vo.CommonResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * <h1>账户服务熔断降级兜底策略</h1>
 *
 * @author superhsc
 */
@Slf4j
@Component
public class AddressClientHystrix implements AddressClient {

    @Override
    public CommonResponse<AddressInfo> getAddressInfoByTablesId(TableId tableId) {

        log.error("[account client feign request error in order service] get address info" + "error: [{}]", JSON.toJSONString(tableId));
        return new CommonResponse<>(-1, "[account client feign request error in order service]", new AddressInfo(-1L, Collections.emptyList()));
    }
}
