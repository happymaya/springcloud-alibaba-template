# demo-springcloud-alibaba
SpringCloud Alibaba 套件落地实践

## 微服务架构的演进过程
### 单体架构设计图
![单体架构设计图](./illustration/Monomer%20architecture.png)
- 优点：开发、部署、上线简单；
- 缺点：代码耦合严重、牵一发而动全身

### 单体架构的升级改进：垂直应用架构

> 垂直：单一的业务场景，例如：电商、外卖、音乐等等
{: .prompt-tip }

![垂直应用架构设计图](./illustration/vertical.png)

- 优点：服务、部署独立，水平扩展容易；
- 缺点：搭建复杂、服务之间关系错综复杂、维护困难

### 垂直应用架构的升级改进：SOA 架构
![SOA 架构设计图](./illustration/soa.png)


## 业界最流行的软件开发架构：微服务架构
![微服务架构设计图](./illustration/micro-service.png)

## 微服务架构遵循的原则


  - 合理、正确的将单体应用迁移到微服务（收益或优点）
    - 单个的微服务，可选择任意一个擅长的语言去开发、扩展性强
    - 对整个应用而言，代码不耦合，不会出现大量的冲突
    - 微服务可以重用，应用发布时间可控性更强
    - 通过故障隔离，让错误在微服务中降级，不会影响到整个应用（或其他服务）

### 不遵循微服务架构原则带来的问题

1. 微服务之间的依赖错综复杂（功能业务划分的不合理,存在大量交叉点），难以维护；
2. 开发过程【互相纠缠】，开发、上线时间严重影响
![不遵循微服务架构原则带来的巨大问题](./illustration/micro-service-weakness.png)

### 微服务架构需要遵循的原则

最佳实践：
1. 职责独立：每个微服务只做自己功能范围内的事情，微服务之间的依赖链不要【过长】
  ![不遵循微服务架构原则带来的巨大问题](./illustration/micro-service-yuanze.png) 
  > 建议单个微服务的依赖链路不要超过 3

  > 微服务的通信链路如果【太长】，就需要考虑重构，重新拆分微服务

  > 对通用的功能逻辑，如果不经常变更，做成 SDK，而不是一个服务

2. 使用熔断实现快速的故障容错和线程隔离，例如：`Hystrix`、`Sentinel`
3. 通过网关代理微服务请求，网关是微服务架构对外暴露的唯一入口
   ![](./illustration/micro-service-gateway.png)
4. 确保微服务 API 变更后能够向后兼容

正确、合理的使用微服务会带来收益和成本的节省。如果前期不做优良的设计，成效差强人意。因此，在做微服务应用之前先不着急写代码，做实施，先将要做的事情、依赖、中间件以及工具梳理好，这是写出好代码、好的微服务架构设计（有可能）！


## 领域驱动设计（DDD）

DDD 相关概念：
- DDD 是一种软件架构设计方法，它并不定义软件开发过程（DevOps）
 
- DDD 利用面向对象的特性，以业务为核心驱动，而不是传统的数据库（表）驱动开发


领域：
- 领域是对功能需求的划分；大的领域下面还有许多小的子领域（理解领域，对功能需求、业务场景的理解）
 ![](./illustration/ddd-domain.png)

领域建模：
- 分析领域模型（搞清楚领域内的业务场景），推演**实体（需要的数据表）**、**值对象（服务之间用来传递数据的对象）**、**领域服务（编写的具体工程实现）**
- 找出聚合边界（合理的业务拆分、服务拆分，服务于服务之间不存在或存在极少的耦合，便于将来的系统维护，降低服务耦合）
- 为聚合配备存储仓库（数据持久化）
- 实践 DDD，不断推到、重构以及优化（考虑软件设计原则，包含性能等问题）

![](./illustration/ddd-layered.png)
- User Interface
- Application
- Domain
- Infrastructure

传统软件开发习惯总是从设计数据表开始的，这种最大问题在于：前期数据表设计不合理，后期的改动则会非常大；而领域驱动设计最大的优势在于软件设计初期关注的是业务，而不是数据表，数据持久化只是设计后期的一个考虑。

没有最好的，只有最合适的。领域驱动设计只是一个流派，并不是完美无缺的，很多问题在实践中才会真正显露出来。就像微服务，虽然带来高内聚、低耦合，但也带来了系统复杂性，带来了服务管理和监控等问题。

实践领域设计，要灵活应变！！！

## 电商工程业务以及微服务模块拆分

### 电商功能业务解读

Tips: 学习领域知识最好的方式就是站在巨人的肩膀上。

![](./illustration/shop-app-service.png)


### 微服务模块拆分

1. 网关是微服务架构的唯一入口
   ![](./illustration/shop-app-gateway.png)
2. 电商功能微服务-四大功能微服务模块：账户、商品、订单、物流
   ![](./illustration/shop-app-service-sub.png)
   之所以这样划分，`e-commerce-service` 功能微服务，还有 `e-commerce-account`、`e-commerce-goods`、`e-commerce-order`、`e-commerce-logistics` 子服务

合理的微服务划分需要考虑：
1. 按照业务划分，没有很多交集（最好没有）
2. 业务之间的依赖很少（很少需要分场景来看待），对于复杂系统，依赖必不可少，莫要过分纠结，即便前期版本规划设计并不完美，后期可以重构优化 ！！！

## Alibaba Nacos

- 服务、配置服务、名字服务
  ![](./illustration/alibaba-nacos-jiagou.png)
  - 服务：一个或一组软件功能，例如：特定信息的检索或一组操作的执行，目的是不同的客户端为不同的目的重用，如通过跨进程的网络调用。Nacos 支持主流的服务生态：K8s Service、gRPC、Doubbo RPC、SpringCloud RESTful Service(本工程的主要使用集成 nacos)
  - 配置服务：工程在运行过程中，可能需要一些动态的配置信息，例如元数据、数据库地址等等，此时就可以通过 Nacos 存储并管理这些配置信息，Nacos 对标 SpringCloud Config，不过比 Nacos 比 SpringCloud Config 更优秀！
  - 名字服务：服务注册和服务发现，是微服务架构下必不可缺少的功能。服务注册指的是微服务将自身的信息（例如，IP 地址、端口号、服务名称）注册到 Naocs 上进行绑定；而服务发现，则是其他的微服务提供一些信息，通过 Naocs 去寻找已经注册的微服务，因此能够实现微服务之间的互通互联

- Nacos Console：Nacos 为了方便用户使用，给用户提供的 Web UI，在完成 Nacos 的安装并启用之后，就可以通过浏览器打开这个 Console 页面，以可视化的方式查看配置信息、配置数据等等
- 服务注册中心：它是服务，实例以及元数据的数据库；服务注册中心可能会调用服务实例的健康检查 API 来验证它是否能够处理请求；
- 服务元数据：包括**服务端点（endpoints）**、**服务标签**、**服务版本号**、**服务实例权重**、**路由规则**、**安全策略**等描述服务的数据
- 服务提供、消费方：提供可复用和可调用服务的应用方；会发起对某个服务调用的应用方
- 配置：在系统开发过程中，通常会将一些需要变更的参数、变量等从代码中分离出来独立管理，以独立的配置文件的形式存在（其实，动态的配置是比较少的，通常情况下，只会把限流配置、路由配置一些信息存储到 Nacos 中）
### 部署 Alibaba Nacos 单机版本
作品遵循简单的原则

- 下载所需要的版本：https://github.com/alibaba/nacos/releases
- 解压：tar -xzvf nacos-server-2.0.0.tar.gz
- 单机模式启动（默认配置就可以）：./startup.sh -m standalone
- 访问路径：http://127.0.0.1:8848/nacos/index.html
- 默认的账号密码都是 nacos, 使用无痕模式吧

### 给 Nacos 配置自定义的 MySQL 持久化
```bash
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user.0=root
db.password.0=root
```

### 部署 Alibaba Nacos 集群

1. 定义集群部署的 ip 和端口，即 cluster.conf 文件；
2. 集群必须要使用可以共同访问（例如 MySQL、PG 等等）到数据源作为持久化的方式
3. 集群化启动没有额外的参数：./startup.sh

```
# 进入到 /Users/brzha12/brzha_imooc/micro_service_solution/soft/nacos/conf, 新建 cluster.conf 文件, 并填充内容

172.16.3.41:8848
172.16.3.41:8858
172.16.3.41:8868

# 集群必须要使用 MySQL(可以是 PG 或者是其他的数据源)作为持久化的方式, 因为需要能够访问到同一个数据源

# 复制出来三份 Nacos
➜  soft cp -p -r ./nacos nacos-8848
➜  soft cp -p -r ./nacos nacos-8858
➜  soft cp -p -r ./nacos nacos-8868

# 修改端口号
vim nacos-8848/conf/application.properties
vim nacos-8858/conf/application.properties
vim nacos-8868/conf/application.properties

# 启动三个 nacos, 不带任何参数标识集群启动
./nacos-8848/bin/startup.sh
./nacos-8858/bin/startup.sh
./nacos-8868/bin/startup.sh
```

### Alibaba Nacos Client 服务注册与发现

#### Alibaba Nacos Client 的服务注册

![](./illustration/alibaba-nacos-discovery.png)


## SpringBoot Admin 监控服务器

### SpringBoot Actuator

#### Actuator Endpoinuts (端点)

- Endpoints 是 Actuator 的核心部分，用来监视应用程序以及交互；SpringBoot Actuator 内置了很多 Endpoints, 并支持扩展
- SpringBoot Actuator 提供的原生端点有三类：
  - 应用配置类（一般）：自动配置信息、Spring Bean 信息、yml 文件信息、环境信息等等
  - 度量指标类（最常使用的）：主要是运行期间的动态信息，例如堆栈、监控指标、metrics 信息等等
  - 操作控制类（很少使用）：主要是指 shutdown，用户可以发送一个请求将应用的监控功能关闭

由于原生的 Actuator 的端点（HTTP 接口，都是 RESTful 类型），返回的都是 JSON 格式数据，因此需要自己实现对 JSON 数据的解析与观察，非常的耗时和麻烦。因此 SpringBoot Admin 就出现了 ！！！

SpringBoot Admin 基于调用 Actuator 端点并使用 Vue 呈现这些端点的监控数据 ，以可视化的图形、文字效果实时呈现应用的监控状态 ！！！


### 搭建 SpringBoot Admin 监控服务器

1. 添加 SpringBoot Admin Starter 自动配置依赖
   ```xml
    <!-- SpringBoot Admin -->
    <!-- 实现对 SpringBoot Admin Server 的自动配置 -->
    <!--
        包含：
            1. spring-boot-admin-server : Server 端
            2. spring-boot-admin-server-ui : UI 界面
            2. spring-boot-admin-server-cloud : 对 Spring Cloud 的接入
    -->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.2.0</version>
    </dependency>
   ```
2. 添加启动注解：`@EnableAdminServer`

### 应用注册到 SpringBoot Admin Server
 
被监控和管理的应用（微服务），注册到 Admin Server 的两种方式：
- 方式一：被监控和管理的应用程序，使用 SpringBoot Admin Client 库，通过 HTTP 调用注册到 SpringBoot Admin Server 上（只有纯 SpringBoot 应用才会使用，微服务架构下基本上不适用，麻烦且没有必要）
- 方式二：首先，被监控和管理的应用程序，注册到 SpringCloud 集成的注册中心；然后 SpringBoot Admin Server 通过注册中心获取到被监控和管理的应用程序

具体步骤：
1. 添加 SpringBoot Actuator 依赖
   ```xml
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```
2. 暴露 SpringBoot Actuator Endpoints
   ```yaml
    # 暴露端点
    management:
      endpoints:
        web:
          exposure:
            include: '*'  # 需要开放的端点。默认值只打开 health 和 info 两个端点。通过设置 *, 可以开放所有端点
      endpoint:
        health:
          show-details: always
   ```
3. 指定 actuator 端点的访问路径
   ```yaml
    metadata:
      management:
        context-path: ${server.servlet.context-path}/actuator
   ```

### 自定义监控告警

1. 邮件
   1. 引入依赖
   ```xml
           <!-- 实现对 Java Mail 的自动化配置 -->
<!--        <dependency>-->
<!--            <groupId>org.springframework.boot</groupId>-->
<!--            <artifactId>spring-boot-starter-mail</artifactId>-->
<!--        </dependency>-->
   ```
   2. 配置
   ```yaml
      # 被监控的应用状态变更为 DOWN、OFFLINE、UNKNOWN 时, 会自动发出告警: 实例的状态、原因、实例地址等信息
      # 需要在 pom.xml 文件中添加 spring-boot-starter-mail 依赖
      # 配置发送告警的邮箱服务器
      # 但是, 这个要能连接上, 否则会报错
      #  mail:
      #    host: qinyi.imooc.com
      #    username: qinyi@imooc.com
      #    password: QinyiZhang
      #    default-encoding: UTF-8
      # 监控告警通知
      #  boot:
      #    admin:
      #      notify:
      #        mail:
      #          from: ${spring.mail.username}
      #          to: qinyi@imooc.com
      #          cc: qinyi@imooc.com

   ```
2. 自定义通知
   ```java
   /**
    * <h1>自定义告警</h1>
    * */
    @Slf4j
    @Component
    @SuppressWarnings("all")
    public class ImayaMallNotifier extends AbstractEventNotifier {

        protected ImayaMallNotifier(InstanceRepository repository) {
            super(repository);
        }

        /**
        * <h2>实现对事件的通知</h2>
        * */
        @Override
        protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
            return Mono.fromRunnable(() -> {
                if (event instanceof InstanceStatusChangedEvent) {
                  // 在这里可以实现，任何的通知，比如钉钉、短信...等等
                    log.info("Instance Status Change: [{}], [{}], [{}]",
                            instance.getRegistration().getName(), event.getInstance(),
                            ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
                } else {
                    log.info("Instance Info: [{}], [{}], [{}]",
                            instance.getRegistration().getName(), event.getInstance(),
                            event.getType());
                }

            });
        }
    }
   ```
   > AbstractEventNotifier 是一个抽象的 ”通知类“，主要发挥作用的是 doNotify 方法，其实就是 SprinBootAdmin 去扫描你定义的这一类 Bean，然后注册每一个 doNotify，根据你的定义逻辑发送消息。这部分功能其实会用、知道怎么用就行，不用花很多时间在这上面。实际的工作也是，主要是搞清楚的业务思想和架构设计。


   ## 授权、鉴权中心微服务功能设计

   ### JWT

   - JSON Web Token(JWT) 是一个开放标准，定义了一种紧凑的、自包含的方式，用于作为 JSON 对象在各方之间安全地传输信息
   - 使用场景: 用户会话状态保持、用户授权、少量的信息交互（加密数据）

  ### JWT 的结构以及含义
  - JWT 由三个部分组成：Header、Payload、Signature，并且用圆点连接：xxxxx.yyyyy.zzzzz
  - Header：由两部分（Tocken 类型、加密算法名称）组成，并使用 Base64 编码
  ```json
  {
    "alg": "HSA256", // 签名的算法
    "typ": "JWT"  // 令牌的类型
  }
  ```
  - Payload：K-V 形式的数据，即想要传递的数据（授权的话就是 Tocken 信息），也需要 Base64 编码
  - Signature：未来得到签名部分，必须有编码过的 Header、编码过的 payload、一个密钥，签名算法是 Header 中指定的那个，然后对它们签名即可
  ```java
    HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
  ```
  ![](./illustration/jwt-token.png)

  ### 对比基于 Token 与基于服务器的身份认证

  #### 基于服务器的认证

  - 最为传统的做法，客户端存储 Cookie（一般是 Session id）, 服务器存储 Session
  - Session 是每次用户认证通过以后，服务器需要创建一条记录保存用户信息，通常是在内存中，随着认证通过的用户越来越多，服务器在这里的开销就会越来越大
  - 在不同域名之前切换的时候，请求可能会被禁止；也就是跨域问题
  ![](./illustration/jwt-vs-service.png)

  #### 基于 Token 的认证
  - JWT 与 Session 的差异相同点是，它们都是存储用户信息；然而，Session 是在服务器端的，而 JWT 是在客户端的
  - JWT 方式将用户状态分散到了客户端中，明显减轻服务端的内存压力
  - ![](./illustration/jwt-token.png)

  #### 两者优缺点对比：
  - 解析方法：JWT 使用算法直接解析得到用户信息；Session 需要额外的数据映射，实现匹配
  - 管理方法：JWT 只有过期时间的限制；Session 数据保存在服务器，可控性更强；
  - 跨平台：JWT 就是一段字符串，可以任意传播；Session 跨平台需要有统一的解析平台，较为繁琐
  - 时效性：JWT 一旦生成，独立存在，很难做特殊控制；Session 的时效性完全由服务端的逻辑说了算
  - 两者都有优缺点，都是登录、授权的解决方案

## 第二代微服务网关组件 SpringCloud Gateway

- SpringCloud Gateway 是 Spring 官方最新推出的一款基于 SpringFramework 5, Project Reactor 和 SpringBoot 2 之上开发的网关
- 它与第一代网关 Zuul 不同的是：gateway 是异步非阻塞的（netty + webflux 实现）；zuul 是同步阻塞请求的
- Gateway 三大组成部分:
  - Route 路由（ID、目标 URI），构建网关的基本模块，由 ID、URI、一系列的断言和过滤器组成
  - Predicate 断言，可以匹配 HTTP 请求中所有的内容（请求头、参数等等），请求与断言相匹配则通过当前断言
  - Filter 过滤器，包括全局和局部过滤器，可以在请求被路由前后对请求进行更改
  ![](./illustration/gateway.png)

### 工作模型
![](./illustration/gateway-model.png)

### Predicate

- 由 Java 8 引入，位于 java.util.function 包中，是一个 FunctionalInterface（函数式接口）
  ```java
  /**
  * Represents a predicate (boolean-valued function) of one argument.
  *
  * <p>This is a <a href="package-summary.html">functional interface</a>
  * whose functional method is {@link #test(Object)}.
  *
  * @param <T> the type of the input to the predicate
  *
  * @since 1.8
  */
  @FunctionalInterface
  public interface Predicate<T> {

      /**
      * Evaluates this predicate on the given argument.
      *
      * @param t the input argument
      * @return {@code true} if the input argument matches the predicate,
      * otherwise {@code false}
      */
      boolean test(T t);
  }
  ```
- boolean test(T t)，需要输入一个参数，返回 boolean 类型，通常用在 stream 的 filter 中，表示是否满足过滤条件

### 静态路由配置

- 静态路由配置写在配置文件中（yml 或者 properties 文件中），端点是：spring.cloud.gateway
- 缺点非常明显，每次改动都需要网关模型重新部署
  ```yaml
      # 静态路由
      gateway:
        routes:
          - id: path_route # 路由的ID
            uri: 127.0.0.1:8080/user/{id} # 匹配后路由地址
            predicates: # 断言, 路径相匹配的进行路由
             - Path=/user/{id}
  ```

  ### 动态路由配置
  - 路由信息在 Alibaba Nacos 中维护，可以实现动态变更
  - ![](./illustration/gateway-nacos-router.png)

### SpringCloud Gateway Filter

- SpringClout Gateway 基于过滤器的思想实现，同 zuul 类似，有 pre 和 post 两种方式的 filter，分别处理前置逻辑（请求的进来）和后置逻辑（请求的返回）
- 客户端的请求先经过 pre 类型的 filter，然后将请求转发到具体的业务服务，收到业务服务的响应之后，再经过 post 类型的 filter 处理，最后返回响应到客户端（与 SpringMVC Filter 的实现思想和效果是一样）
- Filter 一共有两大类：全局过滤器（不需要额外的声明和配置，作用于所以请求）和局部过滤器（需要额外的声明和配置）
  - RouteToRequestUrlFilter.java
  - PrefixPathGatewayFilterFactory.java
  - StripPrefixGatewayFilterFactory.java

#### SpringCloud Gateway Filter 的执行流程
- 过滤器有优先级之分，Order 越大，优先级越低，越晚被执行
- 全局过滤器，作用于所有的路由，不需要单独配置，通过用来实现统一化处理的业务需求
- 局部过滤器只有配置的请求才会执行，实现并生效的三个步骤：
  - 实现 GatewayFilter，Ordered，实现相关的方法
  - 加入到过滤器工厂，并且将工厂注册到 Spring 容器中
  - 在配置文件中进行配置，如果不配置则不启用此过滤器规则（路由规则）
  ![](./illustration/gatewa-filter.png)

 SpringCloud Gateway 是工程的最前端
 -  网关是微服务工程架构下的唯一入口（客户端）
 -  Gateway 提供了统一的路由方式，基于 Filter 链的方式提供了网关的基本功能
 -  与 Zull 的区别是 Zuul 1.X 是一个基于阻塞 I/O 的 API Gateway
    -  Zuul 基于 Servlet 2.5 使用阻塞架构，不支持任何长连接
    -  Zuul 的设计模式与 Nginx 很像，每次 I/O 操作都是从工作线程中选择一个执行，请求线程被阻塞到工作线程完成
 - SpringCloud  Gateway 建立在 Spring5 、Project Reactor 和 SpringBoot 2 之上，使用非阻塞 API
   - SpringCloud Gateway 支持 WebSocket ， 并且与 Spring 紧密集成拥有更好的开发体验


#### SpringCloud Gateway 路由的三种配置方式
1. 在代码中注入 RouterLocator Bean ，并手工编写配置路由定义
2. 在 application.yml，bootstrap.yml 等配置文件中配置 spring.cloud.gateway
3. 通过配置中心（Nocos）实现动态的路由配置

## SpringCloud Sleuth + Zipkin

### SpringCloud Sleuth 

1. 实现的功能是：会自动为当前应用构建起各通信通道的跟踪机制
   1. 通过 Zuul、Gateway 代理传递的请求
   2. 通诸如 RabbitMQ、Kafka（或者其他任何 SpringCloud Stream 绑定器实现的消息中间件）传递的请求
   3. 通过 RestTemplate 发起请求
   ![](./illustration/sleuth.png)
2. 跟踪实现原理
   1. 为了实现请求跟踪，当请求发送到分布式系统的入口端点时，只需要服务跟踪框架为该请求创建一个唯一跟踪标识，Trace ID
   2. 为了统计各处理单元的时间延迟，当请求到达各个服务组件的时候，或是处理逻辑到达某个状态的时候，也通过一个唯一标识来标记它的开始、具体过程以及结束，Span ID
   ![](./illustration/sleuth-traceid-spanid.png)
3. 集成步骤：
   1. 第一原则：保证微服务存在跨进程通信，否则，意义不大（完全可以通过更简单的方式实现）
   2. 在 pom.xml 中添加依赖配置（注意日志输出的变化）
   3. ps：是否可以通过代码获取到相关的跟踪信息吗 ？


### Zipkin

1. 解决微服务架构中的延迟问题，包括数据的收集、存储、查找和展现
2. 四大核心组件构成：
   - Collector: 收集器组件
   - Storage: 存储组件
   - API: RESTFul API，提供外部访问接口
   - UI：Web UI，提供可视化查询页面
3. 搭建 Zipkin Server 的步骤
   1. SpringCloud Finchley 版本（包含）之后，官方不建议自己搭建 Zipkin-Server，提供了以及打包好的 jar 文件（SpringBoot 工程），直接下载启动即可
   2. 下载地址：curl -ssL https://zipkin.io/quickstart.sh | bash -s
   3. 选择自己需要的版本，我的是 zipkin-server-2.21.7.exec.jar
   4. 选择 exec.jar 结尾的 jar
4. 对跟踪信息的收集
   1. 配置 Zipkin Server（为什么 Zipkin Server 做自定义配置）
      1. 默认情况下，Zipkin Server 将**跟踪信息**存储在**内存中**（JVM），重启会丢失
      2. Zipkin Server 默认使用 HTTP 方式上报跟踪数据，性能较差
   2. Zipkin Server 配置 MySQL 跟踪数据持久化（还支持 ES）
      1. MySQL 中添加数据表： https://github.com/openzipkin/zipkin/edit/master/zipkin-storage/mysql-v1/src/main/resources/mysql.sql
      2. Zipkin Server 启动指定 MySQL 路径 `java -jar zipkin-server-2.21.7-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=192.168.31.208 -MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=123456 --MYSQL_DB=imaya_mall_zipkin`

### SpringCloud Sleuth 整合 Zipkin 实现分布式链路追踪、收集
1. pom 文件中添加依赖，Zip Server 使用 MySQL 实现跟踪数据持久化
   ```xml
  <!-- zipkin = spring-cloud-starter-sleuth + spring-cloud-sleuth-zipkin-->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-zipkin</artifactId>
  </dependency>
   ```
2. bootstrap 中增加 Zipkin 的配置（发送数据的方式，不使用 Zipkin 默认方式，而是使用卡夫卡）
   ```bash
    # 简单的看看, 默认端口号是 9411
    java -jar zipkin-server-2.19.3-exec.jar
    nohup java -jar zipkin-server-2.19.3-exec.jar &

    # 访问地址
    http://127.0.0.1:9411/

    # 修改端口, 因为这就是一个 SpringBoot 应用
    java -jar zipkin-server-2.19.3-exec.jar --server.port=8888

    # 跟踪数据保存到 MySQL 中
    wget https://github.com/openzipkin/zipkin/blob/master/zipkin-storage/mysql-v1/src/main/resources/mysql.sql

    # zipkin 服务端可以从消息中间件 (RabbitMQ, Kafka) 获取跟踪数据, 只需要指定好地址就可以, 默认是 HTTP 接口, 性能较差
    # 默认情况下，Zipkin Server 都会将跟踪信息存储在内存中，每次重启 Zipkin Server 都会使得之前收集的跟踪信息丢失，而且当有大量跟踪信息时我们的内存存储也会成为瓶颈
    # 所有正常情况下我们都需要将跟踪信息对接到外部存储组件（比如 MySQL、Elasticsearch）中去
    java -jar zipkin-server-2.21.7-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=root --MYSQL_DB=imooc_zipkin
    
    java -DKAFKA_BOOTSTRAP_SERVERS=127.0.0.1:9092 -jar zipkin-server-2.21.7-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=root --MYSQL_DB=imooc_zipkin
   ```

### 下载、安装 Kafka 配置跟踪数据传输
1. 下载 Kafka，https://kafka.apache.org/quickstart
2. 解压、启动 ZK 和 Kafka Server 即可（使用默认配置）
   ```bash
   # Kafka 依赖于 ZK，先启动 ZK
  zookeeper：bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
  # 启动 Kafka 服务器
  kafka-server：bin/kafka-server-start.sh config/server.properties
  # 创建 Topic
  create topic: bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
  # 查看 Topic 列表
  topic list: bin/kafka-topics.sh --list --zookeeper localhost:2181
  # 启动 Producer
  producer: bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
  # 启动 Consumer
  consumer: bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
  # 查看单个 Topic 信息
  topic info: bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic test
   ```


