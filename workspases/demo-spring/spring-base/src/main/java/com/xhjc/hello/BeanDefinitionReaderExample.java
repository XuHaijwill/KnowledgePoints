package com.xhjc.hello;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.Arrays;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/2/8 19:21
 **/
public class BeanDefinitionReaderExample {

    public static void main(String[] args) {
        // 注册 中心
        BeanDefinitionRegistry register = new SimpleBeanDefinitionRegistry();
        // 读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(register);
        // 资源读取器
        DefaultResourceLoader loder = new DefaultResourceLoader();
        Resource resource = loder.getResource("spring.xml");
        // 装载构建Bean的定义
        reader.loadBeanDefinitions(resource);
        register.getAliases("hey");
        register.getBeanDefinition("hey");
        System.out.println(Arrays.toString(register.getBeanDefinitionNames()));
    }
}
