# spring学习指南

## Spring中BeanFactory与FactoryBean的区别

- 他们两个都是个工厂，但`FactoryBean`本质上还是一个Bean，也归`BeanFactory`管理

- `BeanFactory`是Spring容器的顶层接口，`FactoryBean`更类似于用户自定义的工厂接口

- BeanFactory是个Factory，也就是IOC容器或对象工厂，FactoryBean是个Bean。在Spring中，**所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的**。但对FactoryBean而言，**这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,它的实现与设计模式中的工厂模式和修饰器模式类似** 

  参考：https://juejin.cn/post/6844903967600836621，https://www.cnblogs.com/aspirant/p/9082858.html

### Bean的作用域和生命周期



创建-->初始化-->销毁

## ComponetScan 源码分析

![扫描分析](imgs/cp-01.png)

![扫描分析](imgs/cp-08.png)