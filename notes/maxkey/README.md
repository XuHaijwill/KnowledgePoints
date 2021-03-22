配置

1)hosts配置文件目录C:\Windows\System32\drivers\etc
新增如下内容

127.0.0.1 sso.maxkey.top
127.0.0.1 tokenbased.demo.maxkey.top
127.0.0.1 cas.demo.maxkey.top
127.0.0.1 oauth.demo.maxkey.top



访问地址

| 序号 | 名称             | 访问地址                                    |
| :--- | :--------------- | :------------------------------------------ |
| 1    | 认证平台         | https://sso.maxkey.top/maxkey/login         |
| 2    | 管理平台         | http://sso.maxkey.top:9527/maxkey-mgt/login |
| 3    | 集成指南         | http://sso.maxkey.top:9521/wiki             |
| 4    | 认证平台接口文档 | https://sso.maxkey.top/maxkey/doc.html      |
| 5    | 账户密码         | admin/maxkey                                |

## MaxKey-Demo

https://github.com/MaxKeyTop/MaxKey-Demo



gradle-wrapper.properties

根据电脑安装gradle版本配置即可

```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
distributionUrl=https://services.gradle.org/distributions/gradle-6.8.3-bin.zip
```



# windows10下Kafka环境搭建

参考：https://blog.csdn.net/tianmanchn/article/details/78943147?utm_term=windows10%E5%AE%89%E8%A3%85kafka&utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduweb~default-1-78943147&spm=3001.4430

## 安装JDK

## Zookeeper

http://mirrors.hust.edu.cn/apache/zookeeper/

## Kafka：

http://kafka.apache.org/downloads.html



