<!-- TOC -->autoauto- [mybatis](#mybatis)auto    - [网址](#网址)auto    - [sql拼接#和$的区别](#sql拼接和的区别)auto    - [使用sf4j](#使用sf4j)auto    - [缓存](#缓存)auto    - [缓存介绍](#缓存介绍)auto    - [逆向工程](#逆向工程)autoauto<!-- /TOC -->
# mybatis
## 网址
https://mybatis.org/mybatis-3/

https://github.com/mybatis

## sql拼接#和$的区别
* #{}:是以预编译的形式，将参数设置到sql语句中；PreparedStatement；防止sql注入
* ${}:取出的值直接拼装在sql语句中；会有安全问题；

大多情况下，我们去参数的值都应该去使用#{}；

## 使用sf4j
idea需要安装lombok插件

## 缓存
## 缓存介绍
![cach](img/mybatis-cach.jpg)

先查二级缓存在查一级缓存

## 逆向工程

[mybatis逆向工程开发文档](http://mybatis.org/generator/configreference/xmlconfig.html "点击跳转")

[mybatis逆向工程的DEMO的Github地址](https://github.com/mybatis/generator "点击跳转")

[测试Demo-Github地址](https://github.com/mybatis/generator "点击跳转")

### 参考配置

[参考配置generatorConfig.xml](sources/generatorConfig.xml)

参数说明：https://www.cnblogs.com/personsiglewine/p/12867465.html

### 1.代码实现方式

```xml
<!--逆向工程代码实现依赖-->
		<dependency>
			<groupId>org.mybatis.generator</groupId>
			<artifactId>mybatis-generator-core</artifactId>
			<version>1.3.7</version>
		</dependency>
```

```java
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {

    public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        File configFile = new File("D:\\project\\水阳江\\syj-fetc\\fetc-ams\\src\\main\\resources\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        try {
            MybatisGenerator generatorSqlmap = new MybatisGenerator();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 2.插件实现（IDEA）

pom文件中导入插件

```xml
 <!-- 代码生成 -->
<plugin>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-maven-plugin</artifactId>
	<version>1.3.2</version>
	<configuration>
		<verbose>true</verbose>
		<overwrite>true</overwrite>
	</configuration>
</plugin>
```

配置运行

![配置参数](img\mg01.png)

点击运行即可

mybatis-generator:generate -e

### FAQ

1.代码不生成，无报错日志

![](img\MG02.png)



# mybatis-generator自定义注释生成

https://blog.csdn.net/qq_21251983/article/details/50731368

https://blog.csdn.net/qigc_0529/article/details/80704330



# 基于MybatisGenerator扩展的 Model & Mapper & Service & Controller代码生成器

https://blog.csdn.net/fenglibing/article/details/83384337?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=4a06a2ad-8ee6-431c-a2ca-c4d8a9c27091&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control

https://github.com/fenglibin/CodeGenerator

```
使用说明
运行

进入到 src/test/java 目录下
找到CodeGeneratorMain类 为生成器的启动项
直接 Run As Java Application 运行即可

修改配置

进入到 src/test/resources 目录下
找到 generatorConfig.properties 文件
修改对应的参数即可
具体的注释信息可参考 /src/test/java/com/codegen/service/CodeGeneratorConfig.java 类

自定义生成器

参考目录src/test/java/com/codegen/generator下面相关的实现

模板样式修改

如果需要生成自己所需的 Controller & Service 样式
进入到 src/test/resources/generator/template 目录下
修改对应的ftl文件即可

数据库表名规则

表名支持单个单词的表名，如”table1“，已经根据下划线分隔的表名如“my_table1”，其它的没有测试。
```



## MyBatis中的collection两种使用方法

https://blog.csdn.net/lzxomg/article/details/89739651?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=e64c766f-ab2b-4701-bd46-95fbf10f3a1a&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.control