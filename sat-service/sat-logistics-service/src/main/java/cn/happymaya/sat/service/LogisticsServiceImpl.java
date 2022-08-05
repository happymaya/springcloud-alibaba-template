package cn.happymaya.sat.service;

import cn.happymaya.sat.dao.SatLogisticsDao;
import cn.happymaya.sat.entity.SatLogistics;
import cn.happymaya.sat.order.LogisticsMessage;
import cn.happymaya.sat.sink.LogisticsSink;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * <h1>物流服务实现</h1>
 *
 * @author superhsc
 */
@Slf4j
@EnableBinding(LogisticsSink.class)
public class LogisticsServiceImpl {

    private final SatLogisticsDao logisticsDao;

    public LogisticsServiceImpl(SatLogisticsDao logisticsDao) {
        this.logisticsDao = logisticsDao;
    }

    /**
     * <h2>订阅监听订单微服务发送的物流消息</h2>
     */
    @StreamListener("logisticsInput")
    public void consumeLogisticsMessage(@Payload Object payload) {

        log.info("receive and consume logistics message: [{}]", payload.toString());
        LogisticsMessage logisticsMessage = JSON.parseObject(payload.toString(), LogisticsMessage.class);
        SatLogistics ecommerceLogistics = logisticsDao.save(new SatLogistics(logisticsMessage.getUserId(), logisticsMessage.getOrderId(), logisticsMessage.getAddressId(), logisticsMessage.getExtraInfo()));
        log.info("consume logistics message success: [{}]", ecommerceLogistics.getId());
    }
}
