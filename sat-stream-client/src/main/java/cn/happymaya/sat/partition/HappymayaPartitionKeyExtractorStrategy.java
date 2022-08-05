package cn.happymaya.sat.partition;

import cn.happymaya.sat.vo.HappymayaMessage;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * <h1>自定义从 Message 中提取 partition key 的策略</h1>
 *
 * @author superhs
 */
@Slf4j
@Component
public class HappymayaPartitionKeyExtractorStrategy implements PartitionKeyExtractorStrategy {

    @Override
    public Object extractKey(Message<?> message) {

        HappymayaMessage happymayaMessage = JSON.parseObject(message.getPayload().toString(), HappymayaMessage.class);
        // 自定义提取 key
        String key = happymayaMessage.getProjectName();
        log.info("SpringCloud Stream happymaya Partition Key: [{}]", key);
        return key;
    }
}
