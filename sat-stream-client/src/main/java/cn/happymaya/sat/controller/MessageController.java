package cn.happymaya.sat.controller;

import cn.happymaya.sat.stream.DefaultSendService;
import cn.happymaya.sat.stream.happymaya.HappymayaSendService;
import cn.happymaya.sat.vo.HappymayaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>构建消息驱动</h1>
 *
 * @author superhs
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    private final DefaultSendService defaultSendService;
    private final HappymayaSendService happymayaSendService;

    public MessageController(DefaultSendService defaultSendService, HappymayaSendService happymayaSendService) {
        this.defaultSendService = defaultSendService;
        this.happymayaSendService = happymayaSendService;
    }

    /**
     * <h2>默认信道</h2>
     */
    @GetMapping("/default")
    public void defaultSend() {
        defaultSendService.sendMessage(HappymayaMessage.defaultMessage());
    }

    /**
     * <h2>自定义信道</h2>
     */
    @GetMapping("/happymayaSend")
    public void happymayaSend() {
        happymayaSendService.sendMessage(HappymayaMessage.defaultMessage());
    }
}
