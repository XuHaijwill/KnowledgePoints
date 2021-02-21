# Spring Cloud GateWay网关集群搭建

![img](https://csdnimg.cn/release/blogv2/dist/pc/img/original.png)

[Cinderella100100100](https://blog.csdn.net/qq_41250616) 2020-08-13 13:49:54 ![img](https://csdnimg.cn/release/blogv2/dist/pc/img/articleReadEyes.png) 2263 ![img](https://csdnimg.cn/release/blogv2/dist/pc/img/tobarCollect.png) 收藏 9

文章标签： [nginx](https://www.csdn.net/tags/MtTaEg0sMjAyNDgtYmxvZwO0O0OO0O0O.html) [spring boot](https://so.csdn.net/so/search/s.do?q=spring boot&t=blog&o=vip&s=&l=&f=&viparticle=)

版权

## Spring Cloud GateWay网关集群搭建

1.环境
nginx: 1.19.0

nacos: 1.3.1

openjdk: 1.8.0_181

nacos集群：192.168.8.81

```
                192.168.8.82

                192.168.8.83
123
```

2.实现网关注册nacos中心
1）配置依赖pom.xml

- 因为是搭建网关集群，每一个网关应用使用的依赖都是一致的

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134523315.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

2)修改配置文件

配置网关服务gatewaya的nacos集群注册中心地址、端口号

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134529758.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134537271.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

配置网关服务gatewayb的nacos集群注册中心地址、端口号
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134543334.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134550758.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

3.实现网关转发至服务
1）配置路由

配置网关服务gatewaya支持跨域访问及连接超时时间

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134558471.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

配置网关服务gatewaya的路由转发到在注册中心已注册服务nacos-provider
![在这里插入图片描述](https://img-blog.csdnimg.cn/202008131346138.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

配置网关服务gatewayb支持跨域访问及连接超时时间

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134621470.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

配置网关服务gatewaya的路由转发到在注册中心已注册服务nacos-consumer

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134627442.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

4.nacos-discovery实现数据访问
1）nacos-provider添加访问路径

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134636176.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

2）nacos-consumer添加访问路径
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134700421.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134706540.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134714599.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

5.nginx实现负载均衡
1）安装nginx

yum install nginx

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134720525.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

2）修改配置文件

修改nginx.conf文件 ，配置网关集群的负载均衡

cd /usr/local/nginx/conf

vim nginx.conf
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134727341.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

\#gzip on;
upstream gateways {
server 192.168.6.29:9527 weight=1;
server 192.168.6.29:9528 weight=1;
}

server {
listen 80;
server_name 192.168.6.29;
charset utf8;

\#charset koi8-r;

\#access_log logs/host.access.log main;

location / {
\#root html;
\#index index.html index.htm;
proxy_pass http://gateways;
proxy_set_header Host $host;
proxy_set_header X-Real-IP $remote_addr;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
}

6.测试网关集群
启动nacos-provider、nacos-consumer、gatewaya、gatewayb应用，并进入nacos注册中心查询服务列表

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020081313474062.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMjUwNjE2,size_16,color_FFFFFF,t_70#pic_center)

测试gatewaya设置的路由test_a、test_c和gatewayb设置的路由test_a，浏览器访问http://192.168.8.81/hello ，转发到gatewaya时页面返回Hello Gateway A！字符串，转发到gatewayb时页面返回字符串Hello Gateway B！;

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134908784.gif#pic_center)

测试gatewayb设置的路由test_b,浏览器访问http://192.168.8.81/hello-rest/ayay,转发到gatewaya时返回404页面，转发到gatewayb时页面返回字符串ayay;

测试gatewayb设置的路由test_c，浏览器访问http://192.168.8.81/hello-feign/heihei 转发到gatewaya时返回404页面，转发到gatewayb时页面返回字符串heihei;
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200813134929656.gif#pic_center)

- 此处的原理是nginx监听端口80，监听到访问后将请求转发给gatewaya和gatewayb，权重weight分别设置为1，即两次访问，gatewaya和gatewayb分别会有50%的等概率会被转发到，接着gatewaya和gatewayb根据访问路径匹配相应的路由，gatewaya和gatewayb分别配置了三个路由，都会分别获取到注册的服务nacos-provider和nacos-consumer，通过路径转到注册服务相应的访问路径获取数据