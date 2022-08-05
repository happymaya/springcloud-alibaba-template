package cn.happymaya.sat.feign;

import cn.happymaya.sat.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h1>通过 Sentinel 对 OpenFeign 实现熔断降级</h1>
 *
 * @author superhsc
 */
@FeignClient(value = "sat", fallback = SentinelFeignClientFallback.class)
public interface SentinelFeignClient {

    @RequestMapping(value = "happymaya", method = RequestMethod.GET)
    CommonResponse<String> getResultByFeign(@RequestParam Integer code);
}
