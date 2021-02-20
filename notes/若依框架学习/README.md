# 环境部署

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#准备工作)准备工作

```text
JDK >= 1.8 (推荐1.8版本)
Mysql >= 5.7.0 (推荐5.7版本)
Redis >= 3.0
Maven >= 3.0
Node >= 10
nacos >= 1.1.0
sentinel >= 1.6.0
```

1
2
3
4
5
6
7

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#运行系统)运行系统

### [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#后端运行)后端运行

1、前往`Gitee`下载页面([https://gitee.com/y_project/RuoYi-Cloud (opens new window)](https://gitee.com/y_project/RuoYi-Cloud))下载解压到工作目录
2、导入到`Eclipse`，菜单 `File` -> `Import`，然后选择 `Maven` -> `Existing Maven Projects`，点击 `Next`> 按钮，选择工作目录，然后点击 `Finish` 按钮，即可成功导入。
`Eclipse`会自动加载`Maven`依赖包，初次加载会比较慢（根据自身网络情况而定）
3、创建数据库`ry-cloud`并导入数据脚本`ry_2021xxxx.sql`（必须），quartz.sql（可选）
4、创建数据库`ry-config`并导入数据脚本`ry_config_2021xxxx.sql`（必须）
5、配置`nacos`持久化，修改`conf/application.properties`文件，增加支持`mysql`数据源配置

```yml
# db mysql
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://localhost:3306/ry-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=password
```

1
2
3
4
5
6

提示

配置文件`application.properties`是在下载的`nacos-server`包`conf`目录下。
默认配置单机模式，`nacos`也集群/多集群部署模式参考 ([Nacos支持三种部署模式 (opens new window)](https://nacos.io/zh-cn/docs/deployment.html))

6、打开运行基础模块（启动没有先后顺序）

- RuoYiGatewayApplication （网关模块 必须）
- RuoYiAuthApplication （认证模块 必须）
- RuoYiSystemApplication （系统模块 必须）
- RuoYiMonitorApplication （监控中心 可选）
- RuoYiGenApplication （代码生成 可选）
- RuoYiJobApplication （定时任务 可选）
- RuoYFileApplication （文件服务 可选）

7、集成`seata`分布式事务（可选配置，默认不启用）

创建数据库`ry-seata`并导入数据脚本`ry_seata_2021xxxx.sql`

[参考集成nacos配置中心](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#集成nacos配置中心)

提示

运行前需要先启动`nacos`，运行成功可以通过([http://localhost:8080 (opens new window)](http://localhost:8080/))访问，但是不会出现静态页面，可以继续参考下面步骤部署`ruoyi-ui`前端，然后通过前端地址来访问。

### [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#前端运行)前端运行

```bash
# 进入项目目录
cd ruoyi-ui

# 安装依赖
npm install

# 强烈建议不要用直接使用 cnpm 安装，会有各种诡异的 bug，可以通过重新指定 registry 来解决 npm 安装速度慢的问题。
npm install --registry=https://registry.npm.taobao.org

# 本地开发 启动项目
npm run dev
```

1
2
3
4
5
6
7
8
9
10
11

4、打开浏览器，输入：([http://localhost:80 (opens new window)](http://localhost/)) 默认账户/密码 `admin/admin123`）
若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明环境搭建成功

建议使用`Git`克隆，因为克隆的方式可以和`RuoYi`随时保持更新同步。使用`Git`命令克隆

```text
git clone https://gitee.com/y_project/RuoYi-Cloud.git
```

1

提示

因为本项目是前后端完全分离的，所以需要前后端都单独启动好，才能进行访问。
前端安装完node后，最好设置下淘宝的镜像源，不建议使用cnpm（可能会出现奇怪的问题）

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#部署系统)部署系统

提示

因为本项目是前后端分离的，所以需要前后端都部署好，才能进行访问

### [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#后端部署)后端部署

- 打包工程文件

在`ruoyi`项目的`bin`目录下执行`package.bat`打包Web工程，生成war/jar包文件。
然后会在项目下生成`target`文件夹包含`war`或`jar`

提示

不同模块版本会生成在`ruoyi/ruoyi-xxxx`模块下`target`文件夹

- 部署工程文件

1、jar部署方式
使用命令行执行：`java –jar ruoyi-xxxx.jar` 或者执行脚本：`ruoyi/bin/run-xxxx.bat`

2、war部署方式
`ruoyi/pom.xml`中的`packaging`修改为`war`，放入`tomcat`服务器`webapps`

```xml
   <packaging>war</packaging>
```

1

提示

不同模块版本在`ruoyi/ruoyi-xxxx`模块下修改`pom.xml`

- `SpringBoot`去除内嵌`Tomcat`（PS：此步骤不重要，因为不排除也能在容器中部署`war`）

```xml
<!-- 排除内置tomcat -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```

1
2
3
4
5
6
7
8
9
10
11

### [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#前端部署)前端部署

当项目开发完毕，只需要运行一行命令就可以打包你的应用

```bash
# 打包正式环境
npm run build:prod

# 打包预发布环境
npm run build:stage
```

1
2
3
4
5

构建打包成功之后，会在根目录生成 `dist` 文件夹，里面就是构建打包好的文件，通常是 `***.js` 、`***.css`、`index.html` 等静态文件。

通常情况下 `dist` 文件夹的静态文件发布到你的 nginx 或者静态服务器即可，其中的 `index.html` 是后台服务的入口页面。

outputDir 提示

如果需要自定义构建，比如指定 `dist` 目录等，则需要通过 [config (opens new window)](https://gitee.com/y_project/RuoYi-Vue/blob/master/ruoyi-ui/vue.config.js)的 `outputDir` 进行配置。

publicPath 提示

部署时改变页面js 和 css 静态引入路径 ,只需修改 `vue.config.js` 文件资源路径即可。

```js
publicPath: './' //请根据自己路径来配置更改
```

1

```js
export default new Router({
  mode: 'hash', // hash模式
})
```

1
2
3

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#环境变量)环境变量

[参考环境变量](https://doc.ruoyi.vip/ruoyi-vue/document/hjbs.html#环境变量)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#nginx配置)Nginx配置

[参考nginx配置](https://doc.ruoyi.vip/ruoyi-vue/document/hjbs.html#nginx配置)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#tomcat配置)Tomcat配置

[参考tomcat配置](https://doc.ruoyi.vip/ruoyi-vue/document/hjbs.html#tomcat配置)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/document/hjbs.html#常见问题)常见问题

1. 如果使用`Mac`需要修改`nacos`配置`ruoyi-file-dev.yml`文件路径`path`
2. 如果使用`Linux` 提示表不存在，设置大小写敏感配置在`/etc/my.cnf`添加`lower_case_table_names=1`，重启MYSQL服务
3. 如果提示当前权限不足，无法写入文件请检查`ruoyi-file-dev.yml`中的`path`路径或`logback.xml`中的`log.path`路径是否有可读可写操作权限

如遇到无法解决的问题请到[Issues (opens new window)](https://gitee.com/y_project/RuoYi-Cloud/issues)反馈，会不定时进行解答。

# 服务网关

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#基本介绍)基本介绍

- Spring Cloud Gateway

`Spring Cloud Gateway`是基于`Spring`生态系统之上构建的`API`网关，包括：`Spring 5.x`，`Spring Boot 2.x`和`Project Reactor`。`Spring Cloud Gateway`旨在提供一种简单而有效的方法来路由到`API`，并为它们提供跨领域的关注点，例如：`安全性`，`监视/指标`，`限流`等。

- 什么是服务网关

`API Gateway（APIGW / API 网关）`，顾名思义，是系统对外的唯一入口。`API`网关封装了系统内部架构，为每个客户端提供定制的API。 近几年来移动应用与企业间互联需求的兴起。从以前单一的Web应用，扩展到多种使用场景，且每种使用场景对后台服务的要求都不尽相同。 这不仅增加了后台服务的响应量，还增加了后台服务的复杂性。随着微服务架构概念的提出，API网关成为了微服务架构的一个标配组件。

- 为什么要使用网关

微服务的应用可能部署在不同机房，不同地区，不同域名下。此时客户端（浏览器/手机/软件工具）想 要请求对应的服务，都需要知道机器的具体 IP 或者域名 URL，当微服务实例众多时，这是非常难以记忆的，对 于客户端来说也太复杂难以维护。此时就有了网关，客户端相关的请求直接发送到网关，由网关根据请求标识 解析判断出具体的微服务地址，再把请求转发到微服务实例。这其中的记忆功能就全部交由网关来操作了。

- 核心概念

`路由（Route）`：路由是网关最基础的部分，路由信息由 ID、目标 URI、一组断言和一组过滤器组成。如果断言 路由为真，则说明请求的 URI 和配置匹配。
`断言（Predicate）`：Java8 中的断言函数。Spring Cloud Gateway 中的断言函数输入类型是 Spring 5.0 框架中 的 ServerWebExchange。Spring Cloud Gateway 中的断言函数允许开发者去定义匹配来自于 Http Request 中的任 何信息，比如请求头和参数等。
`过滤器（Filter）`：一个标准的 Spring Web Filter。Spring Cloud Gateway 中的 Filter 分为两种类型，分别是 Gateway Filter 和 Global Filter。过滤器将会对请求和响应进行处理。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#使用网关)使用网关

1、添加依赖

```xml
<!-- spring cloud gateway 依赖 -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```



2、配置文件

```yml
server:
  port: 8080

spring: 
  application:
    name: ruoyi-gateway
```



3、网关启动类

```java
public class RuoYiGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依网关启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
```



提示

目前已经存在`ruoyi-gateway`网关服务，用于路由转发、异常处理、限流、降级、接口、鉴权等等。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#路由配置)路由配置

在`spring cloud gateway`中配置`uri`有三种方式，包括

- websocket配置方式

```yml
spring:
  cloud:
    gateway:
      routes:
        - id: ruoyi-api
          uri: ws://localhost:9090/
          predicates:
            - Path=/api/**
```



- http地址配置方式

```yml
spring:
  cloud:
    gateway:
      routes:
        - id: ruoyi-api
          uri: http://localhost:9090/
          predicates:
            - Path=/api/**
```

- 注册中心配置方式

```yml
spring:
  cloud:
    gateway:
      routes:
        - id: ruoyi-api
          uri: lb://ruoyi-api
          predicates:
            - Path=/api/**
```

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#限流配置)限流配置

顾名思义，限流就是限制流量，就像你宽带包有 1 个 G 的流量，用完了就没了。通过限流，我们可以很好地控 制系统的 QPS，从而达到保护系统的目的。
常见的限流算法有：`计数器`算法，`漏桶（Leaky Bucket）`算法，`令牌桶（Token Bucket）`算法。

`Spring Cloud Gateway`官方提供了`RequestRateLimiterGatewayFilterFactory`过滤器工厂，使用`Redis` 和`Lua`脚本实现了令牌桶的方式。

1、添加依赖

```xml
<!-- spring data redis reactive 依赖 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
</dependency>
```



2、限流规则，根据`URI`限流

```yml
spring:
  redis:
    host: localhost
    port: 6379
    password: 
  cloud:
    gateway:
      routes:
        # 系统模块
        - id: ruoyi-system
          uri: lb://ruoyi-system
          predicates:
            - Path=/system/**
          filters:
            - StripPrefix=1
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 1 # 令牌桶每秒填充速率
                redis-rate-limiter.burstCapacity: 2 # 令牌桶总容量
                key-resolver: "#{@pathKeyResolver}" # 使用 SpEL 表达式按名称引用 bean
```

提示

`StripPrefix=1`配置，表示网关转发到业务模块时候会自动截取前缀。

3、编写`URI`限流规则配置类

```java
package com.ruoyi.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流规则配置类
 */
@Configuration
public class KeyResolverConfiguration
{
    @Bean
    public KeyResolver pathKeyResolver()
    {
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
```

4、测试服务验证限流
启动网关服务`RuoYiGatewayApplication.java`和系统服务`RuoYiSystemApplication.java`。
因为网关服务有认证鉴权，可以设置一下白名单`/system/*`在进行测试，多次请求会发现返回`HTTP ERROR 429`，同时在`redis`中会操作两个`key`，表示限流成功。

```text
request_rate_limiter.{xxx}.timestamp
request_rate_limiter.{xxx}.tokens
```



自定义限流规则

参数限流：`key-resolver: "#{@parameterKeyResolver}"`

```java
@Bean
public KeyResolver parameterKeyResolver()
{
	return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
}
```

IP限流：`key-resolver: "#{@ipKeyResolver}"`

```java
@Bean
public KeyResolver ipKeyResolver()
{
	return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
}
```

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#熔断降级)熔断降级

1、添加依赖。

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

2、配置需要熔断降级服务

```yml
spring:
  redis:
    host: localhost
    port: 6379
    password: 
  cloud:
    gateway:
      routes:
        # 系统模块
        - id: ruoyi-system
          uri: lb://ruoyi-system
          predicates:
            - Path=/system/**
          filters:
            - StripPrefix=1
            # 降级配置
            - name: Hystrix
              args:
                name: default
                # 降级接口的地址
                fallbackUri: 'forward:/fallback'
```



提示

上面配置包含了一个`Hystrix`过滤器，该过滤器会应用`Hystrix`熔断与降级，会将请求包装成名为`fallback`的路由指令`RouteHystrixCommand`，`RouteHystrixCommand`继承于`HystrixObservableCommand`，其内包含了`Hystrix`的断路、资源隔离、降级等诸多断路器核心功能，当网关转发的请求出现问题时，网关能对其进行快速失败，执行特定的失败逻辑，保护网关安全。

配置中有一个可选参数`fallbackUri`，当前只支持`forward`模式的`URI`。如果服务被降级，请求会被转发到该`URI`对应的控制器。控制器可以是自定义的`fallback`接口；也可以使自定义的`Handler`，需要实现接口`org.springframework.web.reactive.function.server.HandlerFunction<T extends ServerResponse>`。

3、实现添加熔断降级处理返回信息

```java
package com.ruoyi.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.Optional;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * 熔断降级处理
 * 
 * @author ruoyi
 */
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse>
{
    private static final Logger log = LoggerFactory.getLogger(HystrixFallbackHandler.class);

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest)
    {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,hystrix服务降级处理", originalUri));
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(JSON.toJSONString(R.fail("服务已被降级熔断"))));
    }
}
```



4、路由配置信息加一个控制器方法用于处理重定向的`/fallback`请求

```java
package com.ruoyi.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import com.ruoyi.gateway.handler.HystrixFallbackHandler;
import com.ruoyi.gateway.handler.ValidateCodeHandler;

/**
 * 路由配置信息
 * 
 * @author ruoyi
 */
@Configuration
public class RouterFunctionConfiguration
{
    @Autowired
    private HystrixFallbackHandler hystrixFallbackHandler;

    @Autowired
    private ValidateCodeHandler validateCodeHandler;

    @SuppressWarnings("rawtypes")
    @Bean
    public RouterFunction routerFunction()
    {
        return RouterFunctions
                .route(RequestPredicates.path("/fallback").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        validateCodeHandler);
    }
}
```



4、测试服务熔断降级
启动网关服务`RuoYiGatewayApplication.java`，访问`/system/*`在进行测试，会发现返回`服务已被降级熔断`，表示降级成功。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#黑名单配置)黑名单配置

顾名思义，就是不能访问的地址。实现自定义过滤器`BlackListUrlFilter`，需要配置黑名单地址列表`blacklistUrl`，当然有其他需求也可以实现自定义规则的过滤器。



```yml
spring:
  cloud:
    gateway:
      routes:
        # 系统模块
        - id: ruoyi-system
          uri: lb://ruoyi-system
          predicates:
            - Path=/system/**
          filters:
            - StripPrefix=1
            - name: BlackListUrlFilter
              args:
                blacklistUrl:
                - /user/list
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#白名单配置)白名单配置

顾名思义，就是允许访问的地址。`且无需登录就能访问`。
在`ignore`中设置`whites`，表示允许匿名访问。

```yml
# 不校验白名单
ignore:
  whites:
    - /auth/logout
    - /auth/login
    - /*/v2/api-docs
    - /csrf
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#全局过滤器)全局过滤器

全局过滤器作用于所有的路由，不需要单独配置，我们可以用它来实现很多统一化处理的业务需求，比如权限认证，IP访问限制等等。目前网关统一鉴权`AuthFilter.java`就是采用的全局过滤器。

单独定义只需要实现`GlobalFilter`, `Ordered`这两个接口就可以了。

```java
package com.ruoyi.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * 
 * @author ruoyi
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered
{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        // 实现自定义业务需求
        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#整合sentinel限流)整合Sentinel限流

Sentinel 支持对 Spring Cloud Gateway、Netflix Zuul 等主流的 API Gateway 进行限流。

1、添加依赖

```xml
<!-- SpringCloud Ailibaba Sentinel -->
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
		
<!-- SpringCloud Ailibaba Sentinel Gateway -->
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
</dependency>
```



2、限流规则配置类

```java
package com.ruoyi.gateway.config;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.ruoyi.gateway.handler.SentinelFallbackHandler;

/**
 * 网关限流配置
 * 
 * @author ruoyi
 */
@Configuration
public class GatewayConfig
{
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
    {
        return new SentinelFallbackHandler();
    }

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter()
    {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void doInit()
    {
        // 加载网关限流规则
        initGatewayRules();
    }

    /**
     * 网关限流规则   
     */
    private void initGatewayRules()
    {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("ruoyi-system")
                .setCount(3) // 限流阈值
                .setIntervalSec(60)); // 统计时间窗口，单位是秒，默认是 1 秒
        // 加载网关限流规则
        GatewayRuleManager.loadRules(rules);
    }
}
```



3、测试验证，一分钟内访问三次系统服务出现异常提示表示限流成功。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#sentinel分组限流)Sentinel分组限流

基于`api`分组限流配置

```java
package com.ruoyi.gateway.config;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.ruoyi.gateway.handler.SentinelFallbackHandler;

/**
 * 网关限流配置
 * 
 * @author ruoyi
 */
@Configuration
public class GatewayConfig
{
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
    {
        return new SentinelFallbackHandler();
    }

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter()
    {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void doInit()
    {
        // 加载网关限流规则
        initGatewayRules();
    }

    /**
     * 网关限流规则   
     */
    private void initGatewayRules()
    {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("system-api")
                .setCount(3) // 限流阈值
                .setIntervalSec(60)); // 统计时间窗口，单位是秒，默认是 1 秒
        rules.add(new GatewayFlowRule("schedule-api")
                .setCount(5) // 限流阈值
                .setIntervalSec(60));
        // 加载网关限流规则
        GatewayRuleManager.loadRules(rules);
        // 加载限流分组
        initCustomizedApis();
    }

    /**
     * 限流分组   
     */
    private void initCustomizedApis()
    {
        Set<ApiDefinition> definitions = new HashSet<>();
        // ruoyi-system 组
        ApiDefinition api1 = new ApiDefinition("system-api").setPredicateItems(new HashSet<ApiPredicateItem>()
        {
            private static final long serialVersionUID = 1L;
            {
                // 匹配 /user 以及其子路径的所有请求
                add(new ApiPathPredicateItem().setPattern("/system/user/**")
                        .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
            }
        });
        // job-api 组
        ApiDefinition api2 = new ApiDefinition("schedule-api").setPredicateItems(new HashSet<ApiPredicateItem>()
        {
            private static final long serialVersionUID = 1L;
            {
                // 只匹配 /job/list
                add(new ApiPathPredicateItem().setPattern("/schedule/job/list"));
            }
        });
        definitions.add(api1);
        definitions.add(api2);
        // 加载限流分组
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }
}
```



访问：`http://localhost:8080/system/user/list` （触发限流 ）
访问：`http://localhost:8080/system/role/list` （不会触发限流）
访问：`http://localhost:8080/schedule/job/list` （触发限流）
访问：`http://localhost:8080/schedule/job/xxxx` （不会触发限流）

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/gateway.html#sentinel自定义异常)Sentinel自定义异常

为了展示更加友好的限流提示， Sentinel支持自定义异常处理。

方案一：`yml`配置

```yml
# Spring
spring: 
  cloud:
    sentinel:
      scg:
        fallback:
          mode: response
          response-body: '{"code":403,"mes":"请求超过最大数，请稍后再试"}'
```

1
2
3
4
5
6
7
8

方案二：`GatewayConfig`注入`Bean`

```java
@Bean
@Order(Ordered.HIGHEST_PRECEDENCE)
public SentinelFallbackHandler sentinelGatewayExceptionHandler()
{
	return new SentinelFallbackHandler();
}
```

1
2
3
4
5
6

**SentinelFallbackHandler.java**

```java
package com.ruoyi.gateway.handler;

import java.nio.charset.StandardCharsets;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 自定义限流异常处理
 *
 * @author ruoyi
 */
public class SentinelFallbackHandler implements WebExceptionHandler
{
    private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange)
    {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] datas = "{\"code\":429,\"msg\":\"请求超过最大数，请稍后再试\"}".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex)
    {
        if (exchange.getResponse().isCommitted())
        {
            return Mono.error(ex);
        }
        if (!BlockException.isBlockException(ex))
        {
            return Mono.error(ex);
        }
        return handleBlockedRequest(exchange, ex).flatMap(response -> writeResponse(response, exchange));
    }

    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable)
    {
        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
    }
}
```

# [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/auth.html#认证中心)认证中心

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/auth.html#基本介绍)基本介绍

- 什么是认证中心

身份认证，就是判断一个用户是否为合法用户的处理过程。最常用的简单身份认证方式是系统通过核对用户输入的用户名和口令，看其是否与系统中存储的该用户的用户名和口令一致，来判断用户身份是否正确。

- 为什么要使用认证中心

登录请求后台接口，为了安全认证，所有请求都携带`token`信息进行安全认证，比如使用`vue`、`react`后者`h5`开发的`app`，用于控制可访问系统的资源。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/auth.html#使用认证)使用认证

1、添加依赖

```xml
<!-- ruoyi common security-->
<dependency>
	<groupId>com.ruoyi</groupId>
	<artifactId>ruoyi-common-security</artifactId>
</dependency>
```



2、认证启动类

```java
public static void main(String[] args)
{
	SpringApplication.run(RuoYiAuthApplication.class, args);
	System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
			" .-------.       ____     __        \n" +
			" |  _ _   \\      \\   \\   /  /    \n" +
			" | ( ' )  |       \\  _. /  '       \n" +
			" |(_ o _) /        _( )_ .'         \n" +
			" | (_,_).' __  ___(_ o _)'          \n" +
			" |  |\\ \\  |  ||   |(_,_)'         \n" +
			" |  | \\ `'   /|   `-'  /           \n" +
			" |  |  \\    /  \\      /           \n" +
			" ''-'   `'-'    `-..-'              ");
}
```



提示

目前已经存在`ruoyi-auth`认证授权中心，用于登录认证，系统退出，刷新令牌。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/auth.html#登录认证)登录认证

顾名思义，就是对系统登录用户的进行认证过程。

`TokenController`控制器`login`方法会进行用户验证，如果验证通过会保存登录日志并返回`token`，同时缓存中会存入`login_tokens:xxxxxx`（包含用户、权限信息）。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/auth.html#系统退出)系统退出

顾名思义，就是对系统登用户的退出过程。

`TokenController`控制器`logout`方法会在用户退出时删除缓存信息同时保存用户退出日志。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/auth.html#刷新令牌)刷新令牌

顾名思义，就是对系统操作用户的进行缓存刷新，防止过期。

`TokenController`控制器`refresh`方法会在用户调用时更新令牌有效期。

# 注册中心

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/nacos.html#基本介绍)基本介绍

- 什么是注册中心

注册中心在微服务项目中扮演着非常重要的角色，是微服务架构中的纽带，类似于`通讯录`，它记录了服务和服务地址的映射关系。在分布式架构中，服务会注册到这里，当服务需要调用其它服务时，就到这里找到服务的地址，进行调用。

- 为什么要使用注册中心

注册中心解决了`服务发现`的问题。在没有注册中心时候，服务间调用需要知道被调方的地址或者代理地址。当服务更换部署地址，就不得不修改调用当中指定的地址或者修改代理配置。而有了注册中心之后，每个服务在调用别人的时候只需要知道服务名称就好，继续地址都会通过注册中心同步过来。

- Nacos 注册中心

`Nacos`是阿里巴巴开源的一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

![nacos](https://oscimg.oschina.net/oscnet/up-3b2499cb4616a7073db056095ff530c03c9.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/nacos.html#下载方式)下载方式

- 源码下载

```bash
$ git clone https://github.com/alibaba/nacos.git
$ cd nacos/
$ mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U  
$ ls -al distribution/target/

// change the $version to your actual path
$ cd distribution/target/nacos-server-$version/nacos/bin
```

1
2
3
4
5
6
7

- 安装包下载

可以从`https://github.com/alibaba/nacos/releases`下载`nacos-server-$version.zip`包。

Windows下载解压后（.zip），直接点击`bin/startup.cmd`就可以了。

![nacos](https://oscimg.oschina.net/oscnet/up-f0bee7ddd852b3c78f6f175469defe0a46b.png)

提示

如果觉得官网下载慢，可以使用我分享的网盘地址: https://pan.baidu.com/s/1E9J52g6uW_VFWY34fHL6zA 提取码: vneh

- 打开控制台

`Nacos`提供了一个可视化的操作平台，安装好之后，在浏览器中输入([http://localhost:8848/nacos (opens new window)](http://localhost:8848/nacos))就可以访问了，默认的用户名和密码都是`nacos`（我使用的是1.4.1版本）

![nacos](https://oscimg.oschina.net/oscnet/up-9a6ee9156ed87e5f8856892938f45bd4ace.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/nacos.html#如何使用)如何使用

1、添加依赖

```xml
<!-- springcloud ailibaba nacos discovery -->
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

1
2
3
4
5

2、添加Nacos配置

```yml
# Spring
spring: 
  application:
    # 应用名称
    name: ruoyi-xxxx 
  cloud:
    nacos:
      discovery:
        # 服务注册地址
        server-addr: 127.0.0.1:8848
```



3、在`Application`启动类加入服务发现注解`@EnableDiscoveryClient`，如果使用注解`@SpringCloudApplication`则不需额外添加，因为里面已经包含`@EnableDiscoveryClient`注解了。

```java
@SpringCloudApplication
public class RuoYiXxxxApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Xxxx启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
```



4、启动服务，查看`Nacos`控制台的服务列表

![nacos](https://oscimg.oschina.net/oscnet/up-4ed73db8d4582d4f0fc6bcc1ba38f6e6054.png)

# 配置中心

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/config.html#基本介绍)基本介绍

- 什么是配置中心

在微服务架构中，当系统从一个单体应用，被拆分成分布式系统上一个个服务节点后，配置文件也必须跟着迁移（分割），这样配置就分散了，不仅如此，分散中还包含着冗余，如下图：

![config](https://oscimg.oschina.net/oscnet/up-e61a40fefffca8dd0ab215273af767f2961.png)

总得来说，配置中心就是一种统一管理各种应用配置的基础服务组件。

- 为什么要使用配置中心

配置中心将配置从各应用中剥离出来，对配置进行统一管理，应用自身不需要自己去管理配置。

- Nacos 配置中心

`Nacos`是阿里巴巴开源的一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

![config](https://oscimg.oschina.net/oscnet/up-36349d06b1ac13ea40abd2f1666b73218aa.png)

配置中心的服务流程如下：

1、用户在配置中心更新配置信息。
2、服务A和服务B及时得到配置更新通知，从配置中心获取配置。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/config.html#下载方式)下载方式

[参考下载方式](https://doc.ruoyi.vip/ruoyi-cloud/cloud/nacos.html#下载方式)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/config.html#如何使用)如何使用

1、添加依赖

```xml
<!-- springcloud ailibaba nacos config -->
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```



2、添加Nacos配置

```yml
# Spring
spring: 
  application:
    # 应用名称
    name: ruoyi-xxxx 
  cloud:
      config:
        # 配置中心地址
        server-addr: 127.0.0.1:8848
        # 配置文件格式
        file-extension: yml
        # 共享配置
        shared-dataids: application-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
```



配置文件加载的优先级（由高到低）
`bootstrap.properties ->bootstrap.yml -> application.properties -> application.yml`

3、给配置中心默认添加一个数据集 （Data Id）

![config](https://oscimg.oschina.net/oscnet/up-43e02457f6a2b5ebc14c0fb33e4984e0612.png)

```yml
# 测试属性
ruoyi:
  # 名称
  name: RuoYi
  # 版本
  version: 1.0.0
```



在Nacos Spring Cloud 中，数据集(Data Id) 的配置完整格式如下：
`${spring.cloud.nacos.config.prefix}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}`通俗一点就是前缀-环境-扩展名

4、在`Controller`类中通过`@Value`注解获取配置值。

```java
@Value("${ruoyi.name}")
private String name;

@Value("${ruoyi.version}")
private String version;
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/config.html#动态刷新)动态刷新

通常会在`Controller`里边用`@Value`取出使用，但是你要是想改变他，就要重新改代码，打包，部署，十分麻烦，我们需要让配置文件的值变得动起来，`Nacos`也采用了`Spring Cloud`原生注解`@RefreshScope`实现配置自动更新。

```java
@RefreshScope //动态刷新配置
public class XxxxController 
{
    ....
    @Value("${ruoyi.name}")
    private String name;

    @Value("${ruoyi.version}")
    private String version;
}
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/config.html#mysql支持)mysql支持

在单机模式时`nacos`使用嵌入式数据库实现数据的存储，不方便观察数据存储的基本情况。我们可以配置`mysql`数据库，可视化的查看数据的存储。

1、安装数据库，版本要求：5.6.5+
2、使用`sql/ry_config_xxxx`文件初始化`ry-config`数据库
3、修改`conf/application.properties`文件增加`mysql`支持

```yml
# db mysql
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://localhost:3306/ry-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=password
```



这个`application.properties`指`nacos`的解压目录`nacos/conf`目录下的文件

# 服务调用

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#基本介绍)基本介绍

- Feign

Feign 是`Spring Cloud Netflix`组件中的一量级`Restful`的 HTTP 服务客户端，实现了负载均衡和 Rest 调用的开源框架，封装了`Ribbon`和`RestTemplate`, 实现了`WebService`的面向接口编程，进一步降低了项目的耦合度。

- 什么是服务调用

顾名思义，就是服务之间的接口互相调用，在微服务架构中很多功能都需要调用多个服务才能完成某一项功能。

- 为什么要使用Feign

Feign 旨在使编写 JAVA HTTP 客户端变得更加简单，Feign 简化了`RestTemplate`代码，实现了`Ribbon`负载均衡，使代码变得更加简洁，也少了客户端调用的代码，使用 Feign 实现负载均衡是首选方案，只需要你创建一个接口，然后在上面添加注解即可。
Feign 是声明式服务调用组件，其核心就是：像调用本地方法一样调用远程方法，无感知远程 HTTP 请求。让开发者调用远程接口就跟调用本地方法一样的体验，开发者完全无感知这是远程方法，无需关注与远程的交互细节，更无需关注分布式环境开发。

- Feign vs OpenFeign

Feign 内置了`Ribbon`，用来做客户端负载均衡调用服务注册中心的服务。
Feign 支持的注解和用法参考官方文档：`https://github.com/OpenFeign/feign`官方文档，使用 Feign 的注解定义接口，然后调用这个接口，就可以调用服务注册中心的服务。

`Feign`本身并不支持`Spring MVC`的注解，它有一套自己的注解，为了更方便的使用`Spring Cloud`孵化了`OpenFeign`。并且支持了`Spring MVC`的注解，如`@RequestMapping`，`@PathVariable`等等。
`OpenFeign`的`@FeignClient`可以解析`Spring MVC`的`@RequestMapping`注解下的接口，并通过动态代理方式产生实现类，实现类中做负载均衡调用服务。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#如何使用)如何使用

1、添加依赖

```xml
<!-- spring cloud openfeign -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```



2、新建 RemoteUserService

```java
package com.ruoyi.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.factory.RemoteUserFallbackFactory;
import com.ruoyi.system.api.model.LoginUser;

/**
 * 用户服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username);
}
```



3、消费者新增info方法

```java
/**
 * 获取当前用户信息
 */
@GetMapping("/user/info/{username}")
public R<LoginUser> info(@PathVariable("username") String username)
{
    ....
}
```



4、启动类添加`@EnableRyFeignClients`注解，默认的`@EnableRyFeignClients`扫描范围`com.ruoyi`。

提示

目前已经存在`ruoyi-api-system`系统接口模块，用于服务调用。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#负载均衡)负载均衡

`Feign`默认集成了`Ribbon`，`Nacos`也很好的兼容了`Feign`，默认实现了负载均衡的效果。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#请求传参)请求传参

`Get`方式传参，使用`@PathVariable`、`@RequestParam`注解接收请求参数

```java
@GetMapping(value = "/user/info/{username}")
public R<LoginUser> getUserInfo(@PathVariable("username") String username);
```



`Post`方式传参，使用`@RequestBody`注解接收请求参数。

```java
@PostMapping("/operlog")
public R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog);
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#性能优化)性能优化

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#gzip压缩)Gzip压缩

`gzip`是一种数据格式，采用`deflate`算法压缩数据。`gzip`大约可以帮我们减少`70%`以上的文件大小。

全局配置

```yml
server:
  compression:
    # 是否开启压缩
    enabled: true
    # 配置支持压缩的 MIME TYPE
    mime-types: text/html,text/xml,text/plain,application/xml,application/json
```



局部配置

```yml
feign:
  compression:
    request:
      # 开启请求压缩
      enabled: true
      # 配置压缩支持的 MIME TYPE
      mime-types: text/xml,application/xml,application/json 
      # 配置压缩数据大小的下限
      min-request-size: 2048   
    response:
      # 开启响应压缩
      enabled: true  
```



提示

开启压缩可以有效节约网络资源，但是会增加CPU压力，建议把最小压缩的文档大小适度调大一点。

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#http连接池)Http连接池

两台服务器建立`HTTP`连接的过程涉及到多个数据包的交换，很消耗时间。采用`HTTP`连接池可以节约大量的时间提示吞吐量。

`Feign`的`HTTP`客户端支持3种框架：`HttpURLConnection`、`HttpClient`、`OkHttp`。

默认是采用`java.net.HttpURLConnection`，每次请求都会建立、关闭连接，为了性能考虑，可以引入`httpclient`、`okhttp`作为底层的通信框架。

例如将`Feign`的`HTTP`客户端工具修改为`HttpClient`。

1、添加依赖

```xml
<!-- feign httpclient -->
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-httpclient</artifactId>
</dependency>
```



2、全局配置

```yml
feign:
  httpclient:
    # 开启httpclient
    enabled: true
```



3、测试验证

```java
// RemoteUserService FeignClient
@GetMapping("/user/selectByUser")
public R<SysUser> selectByUser(SysUser user);


// 消费端
@Autowired
private RemoteUserService remoteUserService;

@GetMapping("selectByUser")
public R<?> selectByUser(SysUser user)
{
	return R.ok(remoteUserService.selectByUser(user));
}

// 提供端
@GetMapping("/selectByUser")
public R<SysUser> getUser(@RequestBody SysUser user)
{
	return R.ok(userService.selectUserById(user.getUserId()));
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#状态查看)状态查看

浏览器发起的请求可以通过`F12`查看请求和响应信息。对于微服务中的每个接口我们可以使用日志配置方式进行查看。

配置文件`logback.xml`设置`com.ruoyi`日志级别为`debug`

全局配置

```java
@Bean
public Logger.Level getLog()
{
	return Logger.Level.FULL;
}
```



局部配置

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;

/**
 * Feign 客户端配置
 *
 * @author ruoyi
 */
@Configuration
public class FeignConfiguration
{
    @Bean
    Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }
}

// ====== 在客户端接口指定此配置 ======

/**
 * 用户服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class, configuration = FeignConfiguration.class)
public interface RemoteUserService
{
} 
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#请求超时)请求超时

`Feign`的负载均衡底层用的就是`Ribbon`，所以请求超时其实就只需要配置`Ribbon`参数。

全局配置

```yml
# 请求处理的超时时间
ribbon:
  ReadTimeout: 10000
  ConnectTimeout: 10000
```



局部配置

```yml
# ruoyi-xxxx为需要配置的服务名称
ruoyi-xxxx:
  ReadTimeout: 10000
  ConnectTimeout: 10000
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/feign.html#请求拦截器)请求拦截器

在微服务应用中，通过`feign`的方式实现`http`的调用，可以通过实现`feign.RequestInterceptor`接口在`feign`执行后进行拦截，对请求头等信息进行修改。

例如项目中利用`feign`拦截器将本服务的`userId`、`userName`、`authentication`传递给下游服务

```java
package com.ruoyi.common.security.feign;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign 请求拦截器
 * 
 * @author ruoyi
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor
{
    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (StringUtils.isNotNull(httpServletRequest))
        {
            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
            // 传递用户信息请求头，防止丢失
            String userId = headers.get(CacheConstants.DETAILS_USER_ID);
            if (StringUtils.isNotEmpty(userId))
            {
                requestTemplate.header(CacheConstants.DETAILS_USER_ID, userId);
            }
            String userName = headers.get(CacheConstants.DETAILS_USERNAME);
            if (StringUtils.isNotEmpty(userName))
            {
                requestTemplate.header(CacheConstants.DETAILS_USERNAME, userName);
            }
            String authentication = headers.get(CacheConstants.AUTHORIZATION_HEADER);
            if (StringUtils.isNotEmpty(authentication))
            {
                requestTemplate.header(CacheConstants.AUTHORIZATION_HEADER, authentication);
            }
        }
    }
}
```

# [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#服务监控)服务监控

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#基本介绍)基本介绍

- 什么是服务监控

监视当前系统应用状态、内存、线程、堆栈等等相关信息，主要目的在服务出现问题或者快要出现问题时能够准确快速地发现以减小影响范围。

- 为什么要使用服务监控

服务监控在微服务改造过程中的重要性不言而喻，没有强大的监控能力，改造成微服务架构后，就无法掌控各个不同服务的情况，在遇到调用失败时，如果不能快速发现系统的问题，对于业务来说就是一场灾难。

- spring boot admin 服务监控

`Spring Boot Admin`是一个针对`spring-boot`的`actuator`接口进行UI美化封装的监控工具。他可以：在列表中浏览所有被监控`spring-boot`项目的基本信息，详细的Health信息、内存信息、JVM信息、垃圾回收信息、各种配置信息（比如数据源、缓存列表和命中率）等，还可以直接修改logger的level。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#如何使用)如何使用

1、添加依赖

```xml
<!-- SpringBoot Admin -->
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-server</artifactId>
	<version>${spring-boot-admin.version}</version>
</dependency>
```



2、监控启动类

```java
@EnableAdminServer
@SpringCloudApplication
public class RuoYiMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiMonitorApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
```



3、测试访问

浏览器访问([http://localhost:9100 (opens new window)](http://localhost:9100/))可以看到以下界面。

![monitor](https://oscimg.oschina.net/oscnet/up-5fa1693c4b4ef2f544105970aa3ed53adef.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#登录认证)登录认证

1、添加依赖

```xml
<!-- spring security -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```



2、配置spring security

```java
package com.ruoyi.modules.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 监控权限配置
 * 
 * @author ruoyi
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter
{
    private final String adminContextPath;

    public WebSecurityConfigurer(AdminServerProperties adminServerProperties)
    {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http
            .headers().frameOptions().disable()
            .and().authorizeRequests()
            .antMatchers(adminContextPath + "/assets/**"
                , adminContextPath + "/login"
                , adminContextPath + "/actuator/**"
                , adminContextPath + "/instances/**"
            ).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage(adminContextPath + "/login")
            .successHandler(successHandler).and()
            .logout().logoutUrl(adminContextPath + "/logout")
            .and()
            .httpBasic().and()
            .csrf()
            .disable();
    }
}
```



3、在`ruoyi-monitor-dev.yml`配置用户，默认账户`ruoyi/123456`

```yml
# spring
spring: 
  security:
    user:
      name: ruoyi
      password: 123456
  boot:
    admin:
      ui:
        title: 若依服务状态监控
```

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#实时日志)实时日志

```
Spring Boot Admin`提供了基于`Web`页面的方式实时查看服务输出的本地日志，前提是服务中配置了`logging.file
```

以`ruoyi-gateway`模块为例，`bootstrap.yml`配置`logging.file`配置

```yml
logging:
  file: logs/${spring.application.name}/info.log
```

进入日志-日志文件`查看实时日志`，效果如下 ![config](https://oscimg.oschina.net/oscnet/up-2f1985d1311e938d7043b48426145c5584d.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#修改日志级别)修改日志级别

`Spring Boot Admin`支持动态修改日志级别。

进入日志-日志配置`修改日志级别`，效果如下

![config](https://oscimg.oschina.net/oscnet/up-f45b254e13a032b82b6e110bf9c658541f8.png)

# 系统接口

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/swagger.html#基本介绍)基本介绍

[参考系统接口实现](https://doc.ruoyi.vip/ruoyi/document/htsc.html#系统接口)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/swagger.html#如何使用)如何使用

1、业务模块添加依赖

```xml
<!-- ruoyi common swagger -->
<dependency>
	<groupId>com.ruoyi</groupId>
	<artifactId>ruoyi-common-swagger</artifactId>
</dependency>
```



2、在`ruoyi-xxxx-dev.yml`添加swagger配置

```yml
# swagger配置
swagger:
  title: 系统模块接口文档
  license: Powered By ruoyi
  licenseUrl: https://ruoyi.vip
```

3、在`Application`启动类加入系统接口注解`@EnableCustomSwagger2`

```java
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class RuoYiSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
```



4、测试验证

访问`http://{ip}:{port}/swagger-ui.html`地址，出现如下图表示成功。

![swagger](https://oscimg.oschina.net/oscnet/up-24a0d329ed368fa86c6da597ed158898e4f.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/swagger.html#文档聚合)文档聚合

访问`swagger-ui.html`的时候会发现右上角的`Select a spec`这个下拉选项

![swagger](https://oscimg.oschina.net/oscnet/up-9d740e616ac8523c9d8285ce553689ca20b.png)

当启动一个`springboot`项目的时候会发现这个下拉选项毫无用处，不过它的强大是在于这个下拉可以用来切换不同项目的`swagger`接口地址，这就实现了使用一个网关的`url`访问所有的项目接口。

1、网关模块添加依赖

```xml
<!-- Swagger -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>${swagger.fox.version}</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>${swagger.fox.version}</version>
</dependency>
```



2、网关服务创建一个类`SwaggerProvider`实现`SwaggerResourcesProvider`

```java
package com.ruoyi.gateway.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 聚合系统接口
 * 
 * @author ruoyi
 */
@Component
public class SwaggerProvider implements SwaggerResourcesProvider
{
    /**
     * Swagger2默认的url后缀
     */
    public static final String SWAGGER2URL = "/v2/api-docs";
    /**
     * 网关路由
     */
    @Autowired
    private RouteLocator routeLocator;

    @Autowired
    private GatewayProperties gatewayProperties;

    /**
     * 聚合其他服务接口
     * 
     * @return
     */
    @Override
    public List<SwaggerResource> get()
    {
        List<SwaggerResource> resourceList = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        // 获取网关中配置的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        gatewayProperties.getRoutes().stream()
                .filter(routeDefinition -> routes
                        .contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                        .filter(predicateDefinition -> !"ruoyi-auth".equalsIgnoreCase(routeDefinition.getId()))
                        .forEach(predicateDefinition -> resourceList
                                .add(swaggerResource(routeDefinition.getId(), predicateDefinition.getArgs()
                                        .get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", SWAGGER2URL)))));
        return resourceList;
    }

    private SwaggerResource swaggerResource(String name, String location)
    {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
```



3、创建一个聚合接口类

```java
package com.ruoyi.gateway.handler;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@RestController
@RequestMapping("/swagger-resources")
public class SwaggerHandler
{
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    private final SwaggerResourcesProvider swaggerResources;

    @Autowired
    public SwaggerHandler(SwaggerResourcesProvider swaggerResources)
    {
        this.swaggerResources = swaggerResources;
    }

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration()
    {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()),
                HttpStatus.OK));
    }

    @GetMapping("/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration()
    {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("")
    public Mono<ResponseEntity> swaggerResources()
    {
        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
    }
}
```



4、测试验证

打开浏览器，输入：([http://localhost:8080/swagger-ui.html (opens new window)](http://localhost:8080/swagger-ui.html))

![swagger](https://oscimg.oschina.net/oscnet/up-7455b9e8a7850faebf31f2a869412e2132d.png)

选择切换不同服务的`swagger`接口

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/swagger.html#全局授权)全局授权

在测试系统接口中可能存在一些接口用到用户信息或权限验证，此时需要添加全局的`token`参数。如图

![swagger](https://oscimg.oschina.net/oscnet/up-a474910efef3e0739b42f3d5cc329f8ef66.png)

`token`是在登录成功后返回的，可以在浏览器通过F12查看`Network`中的请求地址，对应参数`Authorization`。复制截图内容到`swagger`全局`Authorization`属性`value`参数中，点击`Authorize`，以后每次访问接口会携带此`token`信息。

![swagger](https://oscimg.oschina.net/oscnet/up-4f771cfc906fa9dcc173f20fae80c7f5191.png)

# [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/skywalking.html#链路追踪)链路追踪

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/skywalking.html#基本介绍)基本介绍

- 什么是链路追踪

随着微服务分布式系统变得日趋复杂，越来越多的组件开始走向分布式化，如分布式服务、分布式数据库、分布式缓存等，使得后台服务构成了一种复杂的分布式网络。在服务能力提升的同时，复杂的网络结构也使问题定位更加困难。在一个请求在经过诸多服务过程中，出现了某一个调用失败的情况，查询具体的异常由哪一个服务引起的就变得十分抓狂，问题定位和处理效率是也会非常低。

分布式链路追踪就是将一次分布式请求还原成调用链路，将一次分布式请求的调用情况集中展示，比如各个服务节点上的耗时、请求具体到达哪台机器上、每个服务节点的请求状态等等。

- 为什么要使用链路追踪

链路追踪为分布式应用的开发者提供了完整的调用链路还原、调用请求量统计、链路拓扑、应用依赖分析等工具，可以帮助开发者快速分析和诊断分布式应用架构下的性能瓶颈，提高微服务时代下的开发诊断效率。

- skywalking 链路追踪

`SkyWalking`是一个可观测性分析平台（Observability Analysis Platform 简称OAP）和应用性能管理系统（Application Performance Management 简称 APM）。

提供分布式链路追踪，服务网格（Service Mesh）遥测分析，度量（Metric）聚合和可视化一体化解决方案。

SkyWalking 特点

- 多语言自动探针，java，.Net Code ,Node.Js
- 多监控手段，语言探针和Service Mesh
- 轻量高效，不需要额外搭建大数据平台
- 模块化架构，UI ，存储《集群管理多种机制可选
- 支持警告
- 优秀的可视化效果。

下面是`SkyWalking`的架构图： ![skywalking](https://oscimg.oschina.net/oscnet/up-551a741ebdd3614f06408cc23dc302ef576.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/skywalking.html#下载方式)下载方式

- Windows平台安装包下载

可以从`http://skywalking.apache.org/downloads`下载`apache-skywalking-apm-$version.tar.gz`包。

Windows下载解压后（.tar.gz），直接点击`bin/startup.bat`就可以了，这个时候实际上是启动了两个项目，一个收集器，一个web页面。

![skywalking](https://oscimg.oschina.net/oscnet/up-9a74069dbd42e28e47a94840d8db7475652.png)

提示

如果觉得官网下载慢，可以使用我分享的网盘地址: https://pan.baidu.com/s/1E9J52g6uW_VFWY34fHL6zA 提取码: vneh

- 打开控制台

`skywalking`提供了一个可视化的监控平台，安装好之后，在浏览器中输入([http://localhost:8080 (opens new window)](http://localhost:8080/))就可以访问了。（我使用的是8.3.0版本）

![skywalking](https://oscimg.oschina.net/oscnet/up-8c56e9280dbd69e3db1508fa27af7fa704a.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/skywalking.html#如何使用)如何使用

- 配置vm参数

`idea`配置`vm`参数图：

![idea skywalking](https://oscimg.oschina.net/oscnet/up-28824574c3bb227dfc487aa82fa60852656.png)

`eclipse`配置`vm`参数图：

![eclipse skywalking](https://oscimg.oschina.net/oscnet/up-ea035ca46c210197746804c59fad4ef403b.png)

```text
-javaagent:D:\apache-skywalking-apm-bin\agent\skywalking-agent.jar
-Dskywalking.agent.service_name=ruoyi-gateway
-Dskywalking.collector.backend_service=localhost:11800
```



启动项目，访问接口，再去([http://localhost:8080 (opens new window)](http://localhost:8080/))看面板数据

![skywalking](https://oscimg.oschina.net/oscnet/up-c3d59e9788dc7fbc34a9dd9235ad7856ad4.png)

| 参数         | 描述                           |
| ------------ | ------------------------------ |
| javaagent    | 配置skywalking-agent.jar的地址 |
| service_name | 配置需要监控的服务名           |
| javaagent    | skywalking收集器服务的地址     |

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/skywalking.html#链路跟踪)链路跟踪

当我们访问一个服务，而他会调用另一个服务的时候，点击拓扑图会出现下图的效果，这就是链路跟踪的效果

![skywalking](https://oscimg.oschina.net/oscnet/up-fc658837ebe738ba9d470f1f2b4c0de4231.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/skywalking.html#追踪调用链)追踪调用链

在追踪界面，可以查看整个请求的具体调用链

![skywalking](https://oscimg.oschina.net/oscnet/up-a36efa6463829a3ebbc4cc4efacb6eb57e3.png)

# 熔断和降级

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/sentinel.html#基本介绍)基本介绍

- 什么是熔断和降级

服务的稳定是公司可持续发展的重要基石，随着业务量的快速发展，一些平时正常运行的服务，会出现各种突发状况，而且在分布式系统中，每个服务本身又存在很多不可控的因素，比如线程池处理缓慢，导致请求超时，资源不足，导致请求被拒绝，又甚至直接服务不可用、宕机、数据库挂了、缓存挂了、消息系统挂了...对于一些非核心服务，如果出现大量的异常，可以通过技术手段，对服务进行降级并提供有损服务，保证服务的柔性可用，避免引起雪崩效应。

**服务熔断**一般是指软件系统中，由于某些原因使得服务出现了过载现象，为防止造成整个系统故障，从而采用的一种保护措施。

**服务降级**是在服务器压力陡增的情况下，利用有限资源，根据当前业务情况，关闭某些服务接口或者页面，以此释放服务器资源以保证核心任务的正常运行。

- 为什么要使用熔断和降级

在一个分布式系统里，一个服务依赖多个服务，可能存在某个服务调用失败，比如超时、异常等，需要保证在一个依赖出问题的情况下，不会导致整体服务失败。

- sentinel 熔断和降级

随着微服务的流行，服务和服务之间的稳定性变得越来越重要。`Sentinel`是面向分布式服务架构的流量控制组件，主要以流量为切入点，从流量控制、熔断降级、系统自适应保护等多个维度来帮助您保障微服务的稳定性。

sentinel具有以下特征：

**丰富的应用场景:** Sentinel承接了阿里巴巴近十年的双十一大促流量的核心场景,例如秒杀(即突发流量控制在系统容量可以承受的范围),消息削峰填谷,集群流量控制,实时熔断下游不可用应用等

**完美的实时监控:** Sentinel同事提供实时的监控功能,您可以在控制台看到接入应用的单台机器秒级数据,甚至500台一下规模的集群的汇总运行情况

**广泛的开源生态:** Sentinel提供开箱即用的与其他框架/库的整合模块,例如与SpringCloud,Dubbo,gRPC的整合,您只需要引入响应的依赖并进行简单的配置即可快速接入Sentinel

**完美的SPI扩展点:** Sentinel提供简单易用的,完美的SPI扩展接口,可以通过实现扩展接口来快速定制逻辑,例如定制规则管理,适配动态数据源等

下面是`sentinel`的架构图： ![sentinel](https://oscimg.oschina.net/oscnet/up-a35c327257487fd453b4f4f1ccd0967af68.png)

- 核心概念

`sentinel`的使用可以分为两个部分

**核心库**不依赖任何框架/库，能够允许在`jdk7`以上的版本运行时环境，同时对Dubbo、SpringCloud等框架也有比较好的支持。

**控制台**主要负责管理推送规则、监控、集群限流分配管理、机器发现等。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/sentinel.html#下载方式)下载方式

> 注意：启动 Sentinel 控制台需要 JDK 版本为 1.8 及以上版本。

- Windows平台安装包下载

可以从`https://github.com/alibaba/Sentinel/releases`下载`sentinel-dashboard-$version.jar`包。

使用如下命令启动控制台：

```text
java -Dserver.port=8718 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -Dcsp.sentinel.api.port=8719 -jar D:\sentinel\sentinel-dashboard-1.8.0.jar
```



其中`-Dserver.port=8718`用于指定`Sentinel`控制台端口为`8718`，`F:\software\sentinel\sentinel-dashboard-1.8.0.jar`为下载的包路径地址。

![sentinel](https://oscimg.oschina.net/oscnet/up-d3e4965511f73cee8ec905740129e7f063a.png)

提示

如果觉得官网下载慢，可以使用我分享的网盘地址: https://pan.baidu.com/s/1E9J52g6uW_VFWY34fHL6zA 提取码: vneh

- 打开控制台

`Sentinel`提供了一个可视化的操作平台，安装好之后，在浏览器中输入([http://localhost:8718 (opens new window)](http://localhost:8718/))就可以访问了，默认的用户名和密码都是`sentinel`（我使用的是1.8.0版本）

![sentinel](https://oscimg.oschina.net/oscnet/up-82f61a6c9e327ede41d7101a27f559a9702.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/sentinel.html#如何使用)如何使用

1、添加依赖

```xml
<!-- springcloud ailibaba sentinel -->
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```



2、添加Sentinel配置

```yml
# Spring
spring: 
  application:
    # 应用名称
    name: ruoyi-xxxx 
  cloud:
    sentinel:
      # 取消控制台懒加载
      eager: true
      transport:
        # 控制台地址
        dashboard: 127.0.0.1:8718
```


12

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/sentinel.html#nacos存储)nacos存储

当`sentinel`重新启动时，`sentinel dashboard`中原来的数据将会全部消失，这样就需要重新定义限流规则，无疑是不可取的。所以需要将`sentinel`中定义的限流规则保存到配置中心里面。

具体的实现方法如下：

1、在nacos中定义自定义限流策略`sentinel-ruoyi-gateway`

```yml
[
    {
        "resource": "ruoyi-auth",
        "count": 500,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
	{
        "resource": "ruoyi-system",
        "count": 1000,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
	{
        "resource": "ruoyi-gen",
        "count": 200,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    },
	{
        "resource": "ruoyi-job",
        "count": 300,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    }
]
```

![nacos](https://oscimg.oschina.net/oscnet/up-ee25e0735b177d55d48fde1c63426c916ff.png)

2、添加依赖

```xml
<!-- sentinel datasource nacos -->
<dependency>
	<groupId>com.alibaba.csp</groupId>
	<artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```



3、添加相关配置，`sentinel`下面的`dataSource`中配置`nacos`

```yml
# Spring
spring: 
  application:
    # 应用名称
    name: ruoyi-xxxx 
  cloud:
    sentinel:
      # 取消控制台懒加载
      eager: true
      transport:
        # 控制台地址
        dashboard: 127.0.0.1:8718
      # nacos配置持久化
      datasource:
        ds1:
          nacos:
            server-addr: 127.0.0.1:8848
            dataId: sentinel-ruoyi-gateway
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow
```



4、启动`sentinel`应用，可以看到我们在`nacos`中配置的限流规则 ![sentinel](https://oscimg.oschina.net/oscnet/up-256fe9234933671875a7448f0c6b26604d6.png)

# 分布式文件

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#基本介绍)基本介绍

- 什么是分布式文件

分布式文件系统是指文件系统管理的物理存储资源不一定直接连接在本地节点上，而是通过计算机网络与节点相连。

- 为什么要使用分布式文件

分布式文件系统是面对互联网的需求而产生，互联网时代对海量数据如何存储？靠简单的增加硬盘的个数已经满足不了我们的要求，因为硬盘传输速度有限但是数据在急剧增长，另外我们还要要做好数据备份、数据安全等。

`ruoyi-file`目前支持三种存储方式，`本地存储`、`MinIO存储`、`FastDfs存储`，可以在`ruoyi-file-dev.yml`配置。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#本地存储)本地存储

1、文件配置

```yml
# 本地文件上传    
file:
    # 访问地址
    domain: http://127.0.0.1:9300
	# 本地存放的路径地址
    path: D:/ruoyi/uploadPath
	# 映射地址
    prefix: /statics
```



2、继承`WebMvcConfigurer`，重写`addResourceHandlers`，在`registry`里面配置访问路径和映射到的服务器本地路径。

```java
package com.ruoyi.file.config;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用映射配置
 * 
 * @author ruoyi
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(localFilePrefix + "/**")
                .addResourceLocations("file:" + localFilePath + File.separator);
    }
}
```



3、启动`ruoyi-file`应用，访问`statics`会被映射到本地项目`D:/ruoyi/uploadPath`下的目录里面。

![statics](https://oscimg.oschina.net/oscnet/up-b1b7ed31eabe28b4ce815ab76569a771d05.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#minio存储)MinIO存储

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#下载方式)下载方式

- Windows平台安装包下载

可以从`https://min.io/download#/windows`下载`minio.exe`可执行的文件。

Windows下载后新建一个目录存放`minio`文件，例如`D:\minioData`，直接在`cmd`下运行`minio.exe server D:\minioData`。

启动成功以后如下图，最后红色字提示修改`access Key`和`Secret Key`

![minio](https://oscimg.oschina.net/oscnet/up-0f06828839a1214544aae11d4c38cfb5b8a.png)

提示

如果觉得官网下载慢，可以使用我分享的网盘地址: https://pan.baidu.com/s/1E9J52g6uW_VFWY34fHL6zA 提取码: vneh

- 打开控制台

```
minio`提供了一个可视化的管理控制平台，安装好之后，在浏览器中输入([http://localhost:9000/ (opens new window)](http://localhost:9000/))就可以访问了，默认的用户名和密码都是`minioadmin
```

![minio](https://oscimg.oschina.net/oscnet/up-0c6c496704c7f30a569a07c520d0bbf40dd.png)

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#如何使用)如何使用

配置文件

```yml
# Minio配置
minio:
  url: http://127.0.0.1:9000
  # 账号
  accessKey: minioadmin
  # 密码
  secretKey: minioadmin
  # MinIO桶名字
  bucketName: ruoyi
```



- 创建桶

在后台管理界面选择`+号`创建你的`Create Bucket`，可以理解为一个文件夹用来存放图片。桶创建成功之后就可以上传图片了。

![minio](https://oscimg.oschina.net/oscnet/up-fc0a58dcb7bc7a2ec03736857febb50e20d.png)

- 上传图片

在后台管理界面选择`+号`上传你的`Upload file`，上传你自己的图片。在文件列表的右边就可以看到图片了。

![minio](https://oscimg.oschina.net/oscnet/up-106b75fff841430904e5125272b2d7f1bf6.png)

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#访问策略)访问策略

设置`* ReadOnly`则所有用户通过文件路径即可访问，私有桶则不必设置访问策略。

![minio](https://oscimg.oschina.net/oscnet/up-4c2d1660428db87b223240736461ac034fc.png)

启动`ruoyi-file`应用，在浏览器中打开([http://127.0.0.1:9000/ruoyi/ruoyi.png (opens new window)](http://127.0.0.1:9000/ruoyi/ruoyi.png))就可以访问图片了。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#fastdfs存储)FastDfs存储

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#下载方式-2)下载方式

创建目录`mkdir /home/fastdfs`

1)、下载安装libfastcommon

1、下载libfastcommon v1.0.7

```bash
wget https://github.com/happyfish100/libfastcommon/archive/V1.0.7.tar.gz
```



2、解压libfastcommon v1.0.7

```bash
tar -xvf V1.0.7.tar.gz`
cd libfastcommon-1.0.7
```



3、编译、安装

```bash
./make.sh
./make.sh install
```



4、创建软链接

```bash
ln -s /usr/lib64/libfastcommon.so /usr/local/lib/libfastcommon.so
ln -s /usr/lib64/libfastcommon.so /usr/lib/libfastcommon.so
ln -s /usr/lib64/libfdfsclient.so /usr/local/lib/libfdfsclient.so
ln -s /usr/lib64/libfdfsclient.so /usr/lib/libfdfsclient.so
```



2)、下载安装FastDFS

1、下载FastDFS

```bash
wget https://github.com/happyfish100/fastdfs/archive/V5.05.tar.gz
```



2、解压FastDFS

```bash
tar -xvf V5.05.tar.gz
cd fastdfs-5.05
```



3、编译、安装

```bash
./make.sh
./make.sh install
```



3)、配置 Tracker 服务

上述安装成功后，在/etc/目录下会有一个fdfs的目录，进入它。会看到三个.sample后缀的文件，这是作者给我们的示例文件， 我们需要把其中的tracker.conf.sample文件改为tracker.conf配置文件并修改它：

```bash
cp tracker.conf.sample tracker.conf
vi tracker.conf
```



编辑tracker.conf

```bash
# 配置文件是否不生效，false 为生效
disabled=false
# 提供服务的端口
port=22122
# Tracker 数据和日志目录地址
base_path=//home/data/fastdfs
# HTTP 服务端口
http.server_port=80
```



创建tracker基础数据目录，即base_path对应的目录

```bash
mkdir -p /home/data/fastdfs
```



使用ln -s 建立软链接

```bash
ln -s /usr/bin/fdfs_trackerd /usr/local/bin
ln -s /usr/bin/stop.sh /usr/local/bin
ln -s /usr/bin/restart.sh /usr/local/bin
```



启动服务

```bash
service fdfs_trackerd start
service fdfs_trackerd stop
service fdfs_trackerd restart
```



查看监听

```bash
netstat -unltp|grep fdfs
```



如果看到22122端口正常被监听后，这时候说明Tracker服务启动成功啦！

`tracker server`目录及文件结构 `Tracker`服务启动成功后，会在`base_path`下创建`data`、`logs`两个目录。目录结构如下：

```text
${base_path}
  |__data
  |   |__storage_groups.dat：存储分组信息
  |   |__storage_servers.dat：存储服务器列表
  |__logs
  |   |__trackerd.log： tracker server 日志文件 
```



4)、配置 Storage 服务

进入`/etc/fdfs`目录，复制`FastDFS`存储器样例配置文件`storage.conf.sample`，并重命名为`storage.conf`

```bash
# cd /etc/fdfs
# cp storage.conf.sample storage.conf
# vi storage.conf
```



编辑storage.conf

```bash
# 配置文件是否不生效，false 为生效
disabled=false
# 指定此 storage server 所在 组(卷)
group_name=group1
# storage server 服务端口
port=23000
# 心跳间隔时间，单位为秒 (这里是指主动向 tracker server 发送心跳)
heart_beat_interval=30
# Storage 数据和日志目录地址(根目录必须存在，子目录会自动生成)
base_path=/home/data/fastdfs/storage
# 存放文件时 storage server 支持多个路径。这里配置存放文件的基路径数目，通常只配一个目录。
store_path_count=1
# 逐一配置 store_path_count 个路径，索引号基于 0。
# 如果不配置 store_path0，那它就和 base_path 对应的路径一样。
store_path0=/home/data/fastdfs/storage
# FastDFS 存储文件时，采用了两级目录。这里配置存放文件的目录个数。 
# 如果本参数只为 N（如： 256），那么 storage server 在初次运行时，会在 store_path 下自动创建 N * N 个存放文件的子目录。
subdir_count_per_path=256
# tracker_server 的列表 ，会主动连接 tracker_server
# 有多个 tracker server 时，每个 tracker server 写一行 公网访问需要配置公网IP
tracker_server=192.168.1.190:22122
# 允许系统同步的时间段 (默认是全天) 。一般用于避免高峰同步产生一些问题而设定。
sync_start_time=00:00
sync_end_time=23:59
```



创建storaged基础数据目录，即base_path对应的目录

```bash
mkdir -p /home/data/fastdfs/storage
```



使用ln -s 建立软链接

```bash
ln -s /usr/bin/fdfs_storaged /usr/local/bin
```



启动服务

```bash
service fdfs_storaged start
service fdfs_storaged stop
service fdfs_storaged restart
```



查看监听

```bash
netstat -unltp|grep fdfs
```



启动`Storage`前确保`Tracker`是启动的。初次启动成功，会在`/home/data/fastdfs/storage`目录下创建`data`、`logs`两个目录。 如果看到`23000端口`正常被监听后，这时候说明`Storage`服务启动成功啦！

查看`Storage`和`Tracker`是否在通信

```bash
/usr/bin/fdfs_monitor /etc/fdfs/storage.conf
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#配置nginx)配置Nginx

1、下载安装Nginx和fastdfs-nginx-module 安装以下的开发库:

```bash
yum install readline-devel pcre-devel openssl-devel -y
```



2、下载fastdfs-nginx-module-1.20.tar

```bash
tar -xvf fastdfs-nginx-module-1.20.tar
```



3、编辑fastdfs-nginx-module-1.20/src/config文件修改

```bash
vi fastdfs-nginx-module-1.20/src/config
ngx_module_incs="/usr/include/fastdfs /usr/include/fastcommon/"
CORE_INCS="$CORE_INCS /usr/include/fastdfs /usr/include/fastcommon/"
```



4、配置nginx安装，加入fastdfs-nginx-module模块（需要先安装好nginx）

```bash
./configure --add-module=../fastdfs-nginx-module-master/src/
```



5、编译、安装

```bash
make && make install
```



6、查看Nginx的模块

```bash
./nginx -v
```



7、复制fastdfs-nginx-module源码中的配置文件到/etc/fdfs目录，并修改

```bash
cd /home/FastDFS/fastdfs-nginx-module-1.20/src
cp mod_fastdfs.conf /etc/fdfs/
```



```bash
# 连接超时时间
connect_timeout=10
# Tracker Server
tracker_server=192.168.1.190:22122
# StorageServer 默认端口
storage_server_port=23000
# 如果文件ID的uri中包含/group**，则要设置为true
url_have_group_name = true
# Storage 配置的store_path0路径，必须和storage.conf中的一致
store_path0=/home/data/fastdfs/storage
```



8、复制FastDFS的部分配置文件到/etc/fdfs目录

```bash
cd /home/FastDFS/fastdfs-5.11/conf
cp http.conf /etc/fdfs/
cp mime.types /etc/fdfs/
```



9、配置nginx，修改nginx.conf：

```bash
location ~/group([0-9])/M00 {
    ngx_fastdfs_module;
}
```



10、启动Nginx：

```bash
./nginx
ngx_http_fastdfs_set pid=11256
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#如何使用-2)如何使用

1、配置文件

```yml
# FastDFS配置
fdfs:
  domain: http://8.129.231.12
  soTimeout: 3000
  connectTimeout: 2000
  trackerList: 8.129.231.12:22122
```



启动`ruoyi-file`应用，调用`upload`上传接口后会返回一个地址，在浏览器中打开`http://8.129.231.12/group1/M00/00/00/xxxx.png`就可以访问图片了。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/file.html#切换存储方式)切换存储方式

目前默认采用的是本地存储，可以通过注解`@Primary`指定需要使用的文件接口。

```java
@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService
{
    .....
}
```



**本地文件存储**：`LocalSysFileServiceImpl.java`

**Minio 文件存储**：`MinioSysFileServiceImpl.java`

**FastDFS文件存储**：`FastDfsSysFileServiceImpl.java`

# 分布式事务

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#基本介绍)基本介绍

- 什么是分布式事务

指一次大的操作由不同的小操作组成的，这些小的操作分布在不同的服务器上，分布式事务需要保证这些小操作要么全部成功，要么全部失败。从本质上来说，分布式事务就是为了保证不同数据库的数据一致性。

- 为什么要使用分布式事务

在微服务独立数据源的思想，每一个微服务都有一个或者多个数据源，虽然单机单库事务已经非常成熟，但是由于网路延迟和不可靠的客观因素，分布式事务到现在也还没有成熟的方案，对于中大型网站，特别是涉及到交易的网站，一旦将服务拆分微服务，分布式事务一定是绕不开的一个组件，通常解决分布式事务问题。

- seata 分布式事务

`Seata`是阿里开源的一款开源的分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。

`Seata`目标打造一站式的分布事务的解决方案，最终会提供四种事务模式：

**AT 模式**：参见([《Seata AT 模式》 (opens new window)](https://seata.io/zh-cn/docs/dev/mode/at-mode.html))文档
**TCC 模式**：参见([《Seata TCC 模式》 (opens new window)](https://seata.io/zh-cn/docs/dev/mode/tcc-mode.html))文档
**Saga 模式**：参见([《SEATA Saga 模式》 (opens new window)](https://seata.io/zh-cn/docs/dev/mode/saga-mode.html))文档
**XA 模式**：正在开发中... 目前使用的流行度情况是：`AT` > `TCC` > `Saga`。因此，我们在学习`Seata`的时候，可以花更多精力在`AT`模式上，最好搞懂背后的实现原理，毕竟分布式事务涉及到数据的正确性，出问题需要快速排查定位并解决。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#下载方式)下载方式

- Windows平台安装包下载

可以从`https://github.com/seata/seata/releases`下载`seata-server-$version.zip`包。

Windows下载解压后（.zip），直接点击`bin/seata-server.bat`就可以了。（我使用的是1.4.0版本）

![seata](https://oscimg.oschina.net/oscnet/up-859c18c316354a0ec69d8c6b765ef97b03b.png)

提示

如果觉得官网下载慢，可以使用我分享的网盘地址: https://pan.baidu.com/s/1E9J52g6uW_VFWY34fHL6zA 提取码: vneh

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#如何使用)如何使用

1、创建相关测试数据库和表。

```sql
# 订单数据库信息 seata_order
DROP DATABASE IF EXISTS seata_order;
CREATE DATABASE seata_order;

DROP TABLE IF EXISTS seata_order.p_order;
CREATE TABLE seata_order.p_order
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    user_id          INT(11) DEFAULT NULL,
    product_id       INT(11) DEFAULT NULL,
    amount           INT(11) DEFAULT NULL,
    total_price      DOUBLE       DEFAULT NULL,
    status           VARCHAR(100) DEFAULT NULL,
    add_time         DATETIME     DEFAULT CURRENT_TIMESTAMP,
    last_update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_order.undo_log;
CREATE TABLE seata_order.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
  
# 产品数据库信息 seata_product
DROP DATABASE IF EXISTS seata_product;
CREATE DATABASE seata_product;

DROP TABLE IF EXISTS seata_product.product;
CREATE TABLE seata_product.product
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    price            DOUBLE   DEFAULT NULL,
    stock            INT(11) DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_product.undo_log;
CREATE TABLE seata_product.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

INSERT INTO seata_product.product (id, price, stock)
VALUES (1, 10, 20);


# 账户数据库信息 seata_account
DROP DATABASE IF EXISTS seata_account;
CREATE DATABASE seata_account;

DROP TABLE IF EXISTS seata_account.account;
CREATE TABLE seata_account.account
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    balance          DOUBLE   DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_account.undo_log;
CREATE TABLE seata_account.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
INSERT INTO seata_account.account (id, balance)
VALUES (1, 50);
```



其中，每个库中的`undo_log`表，是`Seata AT`模式必须创建的表，主要用于分支事务的回滚。
另外，考虑到测试方便，我们插入了一条`id = 1`的`account`记录，和一条`id = 1`的`product`记录。

2、引入`ruoyi-common-datasource`依赖（包含`seata`配置）

```xml
<!-- ruoyi common datasource -->
<dependency>
	<groupId>com.ruoyi</groupId>
	<artifactId>ruoyi-common-datasource</artifactId>
</dependency>
```

1
2
3
4
5

3、服务配置文件

```yml
# spring配置
spring: 
  redis:
    host: localhost
    port: 6379
    password: 
  datasource:
    druid:
      stat-view-servlet:
        enabled: true
        loginUsername: admin
        loginPassword: 123456
    dynamic:
      druid:
        initial-size: 5
        min-idle: 5
        maxActive: 20
        maxWait: 60000
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        filters: stat,wall,slf4j
        connectionProperties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
      datasource:
          # 主库数据源
          master:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://localhost:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            username: root
            password: password
          # seata_order数据源
          order:
            username: root
            password: password
            url: jdbc:mysql://localhost:3306/seata_order?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            driver-class-name: com.mysql.cj.jdbc.Driver
          # seata_account数据源
          account:
            username: root
            password: password
            url: jdbc:mysql://localhost:3306/seata_account?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            driver-class-name: com.mysql.cj.jdbc.Driver
          # seata_product数据源
          product:
            username: root
            password: password
            url: jdbc:mysql://localhost:3306/seata_product?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
            driver-class-name: com.mysql.cj.jdbc.Driver
      seata: true    #开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭

# seata配置
seata:
  enabled: true
  # Seata 应用编号，默认为 ${spring.application.name}
  application-id: ${spring.application.name}
  # Seata 事务组编号，用于 TC 集群名
  tx-service-group: ${spring.application.name}-group
  # 关闭自动代理
  enable-auto-data-source-proxy: false
  # 服务配置项
  service:
    # 虚拟组和分组的映射
    vgroup-mapping:
      ruoyi-system-group: default
    # 分组和 Seata 服务的映射
    grouplist:
      default: 127.0.0.1:8091
  config:
    type: file
  registry:
    type: file

# mybatis配置
mybatis:
    # 搜索指定包别名
    typeAliasesPackage: com.ruoyi.system
    # 配置mapper的扫描，找到所有的mapper.xml映射文件
    mapperLocations: classpath:mapper/**/*.xml

# swagger配置
swagger:
  title: 系统模块接口文档
  license: Powered By ruoyi
  licenseUrl: https://ruoyi.vip
```

提示

注意，一定要设置`spring.datasource.dynamic.seata`配置项为`true`，开启对`Seata`的集成，否则会导致`Seata`全局事务回滚失败。

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#示例代码)示例代码

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#domain)Domain

**Account.java**

```java
package com.ruoyi.system.domain;

import java.util.Date;

public class Account
{
    private Long id;

    /**
     * 余额
     */
    private Double balance;

    private Date lastUpdateTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Double getBalance()
    {
        return balance;
    }

    public void setBalance(Double balance)
    {
        this.balance = balance;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
}
```



**Order.java**

```java
package com.ruoyi.system.domain;

public class Order
{
    private Integer id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 订单状态
     */
    private int status;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 总金额
     */
    private Double totalPrice;

    public Order()
    {
    }

    public Order(Long userId, Long productId, int status, Integer amount)
    {
        this.userId = userId;
        this.productId = productId;
        this.status = status;
        this.amount = amount;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
```



**Product.java**

```java
package com.ruoyi.system.domain;

import java.util.Date;

public class Product
{

    private Integer id;
    /**
     * 价格
     */
    private Double price;
    /**
     * 库存
     */
    private Integer stock;

    private Date lastUpdateTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getStock()
    {
        return stock;
    }

    public void setStock(Integer stock)
    {
        this.stock = stock;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#dto)Dto

**PlaceOrderRequest.java**

```java
package com.ruoyi.system.domain.dto;

public class PlaceOrderRequest
{
    private Long userId;

    private Long productId;

    private Integer amount;

    public PlaceOrderRequest()
    {
    }

    public PlaceOrderRequest(Long userId, Long productId, Integer amount)
    {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
}
```



**ReduceBalanceRequest.java**

```java
package com.ruoyi.system.domain.dto;

public class ReduceBalanceRequest
{
    private Long userId;

    private Integer price;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }
}
```



**ReduceStockRequest.java**

```java
package com.ruoyi.system.domain.dto;

public class ReduceStockRequest
{
    private Long productId;

    private Integer amount;

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#mapper)Mapper

**AccountMapper.java**

```java
package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Account;

public interface AccountMapper
{
    public Account selectById(Long userId);

    public void updateById(Account account);
}
```



**OrderMapper.java**

```java
package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Order;

public interface OrderMapper
{
    public void insert(Order order);

    public void updateById(Order order);
}
```



**ProductMapper.java**

```java
package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Product;

public interface ProductMapper
{
    public Product selectById(Long productId);

    public void updateById(Product product);
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#service)Service

**AccountService.java**

```java
package com.ruoyi.system.service;

public interface AccountService
{
    /**
     * 账户扣减
     * @param userId 用户 ID
     * @param price 扣减金额
     */
    void reduceBalance(Long userId, Double price);
}
```



**OrderService.java**

```java
package com.ruoyi.system.service;

import com.ruoyi.system.domain.dto.PlaceOrderRequest;

public interface OrderService
{
    /**
     * 下单
     *
     * @param placeOrderRequest 订单请求参数
     */
    void placeOrder(PlaceOrderRequest placeOrderRequest);
}
```



**ProductService.java**

```java
package com.ruoyi.system.service;

public interface ProductService
{
    /**
     * 扣减库存
     *
     * @param productId 商品 ID
     * @param amount 扣减数量
     * @return 商品总价
     */
    Double reduceStock(Long productId, Integer amount);
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#serviceimpl)ServiceImpl

**AccountService.java**

```java
package com.ruoyi.system.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.system.domain.Account;
import com.ruoyi.system.mapper.AccountMapper;
import com.ruoyi.system.service.AccountService;
import io.seata.core.context.RootContext;

@Service
public class AccountServiceImpl implements AccountService
{
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    
    @Resource
    private AccountMapper accountMapper;

    /**
     * 事务传播特性设置为 REQUIRES_NEW 开启新的事务 重要！！！！一定要使用REQUIRES_NEW
     */
    @DS("account")
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reduceBalance(Long userId, Double price)
    {
        log.info("=============ACCOUNT START=================");
        log.info("当前 XID: {}", RootContext.getXID());

        Account account = accountMapper.selectById(userId);
        Double balance = account.getBalance();
        log.info("下单用户{}余额为 {},商品总价为{}", userId, balance, price);

        if (balance < price)
        {
            log.warn("用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new RuntimeException("余额不足");
        }
        log.info("开始扣减用户 {} 余额", userId);
        double currentBalance = account.getBalance() - price;
        account.setBalance(currentBalance);
        accountMapper.updateById(account);
        log.info("扣减用户 {} 余额成功,扣减后用户账户余额为{}", userId, currentBalance);
        log.info("=============ACCOUNT END=================");
    }

}
```



**OrderService.java**

```java
package com.ruoyi.system.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.dto.PlaceOrderRequest;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.service.AccountService;
import com.ruoyi.system.service.OrderService;
import com.ruoyi.system.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;

@Service
public class OrderServiceImpl implements OrderService
{
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @DS("order") // 每一层都需要使用多数据源注解切换所选择的数据库
    @Override
    @Transactional
    @GlobalTransactional // 重点 第一个开启事务的需要添加seata全局事务注解
    public void placeOrder(PlaceOrderRequest request)
    {
        log.info("=============ORDER START=================");
        Long userId = request.getUserId();
        Long productId = request.getProductId();
        Integer amount = request.getAmount();
        log.info("收到下单请求,用户:{}, 商品:{},数量:{}", userId, productId, amount);

        log.info("当前 XID: {}", RootContext.getXID());

        Order order = new Order(userId, productId, 0, amount);

        orderMapper.insert(order);
        log.info("订单一阶段生成，等待扣库存付款中");
        // 扣减库存并计算总价
        Double totalPrice = productService.reduceStock(productId, amount);
        // 扣减余额
        accountService.reduceBalance(userId, totalPrice);

        order.setStatus(1);
        order.setTotalPrice(totalPrice);
        orderMapper.updateById(order);
        log.info("订单已成功下单");
        log.info("=============ORDER END=================");
    }

}
```




**ProductService.java**

```java
package com.ruoyi.system.service.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.system.domain.Product;
import com.ruoyi.system.mapper.ProductMapper;
import com.ruoyi.system.service.ProductService;
import io.seata.core.context.RootContext;

@Service
public class ProductServiceImpl implements ProductService
{
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    private ProductMapper productMapper;

    /**
     * 事务传播特性设置为 REQUIRES_NEW 开启新的事务 重要！！！！一定要使用REQUIRES_NEW
     */
    @DS("product")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Double reduceStock(Long productId, Integer amount)
    {
        log.info("=============PRODUCT START=================");
        log.info("当前 XID: {}", RootContext.getXID());

        // 检查库存
        Product product = productMapper.selectById(productId);
        Integer stock = product.getStock();
        log.info("商品编号为 {} 的库存为{},订单商品数量为{}", productId, stock, amount);

        if (stock < amount)
        {
            log.warn("商品编号为{} 库存不足，当前库存:{}", productId, stock);
            throw new RuntimeException("库存不足");
        }
        log.info("开始扣减商品编号为 {} 库存,单价商品价格为{}", productId, product.getPrice());
        // 扣减库存
        int currentStock = stock - amount;
        product.setStock(currentStock);
        productMapper.updateById(product);
        double totalPrice = product.getPrice() * amount;
        log.info("扣减商品编号为 {} 库存成功,扣减后库存为{}, {} 件商品总价为 {} ", productId, currentStock, amount, totalPrice);
        log.info("=============PRODUCT END=================");
        return totalPrice;
    }

}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#controller)Controller

**OrderController.java**

```java
package com.ruoyi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.system.domain.dto.PlaceOrderRequest;
import com.ruoyi.system.service.OrderService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public String placeOrder(@Validated @RequestBody PlaceOrderRequest request)
    {
        orderService.placeOrder(request);
        return "下单成功";
    }

    @PostMapping("/test1")
    @ApiOperation("测试商品库存不足-异常回滚")
    public String test1()
    {
        // 商品单价10元，库存20个,用户余额50元，模拟一次性购买22个。 期望异常回滚
        orderService.placeOrder(new PlaceOrderRequest(1L, 1L, 22));
        return "下单成功";
    }

    @PostMapping("/test2")
    @ApiOperation("测试用户账户余额不足-异常回滚")
    public String test2()
    {
        // 商品单价10元，库存20个，用户余额50元，模拟一次性购买6个。 期望异常回滚
        orderService.placeOrder(new PlaceOrderRequest(1L, 1L, 6));
        return "下单成功";
    }
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#mapper-xml)Mapper.xml

**AccountMapper.xml**

```sql
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ruoyi.system.mapper.AccountMapper">
    
    <resultMap type="Account" id="AccountResult">
    	<id     property="id"              column="id"                />
        <result property="balance"         column="balance"           />
        <result property="lastUpdateTime"  column="last_update_time"  />
    </resultMap>
    
    <select id="selectById" parameterType="Account" resultMap="AccountResult">
        select id, balance, last_update_time 
		from account where id = #{userId}
    </select>
    
    <update id="updateById" parameterType="Account">
        update account set balance = #{balance}, last_update_time = sysdate() where id = #{id}
    </update>
    
</mapper>
```



**OrderMapper.xml**

```sql
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ruoyi.system.mapper.OrderMapper">
    
    <resultMap type="Order" id="OrderResult">
    	<id     property="id"              column="id"                />
        <result property="userId"          column="user_id"           />
        <result property="productId"       column="product_id"        />
        <result property="amount"          column="amount"            />
        <result property="totalPrice"      column="total_price"       />
        <result property="status"          column="status"            />
        <result property="addTime"         column="add_time"          />
        <result property="lastUpdateTime"  column="last_update_time"  />
    </resultMap>
    
    <insert id="insert" parameterType="Order">
        insert into p_order (
			<if test="userId != null and userId != '' ">user_id,</if>
			<if test="productId != null and productId != '' ">product_id,</if>
			<if test="amount != null and amount != '' ">amount,</if>
			<if test="totalPrice != null and totalPrice != '' ">total_price,</if>
			<if test="status != null and status != ''">status,</if>
 			add_time
        )values(
			<if test="userId != null and userId != ''">#{userId},</if>
			<if test="productId != null and productId != ''">#{productId},</if>
			<if test="amount != null and amount != ''">#{amount},</if>
			<if test="totalPrice != null and totalPrice != ''">#{totalPrice},</if>
			<if test="status != null and status != ''">#{status},</if>
 			sysdate()
		)
    </insert>
	 
    <update id="updateById" parameterType="Order">
        update p_order 
        <set>
            <if test="userId != null and userId != ''">user_id = #{userId},</if>
            <if test="productId != null and productId != ''">product_id = #{productId},</if>
            <if test="amount != null and amount != ''">amount = #{amount},</if>
            <if test="totalPrice != null and totalPrice != ''">total_price = #{totalPrice},</if>
            <if test="status != null and status != ''">status = #{status},</if>
 			last_update_time = sysdate()
        </set>
        where id = #{id}
    </update>
    
</mapper>
```




**ProductMapper.xml**

```sql
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ruoyi.system.mapper.ProductMapper">
    
    <resultMap type="Product" id="ProductResult">
    	<id     property="id"              column="id"                />
        <result property="price"           column="price"             />
        <result property="stock"           column="stock"             />
        <result property="lastUpdateTime"  column="last_update_time"  />
    </resultMap>
    
    <select id="selectById" parameterType="Product" resultMap="ProductResult">
        select id, price, stock, last_update_time 
		from product where id = #{productId}
    </select>
    
    <update id="updateById" parameterType="Product">
        update product set price = #{price}, stock = #{stock}, last_update_time = sysdate() where id = #{id}
    </update>
    
</mapper>
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#测试验证)测试验证

使用`Postman`工具测试接口，注意观察运行日志，至此分布式事务集成案例全流程完毕。

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#正常下单)正常下单

模拟正常下单，买一个商品 `http://localhost:9201/order/placeOrder`

Content-Type/application/json

```json
{
    "userId": 1,
    "productId": 1,
    "amount": 1
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#库存不足)库存不足

模拟库存不足，事务回滚 `http://localhost:9201/order/placeOrder`

Content-Type/application/json

```json
{
    "userId": 1,
    "productId": 1,
    "amount": 22
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#用户余额不足)用户余额不足

模拟用户余额不足，事务回滚 `http://localhost:9201/order/placeOrder`

Content-Type/application/json

```json
{
    "userId": 1,
    "productId": 1,
    "amount": 6
}
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#集成nacos配置中心)集成Nacos配置中心

1、解压`seata-server-$version.zip`后修改`conf/registry.conf`文件：

```json
registry {
  type = "nacos"
  nacos {
    application = "seata-server"
    serverAddr = "127.0.0.1:8848"
    group = "SEATA_GROUP"
    namespace = ""
    cluster = "default"
    username = "nacos"
    password = "nacos"
  }
}

config {
  type = "nacos"
  nacos {
    serverAddr = "127.0.0.1:8848"
    namespace = ""
    group = "SEATA_GROUP"
    username = "nacos"
    password = "nacos"
  }
}
```



由于使用`nacos`作为注册中心，所以`conf`目录下的`file.conf`无需理会。然后就可以直接启动`bin/seata-server.bat`，可以在`nacos`里看到一个名为`seata-server`的服务了。 ![seata](https://oscimg.oschina.net/oscnet/up-ab93c4f4de03cd9fa3dfcb7727531399c69.png)

2、由于`seata`使用`mysql`作为`db`高可用数据库，故需要在`mysql`创建一个`ry-seata`库，并导入数据库脚本。

```sql
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(96),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
```



3、`config.txt`文件复制到seata目录

**config.txt**

```text
service.vgroupMapping.ruoyi-system-group=default
store.mode=db
store.db.datasource=druid
store.db.dbType=mysql
store.db.driverClassName=com.mysql.jdbc.Driver
store.db.url=jdbc:mysql://127.0.0.1:3306/ry-seata?useUnicode=true
store.db.user=root
store.db.password=password
store.db.minConn=5
store.db.maxConn=30
store.db.globalTable=global_table
store.db.branchTable=branch_table
store.db.queryLimit=100
store.db.lockTable=lock_table
store.db.maxWait=5000
```

1
2
3
4
5
6
7
8
9
10
11
12
13
14
15

4、`nacos-config.sh`复制到seata的conf目录

**nacos-config.sh**

```sh
#!/usr/bin/env bash
# Copyright 1999-2019 Seata.io Group.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at、
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

while getopts ":h:p:g:t:u:w:" opt
do
  case $opt in
  h)
    host=$OPTARG
    ;;
  p)
    port=$OPTARG
    ;;
  g)
    group=$OPTARG
    ;;
  t)
    tenant=$OPTARG
    ;;
  u)
    username=$OPTARG
    ;;
  w)
    password=$OPTARG
    ;;
  ?)
    echo " USAGE OPTION: $0 [-h host] [-p port] [-g group] [-t tenant] [-u username] [-w password] "
    exit 1
    ;;
  esac
done

urlencode() {
  for ((i=0; i < ${#1}; i++))
  do
    char="${1:$i:1}"
    case $char in
    [a-zA-Z0-9.~_-]) printf $char ;;
    *) printf '%%%02X' "'$char" ;;
    esac
  done
}

if [[ -z ${host} ]]; then
    host=localhost
fi
if [[ -z ${port} ]]; then
    port=8848
fi
if [[ -z ${group} ]]; then
    group="SEATA_GROUP"
fi
if [[ -z ${tenant} ]]; then
    tenant=""
fi
if [[ -z ${username} ]]; then
    username=""
fi
if [[ -z ${password} ]]; then
    password=""
fi

nacosAddr=$host:$port
contentType="content-type:application/json;charset=UTF-8"

echo "set nacosAddr=$nacosAddr"
echo "set group=$group"

failCount=0
tempLog=$(mktemp -u)
function addConfig() {
  curl -X POST -H "${contentType}" "http://$nacosAddr/nacos/v1/cs/configs?dataId=$(urlencode $1)&group=$group&content=$(urlencode $2)&tenant=$tenant&username=$username&password=$password" >"${tempLog}" 2>/dev/null
  if [[ -z $(cat "${tempLog}") ]]; then
    echo " Please check the cluster status. "
    exit 1
  fi
  if [[ $(cat "${tempLog}") =~ "true" ]]; then
    echo "Set $1=$2 successfully "
  else
    echo "Set $1=$2 failure "
    (( failCount++ ))
  fi
}

count=0
for line in $(cat $(dirname "$PWD")/config.txt | sed s/[[:space:]]//g); do
  (( count++ ))
	key=${line%%=*}
    value=${line#*=}
	addConfig "${key}" "${value}"
done

echo "========================================================================="
echo " Complete initialization parameters,  total-count:$count ,  failure-count:$failCount "
echo "========================================================================="

if [[ ${failCount} -eq 0 ]]; then
	echo " Init nacos config finished, please start seata-server. "
else
	echo " init nacos config fail. "
fi
```



5、执行命令，后面填写`nacos`的IP地址，我的是本机所以是`127.0.0.1`

```sh
sh nacos-config.sh 127.0.0.1
```

1

成功后`nacos`配置列表也能查询到相关配置 ![nacos](https://oscimg.oschina.net/oscnet/up-7e4bb45b376e7468057cd47af0d3e5d7f35.png)

6、修改服务配置文件

```yml
# spring配置
spring: 
  datasource:
    dynamic:
      # 开启seata代理
      seata: true
	  
# seata配置
seata:
  enabled: true
  # Seata 应用编号，默认为 ${spring.application.name}
  application-id: ${spring.application.name}
  # Seata 事务组编号，用于 TC 集群名
  tx-service-group: ${spring.application.name}-group
  # 关闭自动代理
  enable-auto-data-source-proxy: false
  # 服务配置项
  service:
    # 虚拟组和分组的映射
    vgroup-mapping:
      ruoyi-system-group: default
  config:
    type: nacos
    nacos:
      serverAddr: 127.0.0.1:8848
      group: SEATA_GROUP
      namespace:
  registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      namespace:
```

7、测试验证

[参考测试验证](https://doc.ruoyi.vip/ruoyi-cloud/cloud/seata.html#测试验证)

# 分布式日志

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#基本介绍)基本介绍

- 什么是分布式日志

在分布式应用中，日志被分散在储存不同的设备上。如果你管理数十上百台服务器，你还在使用依次登录每台机器的传统方法查阅日志。这样是不是感觉很繁琐和效率低下。所以我们使用集中化的日志管理，分布式日志就是对大规模日志数据进行采集、追踪、处理。

- 为什么要使用分布式日志

一般我们需要进行日志分析场景：直接在日志文件中`grep`、`awk`就可以获得自己想要的信息。但在规模较大的场景中，此方法效率低下，面临问题包括日志量太大如何归档、文本搜索太慢怎么办、如何多维度查询。需要集中化的日志管理，所有服务器上的日志收集汇总。常见解决思路是建立集中式日志收集系统，将所有节点上的日志统一收集，管理，访问。

- ELK 分布式日志

实际上`ELK`是三款软件的简称，分别是`Elasticsearch`、 `Logstash`、`Kibana`组成。

**Elasticsearch** 基于`java`，是个开源分布式搜索引擎，它的特点有：分布式，零配置，自动发现，索引自动分片，索引副本机制，`restful`风格接口，多数据源，自动搜索负载等。

**Kibana** 基于`nodejs`，也是一个开源和免费的工具，`Kibana`可以为`Logstash`和`ElasticSearch`提供的日志分析友好的Web 界面，可以汇总、分析和搜索重要数据日志。

**Logstash** 基于`java`，是一个开源的用于收集,分析和存储日志的工具。

下面是`ELK`的工作原理： ![elk](https://oscimg.oschina.net/oscnet/up-c62bd9299557f77a05d1a9c4ccd046f8fef.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#elasticsearch)Elasticsearch

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#简介)简介

ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。

我们建立一个网站或应用程序，并要添加搜索功能，但是想要完成搜索工作的创建是非常困难的。我们希望搜索解决方案要运行速度快，我们希望能有一个零配置和一个完全免费的搜索模式，我们希望能够简单地使用JSON通过HTTP来索引数据，我们希望我们的搜索服务器始终可用，我们希望能够从一台开始并扩展到数百台，我们要实时搜索，我们要简单的多租户，我们希望建立一个云的解决方案。因此我们利用Elasticsearch来解决所有这些问题及可能出现的更多其它问题。

ElasticSearch是Elastic Stack的核心，同时Elasticsearch 是一个分布式、RESTful风格的搜索和数据分析引擎，能够解决不断涌现出的各种用例。作为Elastic Stack的核心，它集中存储您的数据，帮助您发现意料之中以及意料之外的情况。

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#下载)下载

到官网下载： ([https://www.elastic.co/cn/downloads/elasticsearch (opens new window)](https://www.elastic.co/cn/downloads/elasticsearch)) ![Elasticsearch](https://oscimg.oschina.net/oscnet/up-d392963dafc68bc669d12ada72348dbf95b.png)

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#安装)安装

- 解压到相应目录

```sh
tar -zxvf elasticsearch-7.10.2-linux-x86_64.tar.gz -C /usr/local
```

- 修改配置

```sh
cd /usr/local/elasticsearch-7.10.2/config/
vim elasticsearch.yml
```

```sh
node.name: node-1
path.data: /usr/local/elasticsearch-7.10.2/data
path.logs: /usr/local/elasticsearch-7.10.2/logs
network.host: 127.0.0.1
http.host: 0.0.0.0
http.port: 9200
discovery.seed_hosts: ["127.0.0.1"]
cluster.initial_master_nodes: ["node-1"]
```

- 创建`es`用户 因为`ElasticSearch`不支持`Root`用户直接操作，因此我们需要创建一个`es`用户

```sh
useradd es
chown -R es:es /usr/local/elasticsearch-7.10.2
```

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#启动)启动

- 切换用户成es用户进行操作

```sh
su - es
/usr/local/elasticsearch-7.10.2/bin/elasticsearch
```



- 后台启动

```sh
/usr/local/elasticsearch-7.10.2/bin/elasticsearch -d 
```



在浏览器打开`9200`端口地址： ([http://120.78.129.95:9200/ (opens new window)](http://120.78.129.95:9200/))，如果出现了下面的信息，就表示已经成功启动了 ![Elasticsearch](https://oscimg.oschina.net/oscnet/up-38da6dfc0998a88b8b2f974f6192ae6420a.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#logstash)Logstash

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#简介-2)简介

Logstash是一个开源的服务器端数据处理管道，能够同时从多个来源采集数据，转换数据，然后将数据发送到最喜欢的存储库中（我们的存储库当然是ElasticSearch）

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#下载-2)下载

到官网下载： ([https://www.elastic.co/cn/downloads/logstash (opens new window)](https://www.elastic.co/cn/downloads/logstash))
![Logstash](https://oscimg.oschina.net/oscnet/up-7780b6e1555bae2c2d2ce3e1dde44d9e783.png)

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#安装-2)安装

- 解压到相应目录

```sh
tar -zxvf logstash-7.10.2.tar.gz -C /usr/local
```



- 新增配置文件

```sh
cd /usr/local/logstash-7.10.2/bin
vim logstash-elasticsearch.conf
```



```sh
input {
	stdin {}
}
output {
	elasticsearch {
		hosts => '120.78.129.95:9200'
	}
	stdout {
		codec => rubydebug
	}
}
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#启动-2)启动

```sh
./logstash -f logstash-elasticsearch.conf
```



## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#kibana)Kibana

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#简介-3)简介

Kibana 是一款开源的数据分析和可视化平台，它是 Elastic Stack 成员之一，设计用于和 Elasticsearch 协作。您可以使用 Kibana 对 Elasticsearch 索引中的数据进行搜索、查看、交互操作。您可以很方便的利用图表、表格及地图对数据进行多元化的分析和呈现。

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#下载-3)下载

到官网下载： ([https://www.elastic.co/cn/downloads/kibana (opens new window)](https://www.elastic.co/cn/downloads/kibana))
![Kibana](https://oscimg.oschina.net/oscnet/up-8a4821b16ba2f3bd96baf9a3b2bb7b55f0b.png)

### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#安装-3)安装

- 解压到相应目录

```sh
tar -zxvf kibana-7.10.2-linux-x86_64.tar.gz -C /usr/local
mv /usr/local/kibana-7.10.2-linux-x86_64 /usr/local/kibana-7.10.2
```



- 修改配置

```sh
cd /usr/local/kibana-7.10.2/config
vim kibana.yml
```



```sh
server.port: 5601 
server.host: "0.0.0.0" 
elasticsearch.hosts: ["http://120.78.129.95:9200"] 
kibana.index: ".kibana"
```



- 授权es用户

```text
chown -R es:es /usr/local/kibana-7.10.2/
```



### [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#启动-3)启动

- 切换用户成es用户进行操作

```sh
su - es
/usr/local/kibana-7.10.2/bin/kibana 
```



- 后台启动

```sh
/usr/local/kibana-7.10.2/bin/kibana &
```

1

在浏览器打开`5601`端口地址： ([http://120.78.129.95:5601/ (opens new window)](http://120.78.129.95:5601/))，如果出现了下面的信息，就表示已经成功启动了 ![kibana](https://oscimg.oschina.net/oscnet/up-f9bd125ad0b1d3887a2d3f94df9e9202d2c.png)

## [#](https://doc.ruoyi.vip/ruoyi-cloud/cloud/elk.html#日志收集)日志收集

对应服务器安装`logstash`，配置规则，例如新建`logstash-apache.conf`

```sh
input {
  file {
    path => "/home/ruoyi/logs/sys-*.log"
	start_position => beginning
	sincedb_path => "/dev/null"
	codec => multiline {
      pattern => "^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}"
      negate => true
      auto_flush_interval => 3
      what => previous
    }
  }
}

filter {
  if [path] =~ "info" {
    mutate { replace => { type => "sys-info" } }
    grok {
      match => { "message" => "%{COMBINEDAPACHELOG}" }
    }
    date {
      match => [ "timestamp" , "dd/MMM/yyyy:HH:mm:ss Z" ]
    }
  } else if [path] =~ "error" {
    mutate { replace => { type => "sys-error" } }
  } else {
    mutate { replace => { type => "random_logs" } }
  }
}

output {
  elasticsearch {
    hosts => '120.78.129.95:9200'
  }
  stdout { codec => rubydebug }
}
```



- 启动logstash

```sh
./logstash -f logstash-apache.conf
```



- 通过`kibana`可视化检索各个服务日志数据 ![kibana](https://oscimg.oschina.net/oscnet/up-928d6f45a566fc7e6191db840a4b27de551.png)