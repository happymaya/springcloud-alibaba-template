package cn.happymaya.sat.feign;

import cn.happymaya.sat.common.TableId;
import cn.happymaya.sat.feign.hystrix.GoodsClientHystrix;
import cn.happymaya.sat.goods.SimpleGoodsInfo;
import cn.happymaya.sat.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <h1>安全的商品服务 Feign 接口</h1>
 *
 * @author superhsc
 */
@FeignClient(contextId = "SecuredGoodsClient", value = "e-commerce-goods-service", fallback = GoodsClientHystrix.class)
@Component
public interface SecuredGoodsClient {

    /**
     * <h2>根据 ids 查询简单的商品信息</h2>
     */
    @RequestMapping(value = "/sat-goods-service/goods/simple-goods-info", method = RequestMethod.POST)
    CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(@RequestBody TableId tableId);
}
