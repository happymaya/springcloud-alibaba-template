package cn.happymaya.sat.fallback_handler;

import cn.happymaya.sat.vo.JwtToken;
import cn.happymaya.sat.vo.UsernameAndPassword;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>Sentinel 回退降级的兜底策略</h1>
 * 都需要是静态方法
 * */
@Slf4j
public class HappymayaFallbackHandler {

    /**
     * <h2>getTokenFromAuthorityService 方法的 fallback</h2>
     * */
    public static JwtToken getTokenFromAuthorityServiceFallback(
            UsernameAndPassword usernameAndPassword
    ) {
        log.error("get token from authority service fallback: [{}]",
                JSON.toJSONString(usernameAndPassword));
        return new JwtToken("imooc-qinyi-fallback");
    }

    /**
     * <h2>ignoreException 方法的 fallback</h2>
     * */
    public static JwtToken ignoreExceptionFallback(Integer code) {
        log.error("ignore exception input code: [{}] has trigger exception", code);
        return new JwtToken("imooc-qinyi-fallback");
    }
}
