# SpringCloud学习

## 常用地址

https://spring.io/projects/spring-cloud

https://start.spring.io/actuator/info



## 学习视频地址

https://gitee.com/debugxu/SpringCloud2020

代码地址:

D:\workspaces\resources\cloud2020

https://github.com/XuHaijwill/springCloud

链接：https://pan.baidu.com/s/1HyJfyzUD24RKONsd_tSqag 
提取码：syl5 

## IDEA 无法显示 Run Dashboard 的解决方法

https://www.cnblogs.com/dalianpai/p/12258444.html

## 微服务架构的高并发问题

https://www.cnblogs.com/dalianpai/p/12270902.html

## SpringCloud Hystrix微服务架构的高并发问题与解决策略

https://blog.csdn.net/liuhenghui5201/article/details/110291387?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_v2~rank_aggregation-1-110291387.pc_agg_rank_aggregation&utm_term=%E5%BE%AE%E6%9C%8D%E5%8A%A1%E4%B8%AD%E7%9A%84%E9%AB%98%E5%B9%B6%E5%8F%91&spm=1000.2123.3001.4430

## [谈谈微服务中的 API 网关（API Gateway）](https://www.cnblogs.com/savorboard/p/api-gateway.html)

https://www.cnblogs.com/savorboard/p/api-gateway.html

![](imgs\修改配置001.png)

## nacos学习指南

学习地址：https://github.com/alibaba/nacos

https://nacos.io/zh-cn/docs/deployment.html



若依框架

```
切换存储方式
目前默认采用的是本地存储，可以通过注解@Primary指定需要使用的文件接口。

@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService
{
    .....
}

本地文件存储：LocalSysFileServiceImpl.java

Minio 文件存储：MinioSysFileServiceImpl.java

FastDFS文件存储：FastDfsSysFileServiceImpl.java
```



