# mysql使用指南

## mysql主从同步

文档：[centos7安装mysql(完整).note](http://note.youdao.com/noteshare?id=db7f96c202861ac2e580e0daf15f3a92&sub=27C808D98F554CB493A7C4BD2F37DB81)

参考：https://www.jianshu.com/p/c80712f5aad9

### 参数讲解

参考：https://blog.csdn.net/z69183787/article/details/70183284

编辑主服务器的配置文件：/etc/my.cnf 

```txt
binlog_do_db是指定binlog日志记录那些库的二进制日志。replicate_do_db则在slave库中指定同步那些库的binlog日志。
在主从互备环境中，有没有必要每个服务器都同时配置binlog_do_db和replicate_do_db?理由是什么？
```

```txt
binlog_do_db是指定binlog日志记录那些库的二进制日志。replicate_do_db则在slave库中指定同步那些库的binlog日志。
在主从互备环境中，有没有必要每个服务器都同时配置binlog_do_db和replicate_do_db?理由是什么？
```

binlog-do-db：指定mysql的binlog日志记录哪个db

Replicate_Do_DB：参数是在slave上配置，指定slave要复制哪个库

在master上设置binlog_do_弊端：
1、过滤操作带来的负载都在master上
2、无法做基于时间点的复制（利用binlog）。

server-id=1
log-bin
[binlog-do-db](http://dev.mysql.com/doc/refman/5.0/en/replication-options-binary-log.html#option_mysqld_binlog-do-db)=需要复制的数据库名，如果复制多个数据库，重复设置这个选项即可
[binlog-ignore-db](http://dev.mysql.com/doc/refman/5.0/en/replication-options-binary-log.html#option_mysqld_binlog-ignore-db)=不需要复制的数据库苦命，如果复制多个数据库，重复设置这个选项即可

注意：如果你想做一个复杂点的结构：比如说，A->B->C，其中B是A的从服务器，同时B又是C的主服务器，那么B服务器除了需要打开log-bin之外，还需要打开[log-slave-updates](http://dev.mysql.com/doc/refman/5.0/en/replication-options-slave.html#option_mysqld_log-slave-updates)选项，你可以再B上使用“show variables like 'log%';”来确认是否已经生效。

 编辑从服务器的配置文件：/etc/my.cnf

server-id=2
master-host=主机
master-user=用户名
master-password=密码
master-port=端口
[replicate-do-db](http://dev.mysql.com/doc/refman/5.0/en/replication-options-slave.html#option_mysqld_replicate-do-db)=需要复制的数据库名，如果复制多个数据库，重复设置这个选项即可
[replicate-ignore-db](http://dev.mysql.com/doc/refman/5.0/en/replication-options-slave.html#option_mysqld_replicate-ignore-db)=需要复制的数据库名，如果复制多个数据库，重复设置这个选项即可

配置主从服务器的my.cnf时，留心各自的server-id一定要彼此独立，不能重复，否则，会出现如下错误：

Slave: received end packet FROM server, apparent master shutdown

另一个需要注意的是最好在从服务器的my.cnf里设置[read_only](http://dev.mysql.com/doc/refman/5.0/en/server-system-variables.html#sysvar_read_only)选项，防止发生意外（连接用户不能有SUPER权限，否则无效）。

记得先手动同步一下主从服务器，数据量小的话可以用mysqldump，它有一个[master-data](http://dev.mysql.com/doc/refman/5.0/en/mysqldump.html#option_mysqldump_master-data)参数很有用，通过使用此参数，导出的SQL文件里会自动包含CHANGE MASTER TO MASTER_LOG_FILE='...', MASTER_LOG_POS=...;，这样创建从服务器就更方便了。

