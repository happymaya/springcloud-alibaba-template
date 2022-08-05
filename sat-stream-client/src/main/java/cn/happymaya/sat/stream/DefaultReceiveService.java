package cn.happymaya.sat.stream;

import cn.happymaya.sat.vo.HappymayaMessage;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * <h1>使用默认的信道实现消息的接收</h1>
 *
 * @author superhsc
 */
@Slf4j
@EnableBinding(Sink.class)
public class DefaultReceiveService {

    /**
     * <h2>使用默认的输入信道接收消息</h2>
     */
    @StreamListener(Sink.INPUT)
    public void receiveMessage(Object payload) {

        log.info("in DefaultReceiveService consume message start");
        HappymayaMessage happymayaMessage = JSON.parseObject(payload.toString(), HappymayaMessage.class);
        // 消费消息
        log.info("in DefaultReceiveService consume message success: [{}]", JSON.toJSONString(happymayaMessage));
    }
}
