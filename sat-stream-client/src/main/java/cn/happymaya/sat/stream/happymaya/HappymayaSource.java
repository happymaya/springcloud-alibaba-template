package cn.happymaya.sat.stream.happymaya;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <h1>自定义输出信道</h1>
 * */
public interface HappymayaSource {

    String OUTPUT = "happymayaOutput";

    /** 输出信道的名称是 happymayaOutput, 需要使用 Stream 绑定器在 yml 文件中声明 */
    @Output(HappymayaSource.OUTPUT)
    MessageChannel happymayaOutput();
}
