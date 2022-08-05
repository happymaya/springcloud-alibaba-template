package cn.happymaya.sat.feign.hystrix;

import cn.happymaya.sat.common.TableId;
import cn.happymaya.sat.feign.SecuredGoodsClient;
import cn.happymaya.sat.goods.SimpleGoodsInfo;
import cn.happymaya.sat.vo.CommonResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * <h1>商品服务熔断降级兜底</h1>
 * @author superhsc
 * */
@Slf4j
@Component
public class GoodsClientHystrix implements SecuredGoodsClient {

    @Override
    public CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            TableId tableId) {

        log.error("[goods client feign request error in order service] get simple goods" +
                "error: [{}]", JSON.toJSONString(tableId));
        return new CommonResponse<>(
                -1,
                "[goods client feign request error in order service]",
                Collections.emptyList()
        );
    }
}
