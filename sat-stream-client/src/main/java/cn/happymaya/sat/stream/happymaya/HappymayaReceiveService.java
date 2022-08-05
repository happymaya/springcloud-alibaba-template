package cn.happymaya.sat.stream.happymaya;

import cn.happymaya.sat.vo.HappymayaMessage;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * <h1>使用自定义的输入信道实现消息的接收</h1>
 * */
@Slf4j
@EnableBinding(HappymayaSink.class)
public class HappymayaReceiveService {

    /** 使用自定义的输入信道接收消息 */
    @StreamListener(HappymayaSink.INPUT)
    public void receiveMessage(@Payload Object payload) {

        log.info("in HappymayaReceiveService consume message start");
        HappymayaMessage happymayaMessage = JSON.parseObject(payload.toString(), HappymayaMessage.class);
        log.info("in HappymayaReceiveService consume message success: [{}]",
                JSON.toJSONString(happymayaMessage));
    }
}
