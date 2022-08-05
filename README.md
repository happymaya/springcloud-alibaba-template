# Spring Cloud / Alibaba

## 工程架构部署
![微服务功能逻辑](https://images.happymaya.cn/assert/sat/sat-01.png)

| 服务治理、工程架构                                           | 微服务高可用、监控管理                                       | 微服务应用                                                   |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Nacos 完成微服务注册与发现、实现服务治理<br/>Gateway 实现微服务网关<br/>Nacos Config 配置管理实现路由、限流动态配置<br/> | SpringBoot Admin 实现微服务监控<br/>Hystrix 实现微服务容错<br/>Sentinel 在网关层面实现服务、API 接口限流 | Sleuth + Zipkin 实现分布式链路追踪<br/>OpenFeign 实现微服务通信<br/>SpringCloud Stream 构建消息驱动微服务<br/>Alibaba Seata 解决分布式事务 |

:smiley: 开箱即用 ！