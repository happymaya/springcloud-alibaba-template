package cn.happymaya.sat.service.communication.hystrix;

import cn.happymaya.sat.service.communication.AuthorityFeignClient;
import cn.happymaya.sat.vo.JwtToken;
import cn.happymaya.sat.vo.UsernameAndPassword;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>AuthorityFeignClient 后备 fallback</h1>
 * */
@Slf4j
@Component
public class AuthorityFeignClientFallback implements AuthorityFeignClient {

    @Override
    public JwtToken getTokenByFeign(UsernameAndPassword usernameAndPassword) {

        log.info("authority feign client get token by feign request error " +
                "(Hystrix Fallback): [{}]", JSON.toJSONString(usernameAndPassword));
        return new JwtToken("happymaya");
    }
}
