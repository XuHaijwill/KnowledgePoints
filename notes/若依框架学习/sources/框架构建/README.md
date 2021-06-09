# NACOS

https://nacos.io/zh-cn/

![](imgs\nacos001.png)

中水三立电脑：

D:\workspaces\springcloud\docs

## nacos修改默认的用户名密码

nacos修改默认的用户名密码
首先要先安装mysql

我选择的mysql5.7
安装后
登录 mysql -u root -p
查询所有用户select Host,User from mysql.user;
添加新用户，授予远程访问权限GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' IDENTIFIED BY 'secret' WITH GRANT OPTION;
然后根据nacos提供的初始化sql,导入sql,会生成几个表,其中users是用户表
nacos配置文件修改(以下是nacos官方文档提供)

```
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://11.162.196.16:3306/nacos_devtest?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=nacos_devtest
db.password=youdontknow
```



启动 nacos,单机模式 ./startup.sh -m standalone &
修改密码

```
生成nacos密码
需要先引入依赖
<dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
</dependency>
然后执行下边这个代码
 new BCryptPasswordEncoder().encode("123456");

使用的数据库为 nacos， 用户表 users
修改用户账号密码就是替换users表的账户密码信息
```





