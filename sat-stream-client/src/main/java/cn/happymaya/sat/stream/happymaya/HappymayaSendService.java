package cn.happymaya.sat.stream.happymaya;

import cn.happymaya.sat.vo.HappymayaMessage;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * <h1>使用自定义的通信信道 QinyiSource 实现消息的发送</h1>
 * */
@Slf4j
@EnableBinding(HappymayaSource.class)
public class HappymayaSendService {

    private final HappymayaSource happymayaSource;

    public HappymayaSendService(HappymayaSource happymayaSource) {
        this.happymayaSource = happymayaSource;
    }

    /**
     * <h2>使用自定义的输出信道发送消息</h2>
     * */
    public void sendMessage(HappymayaMessage message) {

        String _message = JSON.toJSONString(message);
        log.info("in HappymayaSendService send message: [{}]", _message);
        happymayaSource.happymayaOutput().send(
                MessageBuilder.withPayload(_message).build()
        );
    }
}
