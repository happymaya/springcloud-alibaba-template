package cn.happymaya.sat.partition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.stereotype.Component;

/**
 * <h1>决定 message 发送到哪个分区的策略</h1>
 *
 * @author superhs
 */
@Slf4j
@Component
public class HappymayaPartitionSelectorStrategy implements PartitionSelectorStrategy {

    /**
     * <h2>选择分区的策略</h2>
     */
    @Override
    public int selectPartition(Object key, int partitionCount) {

        int partition = key.toString().hashCode() % partitionCount;
        log.info("SpringCloud Stream Qinyi Selector info: [{}], [{}], [{}]", key, partitionCount, partition);

        return partition;
    }
}
