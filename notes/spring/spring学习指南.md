# spring学习指南

## Spring中BeanFactory与FactoryBean的区别

- 他们两个都是个工厂，但`FactoryBean`本质上还是一个Bean，也归`BeanFactory`管理

- `BeanFactory`是Spring容器的顶层接口，`FactoryBean`更类似于用户自定义的工厂接口

- BeanFactory是个Factory，也就是IOC容器或对象工厂，FactoryBean是个Bean。在Spring中，**所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的**。但对FactoryBean而言，**这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,它的实现与设计模式中的工厂模式和修饰器模式类似** 

  参考：https://juejin.cn/post/6844903967600836621，https://www.cnblogs.com/aspirant/p/9082858.html

### Bean的作用域和生命周期

创建-->初始化-->销毁

通过@Scope指定bean的实例类型

![](imgs\bean01.png)

#### spring中的缓存：DefaultSingletonBeanRegistry中存储bean的不同map/set、

​		org.springframework.beans.factory.support.DefaultSingletonBeanRegistry这个类当中有四个缓存

- singletonObjects(ConcurrentHashMap)

​       保存BeanName和创建的bean实例之间的关系，bean name->bean instance。

- singletonFactories(HashMap)

  保存BeanName和创建bean的工厂之间的关系，bean name -> ObjectsFatory

- earlySingletonObjects(HashMap)

  也是保存BeanName和创建bean实例之间的关系，与singletonObjects之间的区别在于，当一个单例bean被放到earlySingletonObjects里面之后，该bean就可以通过getBean()方法获取到了（虽然只是早期对象，即还在创建过程中。目的是解决循环依赖的问题）

- registeredSingletons(LinkedHashSet)

  保存当前所有已注册的bean，这里为什么要用LinkedHashSet？

## ComponetScan 源码分析

![扫描分析](imgs/cp-01.png)

![扫描分析](imgs/cp-08.png)