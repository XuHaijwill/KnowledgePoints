# activiti工作指南

## 2019最全Activiti6.0源码概述以及核心模块

https://blog.csdn.net/x15011238662/article/details/85766569?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control



## Activiti 流程部署方式 activi 动态部署（高级源码篇）



https://blog.csdn.net/tianzong8/article/details/51170143?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-8.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-8.control



## Activiti6源码分析以及实战课程

https://blog.csdn.net/qq_30739519/article/details/81219422



## [springboot2.2 集成 activity6实现请假完整流程示例详解](http://www.cppcns.com/ruanjian/java/326274.html)

```
# Spring配置
spring:
  # activiti 模块
  activiti:
    # 解决启动报错：class path resource [processes/] cannot be resolved to URL because it does not exist
    check-process-definitions: false
    # 检测身份信息表是否存在
    db-identity-used: false
    #设置数据库更新策略为不更新
    database-schema-update: false
```



```
databaseSchemaUpdate：数据库更新策略，其取值有四个：
flase：       默认值。activiti在启动时，会对比数据库表中保存的版本，如果没有表或者版本不匹配，将抛出异常。（生产环境常用）
true：        activiti会对数据库中所有表进行更新操作。如果表不存在，则自动创建。（开发时常用）
create_drop： 在activiti启动时创建表，在关闭时删除表（必须手动关闭引擎，才能删除表）。（单元测试常用）
drop-create： 在activiti启动时删除原来的旧表，然后在创建新表（不需要手动关闭引擎）。
```



## 若依课程

https://www.bilibili.com/video/av79865921/



学习6.x

https://www.bilibili.com/video/BV1RT4y1P7vy?from=search&seid=9443321178505080102

链接：https://pan.baidu.com/s/1rN3yRUf4p-x5wZJZCRPRkA 

## Activiti官网

https://www.activiti.org/userguide/

数据库文件

However, often only database administrators can execute DDL statements on a database. On a production system, this is also the wisest of choices. The SQL DDL statements can be found on the Activiti downloads page or inside the Activiti distribution folder, in the `database` subdirectory. The scripts are also in the engine jar (*activiti-engine-x.jar*), in the package *org/activiti/db/create* (the *drop* folder contains the drop statements). The SQL files are of the form

```
activiti.{db}.{create|drop}.{type}.sql
```



## 如何让Activiti支持DM7

https://weibo.com/ttarticle/p/show?id=2309404124388625636846#_0

## activiti6 表结构总结

表结构查看：https://www.devdoc.cn/activiti-table-act_re_model.html

方便：https://blog.csdn.net/cobrayangxiaoping/article/details/88691703?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-2&spm=1001.2101.3001.4242#t4

```
Activiti的后台是有数据库的支持，所有的表都以ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的API对应。

ACT_RE_*: 'RE’表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。

ACT_RU_*: 'RU’表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。

ACT_ID_*: 'ID’表示identity。 这些表包含身份信息，比如用户，组等等。

ACT_HI_*: 'HI’表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。

ACT_GE_*: 通用数据， 用于不同场景下，如存放资源文件。
```

### 详细说明：

```
act_evt_log  日志信息
```



```
act_id_user  用户信息   

act_id_info 账户信息/用户扩展信息

act_id_group 用户组信息

act_id_membership 用户与用户组关系（多对多）

PS:这四张表很常见，基本的组织机构管理，关于用户认证方面建议还是自己开发一套，组件自带的功能太简单，使用中有很多需求难以满足
```



```
act_ge_bytearray 通用流程信息（里面有流程图片、二进制数据、xml信息）

act_ge_property 通用属性信息（没有这个，流程引擎会初始化为null）
```



```
act_re_deployment流程部署信息

act_re_procdef 流程定义信息

act_re_model 流程对象信息
```



```
act_ru_identitylink 运行时流程人员关系表（流程定义与用户，流程定义与用户组）

act_ru_task 运行时任务信息（任务执行人、状态、父节点、对应的流程定义ID等信息,与其对应的实体类为TaskEntityImpl）- taskservice操作

act_ru_execution  运行时流程执行实例表（保存流程实例（ProcessInstance）和执行流（Exection）的数据，与其对应的实体类为ExecutionEntityImpl）

act_ru_variable  运行时流程变量数据表（activiti中的各种参数均保存在该表）

act_ru_event_subscr 运行时事件信息

act_ru_job 一般工作任务表（异步工作或者定时工作等信息存储，一般工作表，对应JobQuery）-managementservice操作

act_ru_timer_job 定时器工作表（对应TimerJobQuery）-managementservice操作

act_ru_suspended_job 中断工作表（对应SuspendedJobQuery）-managementservice操作

act_ru_deadletter_job 无法执行工作表（对应DeadLetterJobQuery）-managementservice操作
```



```
act_hi_attachment  历史附件表（保存任务附件数据，与其对应的实体类为AttachmentEntityImpl）- taskservice操作

act_hi_comment  历史意见表（保存两种类型数据：任务评论  和 部分事件记录，与其对应的实体类为CommentEntityImpl）- taskservice操作

act_hi_procinst  历史流程实例表（historyservice.createHistoricProcessInstanceQuery()得到HistoricProcessInstanceQuery对象查询流程实例的历史数据）-historyservice操作

act_hi_taskinst   历史任务实例表（historyservice.createHistoricTaskInstanceQuery()得到HistoricTaskInstanceQuery对象查询任务的历史数据）-historyservice操作

act_hi_actinst  历史节点/行为表（当流程进行到一个节点时，该数据表会记录流程节点的信息，包括节点的id、节点名称、节点类型和操作时间等）-historyservice操作

act_hi_detail   历史详情表，提供历史变量的查询 -historyservice操作

act_hi_varinst   历史变量表

act_hi_identitylink  历史流程人员表
```

# Springboot2.0 项目中TKmybatis和Activiti集成的问题

https://blog.csdn.net/jiaoshaoping/article/details/80612637



# Spring Data JPA 连接达梦数据库配置

https://blog.csdn.net/Qiyeye/article/details/109491326



# spring-data-jpa 连接达梦数据库（DM）文档

https://blog.csdn.net/wllpeter/article/details/79486426