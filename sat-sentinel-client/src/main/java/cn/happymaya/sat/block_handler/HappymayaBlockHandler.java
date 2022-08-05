package cn.happymaya.sat.block_handler;

import cn.happymaya.sat.vo.CommonResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>自定义通用的限流处理逻辑</h1>
 *
 * @author superhsc
 */
@Slf4j
public class HappymayaBlockHandler {

    /**
     * <h2>通用限流处理方法</h2>
     * 这个方法必须是 static 的
     */
    public static CommonResponse<String> qinyiHandleBlockException(BlockException exception) {

        log.error("trigger happyamaya block handler: [{}], [{}]", JSON.toJSONString(exception.getRule()), exception.getRuleLimitApp());
        return new CommonResponse<>(-1, "flow rule trigger block exception", null);
    }
}
