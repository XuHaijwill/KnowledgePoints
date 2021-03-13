# spring学习指南

## Spring中BeanFactory与FactoryBean的区别

- 他们两个都是个工厂，但`FactoryBean`本质上还是一个Bean，也归`BeanFactory`管理

- `BeanFactory`是Spring容器的顶层接口，`FactoryBean`更类似于用户自定义的工厂接口

- BeanFactory是个Factory，也就是IOC容器或对象工厂，FactoryBean是个Bean。在Spring中，**所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的**。但对FactoryBean而言，**这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,它的实现与设计模式中的工厂模式和修饰器模式类似** 

  参考：https://juejin.cn/post/6844903967600836621，https://www.cnblogs.com/aspirant/p/9082858.html

## Bean的作用域和生命周期

创建-->初始化-->销毁

### 通过@Scope指定bean的实例类型

![](imgs\bean01.png)

### 给容器中注册组件的方式

```
1,@Bean: [导入第三方的类或包的组件],比如Person为第三方的类, 需要在我们的IOC容器中使用
2,包扫描+组件的标注注解(@ComponentScan:  @Controller, @Service  @Reponsitory  @ Componet),一般是针对 我们自己写的类,使用这个
3,@Import:[快速给容器导入一个组件] 注意:@Bean有点简单
	a,@Import(要导入到容器中的组件):容器会自动注册这个组件,bean 的 id为全类名
	b,ImportSelector:是一个接口,返回需要导入到容器的组件的全类名数组
	c,ImportBeanDefinitionRegistrar:可以手动添加组件到IOC容器, 所有Bean的注册可以使用BeanDifinitionRegistry
	写JamesImportBeanDefinitionRegistrar实现ImportBeanDefinitionRegistrar接口即可
4,使用Spring提供的FactoryBean(工厂bean)进行注册
```

### spring中的缓存：DefaultSingletonBeanRegistry

spring中的缓存：DefaultSingletonBeanRegistry中存储bean的不同map/set、

org.springframework.beans.factory.support.DefaultSingletonBeanRegistry这个类当中有四个缓存

- singletonObjects(ConcurrentHashMap)

​       保存BeanName和创建的bean实例之间的关系，bean name->bean instance。

- singletonFactories(HashMap)

  保存BeanName和创建bean的工厂之间的关系，bean name -> ObjectsFatory

- earlySingletonObjects(HashMap)

  也是保存BeanName和创建bean实例之间的关系，与singletonObjects之间的区别在于，当一个单例bean被放到earlySingletonObjects里面之后，该bean就可以通过getBean()方法获取到了（虽然只是早期对象，即还在创建过程中。目的是解决循环依赖的问题）

- registeredSingletons(LinkedHashSet)

  保存当前所有已注册的bean，这里为什么要用LinkedHashSet？



### 常用注解

-  **@Conditional条件注册bean**

  ```java
      @Conditional(WinCondition.class)
  	@Bean("lison")
  	public Person lison(){
  		System.out.println("给容器中添加lison.......");
  		return new Person("Lison",58);
  	}
  	
  	@Conditional(LinCondition.class)
  	@Bean("james")//bean在容器中的ID为james, IOC容器MAP,  map.put("id",value)
  	public Person james(){
  		System.out.println("给容器中添加james.......");
  		return new Person("james",20);
  	}
  ```

-  **@Import注册bean**

- **@Value**

- **@Autowired** 自动装配:spring利用依赖注入(DI), 完成对IOC容器中的各个组件的依赖关系赋值

- **@Resource**
  - @Resource和Autowired一样可以装配bean
  - @Resource缺点: 不能支持@Primary功能
    - 不能支持@Autowired(required = false)的功能

- **@Inject**
  - @Inject和Autowired一样可以装配bean, 并支持@Primary功能, 可用于非spring框架.
  - @Inject缺点: 但不能支持@Autowired(required = false)的功能,需要引入第三方包javax.inject 

```
<dependency>
    <groupId>javax.inject</groupId>
	<artifactId>javax.inject</artifactId>
	<version>1</version>
</dependency>
```

## AOP源码分析

## ComponetScan 源码分析

![扫描分析](imgs/cp-01.png)

![扫描分析](imgs/cp-08.png)



## spring面试问题

- spring的循环依赖如何解决（https://zhuanlan.zhihu.com/p/84267654）

## spring学习资源

https://javadoop.com/

学习路径

https://www.processon.com/view/link/5cb6c8a4e4b059e209fbf369#outline


## 实际问题

### SpringBoot指定额外需要扫描的包

```
我们都知道，SpringBoot主启动类标注了@SpringBootApplication注解，该注解引入了@ComponentScan注解
所以默认的包扫描规则是，程序会自动扫描主启动类所在包及其子包

但是在多模块项目开发中，有时候会遇到这样的需求：
需要将公共模块的一个组件加入IOC容器，但是其所在包又不在默认扫描范围内

解决办法两个：
方法1：将公共模块中的该组件放在默认扫描的包下（包名一样）

方法2：使用@ComponentScan额外指定待扫描的包，但是不能用在主启动类上，因为这样会覆盖掉默认的包扫描规则，可以在其他标注了@Configuration的地方配置@ComponentScan(basePackages = { "xxx.yyy"})进行额外指定，这样就能达到效果也不会覆盖默认的包扫描规则，亲试有效。
```

# spring源码阅读

## 本地环境构建

```
1、先来说下导入环境和工具：
    java版本：1.8
    idea： idea2020
    gradle：使用spring源码自带的配置即可，版本过高会引发各种gradle插件问题
2、gradle的安装
   
3、在idea2020中配置gradle
    如下图所示：
```

![](D:\sources\git\github\KnowledgePoints\notes\spring\imgs\spsource-001.png)

```
4、下载并且修改build.gradle
    先放github上spring源码的地址：https://github.com/spring-projects/spring-framework/tree/5.1.x (直接从github上进的话记得选择5.1分支)
       下载源码有两种：
        1、使用git clone从github上克隆(不推荐去gitee上下载所谓的镜像，我下过，和github上的代码不一致，应该是没更新的缘故)
        2、直接下载jar包
        这里推荐直接下载压缩包，个人感觉使用git clone有点慢。下载完成后解压缩即可。
    修改spring源码依赖的jar包的下载地址，打开根目录下的build.gradle
    第一处： 在文件的首行，修改后的配置如下：
       repositories {
             mavenLocal()
             maven { url "https://maven.aliyun.com/repository/spring-plugin" }
             maven{ url "https://maven.aliyun.com/nexus/content/repositories/spring-plugin"}
             maven { url "https://repo.spring.io/plugins-release" }
             mavenCentral()
            maven { url "https://plugins.gradle.org/m2/" }
            maven { url 'https://jetbrains.bintray.com/intellij-plugin-service' }
         }
    第二处：大概在第150行，修改后的配置如下：
        repositories {
             mavenLocal()
             maven { url "https://maven.aliyun.com/repository/spring-plugin" }
             maven{ url "https://maven.aliyun.com/nexus/content/repositories/spring-plugin"}
             maven { url "https://repo.spring.io/plugins-release" }
             mavenCentral()
            maven { url "https://plugins.gradle.org/m2/" }
            maven { url 'https://jetbrains.bintray.com/intellij-plugin-service' }
         }
5、打开idea2020，导入spring源码
    idea2020导入源码之后会自动进行build，这个时候会下载许多依赖包，时间比较久。
    中间有可能包gradle插件的相关问题，可以在网上找到对应的插件jar放入本地仓库中，解决相关报错问题。
```

## springmvc源码阅读

源码编译运行参考链接：

https://blog.csdn.net/java_lyvee/article/details/107300648?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242

https://www.cnblogs.com/bojuetech/p/5844413.html

build.gradle配置：

```
//plugins {
//    id 'java'
//}

group 'org.springframework'
version '5.0.20.BUILD-SNAPSHOT'

apply plugin: 'groovy'
apply plugin: 'java'
apply plugin: 'war'
apply plugin: 'idea'

repositories {
    mavenLocal()
    maven { url "https://maven.aliyun.com/repository/spring-plugin" }
    maven{ url "https://maven.aliyun.com/nexus/content/repositories/spring-plugin"}
    maven { url "https://repo.spring.io/plugins-release" }
    mavenCentral()
    maven {
        url "https://plugins.gradle.org/m2/"
    }
    maven {
        url 'https://jetbrains.bintray.com/intellij-plugin-service'
    }
}

dependencies {
    compile(project(":spring-webmvc"))
    // https://mvnrepository.com/artifact/javax.servlet/jstl
    implementation group: 'javax.servlet', name: 'jstl', version: '1.2'
    // https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
    compileOnly group: 'javax.servlet', name: 'javax.servlet-api', version: '3.1.0'
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

```



## 手写springmvc

# spring-mvc源码解析

https://juejin.cn/post/6844904017772937229#heading-18



# springboot

源码构建

https://blog.csdn.net/bskfnvjtlyzmv867/article/details/89434894

https://github.com/spring-projects/spring-boot/tree/v2.4.1