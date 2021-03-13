# Nginx使用指南

## Nginx负载均衡检测节点状态

https://www.linuxgogo.com/505.html

# Nginx负载均衡中后端节点服务器健康检查 - 运维笔记

nginx_upstream_check_module

https://www.cnblogs.com/kevingrace/p/6685698.html

# NGINX 负载均衡监测节点状态 之 十一

https://blog.51cto.com/12965094/2148732

```

http {
    upstream fire_server{
    ip_hash;
    server 192.168.1.1:80;
    server 192.168.1.2:80;
 
    check interval=3000 rise=2 fall=5 timeout=1000 type=http ;
    check_http_send "GET /status.html HTTP/1.1\r\nHost: 127.0.0.1\r\n\r\n";
    check_http_expect_alive http_2xx http_3xx ;
    }
 
    server {
        listen       80;
        server_name  localhost default;
 
        location / {
            proxy_pass http://fire_server;
            access_log logs/fire_server_access.log main;
            error_log logs/error.log debug;
        }
 
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }   
}
```



- check interval 指令可以打开后端服务器的健康检查功能。

```

指令后面的参数意义是：
 
interval：向后端发送的健康检查包的间隔。
fall(fall_count): 如果连续失败次数达到fall_count，服务器就被认为是down。
rise(rise_count): 如果连续成功次数达到rise_count，服务器就被认为是up。
timeout: 后端健康请求的超时时间。
default_down: 设定初始时服务器的状态，如果是true，就说明默认是down的，如果是false，就是up的。默认值是true，也就是一开始服务器认为是不可用，要等健康检查包达到一定成功次数以后才会被认为是健康的。
type：健康检查包的类型，现在支持以下多种类型
    tcp：简单的tcp连接，如果连接成功，就说明后端正常。
    ssl_hello：发送一个初始的SSL hello包并接受服务器的SSL hello包。
    http：发送HTTP请求，通过后端的回复包的状态来判断后端是否存活。
    mysql: 向mysql服务器连接，通过接收服务器的greeting包来判断后端是否存活。
    ajp：向后端发送AJP协议的Cping包，通过接收Cpong包来判断后端是否存活。
port: 指定后端服务器的检查端口。
```

- check_http_expect_alive 指令

```
check_http_expect_alive [ http_2xx | http_3xx | http_4xx | http_5xx ]



返回指定HTTP code，符合预期就算检测成功
```

### RealServer配置

```
        location = /status.html {



            root html;



            access_log logs/access.log main;



        }
```

后端realserver配置，只需要保证 curl [http://realserver_ip/status.html](https://blog.csdn.net/status.html) 能访问到即可。

# Nginx使用nginx_upstream_check_module模块实现后端节点健康检查功能

https://www.jianshu.com/p/bc2c64ab8276

# Windows版Nginx配置实现不停服更新Tomcat

https://blog.csdn.net/qq_24054301/article/details/112156013



# 编译Windows版Nginx并添加模块

https://blog.csdn.net/qq_24054301/article/details/112131057



# nginx安装第三方模块nginx_upstream_check_module

https://blog.csdn.net/pcn01/article/details/105182600?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&dist_request_id=9999a655-1958-47a4-9002-bbbeab0576fd&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control

