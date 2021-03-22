# jdk使用指南

## jdk 旧版本下载（jdk1.8.0_11）

https://blog.csdn.net/qq_36801585/article/details/107509183

## Oracle如何对JDK收费

https://zhuanlan.zhihu.com/p/64731331

## OpenJDK

http://jdk.java.net/archive/

**AdoptOpenJDK**

官网地址：[http://adoptopenjdk.net](https://link.zhihu.com/?target=http%3A//adoptopenjdk.net)

下载地址：https://mirrors4.tuna.tsinghua.edu.cn/help/adoptopenjdk/

但是还有很多优秀的基于OpenJDK的版本，通通不收费。其中很多都有给出LTS版本，像是[Adopt OpenJDK](https://link.zhihu.com/?target=https%3A//adoptopenjdk.net/)，[Red Hat OpenJDK](https://link.zhihu.com/?target=https%3A//developers.redhat.com/products/openjdk/download)，[Zulu OpenJDK](https://link.zhihu.com/?target=https%3A//www.azul.com/downloads/zulu-community/%3Fversion%3Djava-11-lts%26architecture%3Dx86-64-bit%26package%3Djdk)，[Alibaba Dragonwell](https://link.zhihu.com/?target=https%3A//www.aliyun.com/product/dragonwell)，[Amazon Corretto](https://link.zhihu.com/?target=https%3A//aws.amazon.com/cn/corretto/)。

**一点一点看JDK源码**

# JDK8的源码在哪儿可以下载？

<<<<<<< HEAD
# Java8 lambda 的使用

https://blog.csdn.net/bibiboyx/article/details/84257741

# java8 lambda表达式

https://www.cnblogs.com/kingsonfu/p/11047116.html

https://objcoding.com/2019/03/04/lambda/

# [JVM教程与调优] 了解JVM 堆内存溢出以及非堆内存溢出

https://bbs.huaweicloud.com/blogs/157230
=======
https://www.cnblogs.com/liuyuhangCastle/p/9580732.html

下载 JDK 之后，安装，然后安装的目录下有个 src.zip —— 这就是源码

这个还真难google到。

如果是OpenJDK的源码，而不是Oracle的，这里有，**包括JVM的**：
http://hg.openjdk.java.net/openjfx/8/master/rt

具体可参考：
https://stackoverflow.com/questions/19045831/where-can-i-find-the-jdk-8-javafx-8-source-code

Oracle的还真没有。

如果你只需要**Java的部分**，即src.zip，有些版本的Windows安装包里是没有的（可能是打包时忘加了吧），下个Linux的安装包，把src.zip取出来就可以了。



# [openjdk的源码下载方式](https://www.cnblogs.com/geektcp/p/10589457.html)

官方地址是：

http://hg.openjdk.java.net/

 

jdk8的下载地址：

http://hg.openjdk.java.net/jdk8/jdk8/hotspot/

点击zip即可开始下载，实际下载地址（以jdk8为例）：

http://hg.openjdk.java.net/jdk8/jdk8/hotspot/archive/tip.zip 

注意选择的路径不是jdk，而是hotspot

http://hg.openjdk.java.net/jdk8/jdk8/hotspot/

因为jdk的java develop kit即java开发工具包。hotspot才是真正的jvm即java virtual machine虚拟机。

目前至少从jdk1.7开始，虚拟机都是用的是hotspot。所有jdk的底层实现都是通过hotspot做到。

所以要研究jdk源码要下载hotspot。



https://www.cnblogs.com/linzhanfly/p/9474173.html



https://www.cnblogs.com/linzhanfly/p/9474173.html（重要）

# java反射之Method的invoke方法实现

https://blog.csdn.net/wenyuan65/article/details/81145900





List遍历

```
for (ZhsfWtlElwnIndex indexBean : indexList) {
    List<ZhsfWtlElwnValue> values = indexBean.getTableData();
    if (StringUtils.isNotEmpty(values)) {
        String collect = values.stream().map(r -> "" + r.getAlarmVl() + "").collect(Collectors.joining(","));
        indexBean.setElWarnValues(collect);
    }
}
```



```
/**
 * 实时预警信息和时段数据数据进行拼接
 *
 * @param stPptnRVoList
 * @param timeItvRainVoList
 * @return
 */
public List<StPptnRVo> getStPptnRVoRainResultList(List<StPptnRVo> stPptnRVoList, List<StTimeItvRainVo> timeItvRainVoList) {
    List<StPptnRVo> commonList = stPptnRVoList.stream()
            .map((uA) -> {
                return timeItvRainVoList.stream()
                        .filter((uB) -> {
                            return StringUtils.isNotNull(uA.getStcd()) && uA.getStcd().equals(uB.getStcd());
                        })
                        .map((uB) -> {
                            uA.setStPptnRRealTimeDetail(uB);
                            return uA;
                        })
                        .collect(Collectors.toList());
            }) // 结果类型 Steam<List<StPptnRVo>>
            .flatMap(List::stream) // 结果类型 Steam<StPptnRVo>
            .collect(Collectors.toList()); // 结果类型 List<StPptnRVo>
    return commonList;
}
```
>>>>>>> 9bc8ed7790ff953b7a6f70cedb3efb6bcaceccce
