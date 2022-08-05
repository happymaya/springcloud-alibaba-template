package cn.happymaya.sat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <h1>Sentinel 集成到 SpringCloud 工程中</h1>
 *
 * @author superhsc
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(SentinelClientApplication.class, args);
    }
}
