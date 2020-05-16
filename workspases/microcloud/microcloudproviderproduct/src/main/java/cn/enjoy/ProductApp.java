package cn.enjoy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-15 17:58
 **/
@SpringBootApplication
@MapperScan("cn.enjoy.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
public class ProductApp{
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class,args);
    }
}