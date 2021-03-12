# 异步调用

## Java按时间梯度实现异步回调接口的方法

https://www.jb51.net/article/146511.htm

## java异步线程池同时请求多个接口数据

https://www.cnblogs.com/xiaoliao/p/7622448.html

## Java 一个接口需要调用几个无关联接口，怎么设计异步执行提高效率。

https://www.oschina.net/question/1186051_2244564



## 详解 @EnableAsync & @Async，主要分下面几个点进行介绍。

https://blog.csdn.net/likun557/article/details/107502663



## Java并发请求多个API的实践

https://blog.csdn.net/qq_39565575/article/details/97111935



## springboot利用@Async提升API接口并发能力

https://blog.csdn.net/u011585609/article/details/104872325



## Java 中并发请求多个接口怎样才能效率最高呢？

https://www.zhihu.com/question/382668099

(重要)

## springboot并发工具

https://github.com/lvyahui8/spring-boot-data-aggregator

https://www.cnblogs.com/idoljames/p/11418028.html





https://blog.csdn.net/u014089832/article/details/106980020/?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242

```
在项目中使用

<!--https://mvnrepository.com/artifact/io.github.lvyahui8/spring-boot-data-aggregator-starter -->
<dependency>
    <groupId>io.github.lvyahui8</groupId>
    <artifactId>spring-boot-data-aggregator-starter</artifactId>
    <version>1.1.3</version>
</dependency>

并在 application.properties 文件中声明注解的扫描路径.
# 替换成你需要扫描注解的包
io.github.lvyahui8.spring.base-packages = xx.xxxxxx.xxxxxx.xxxxxx.example
```

增加额外包扫描

```
@Configuration
@ComponentScan(basePackages = { "io.github.lvyahui8.spring"})
public class AggregatorConfig {
}
```





## springboot并发

https://www.v2ex.com/t/656411

https://blog.csdn.net/qq_21480329/article/details/105901901

```
Completable<Void> f = CompletableFuture.allOf(task1, task2, task3); // 这里注意要使用线程池
f.get(); // 这里消耗的时间是 task1 、task2 、task3 的最大值

Stream.of(future1, future2, future3).forEach(dd -> dd.thenAccept(e -> {
// 处理数据
}));

micean   348 天前
java 的 vertx 可以这么用

CompositeFuture.all(请求 1，请求 2...)
.compose(结果集 -> 处理结果集，返回最终结果)
.setHandler(成功时的处理最终结果，至少一项请求失败时处理异常)

和 java 自带的 CompletableFuture 相比，只用了 1 个线程，无阻塞。
```

https://blog.csdn.net/qq_21480329/article/details/105901901

