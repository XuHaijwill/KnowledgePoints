package com.xhjc;

import static org.junit.Assert.assertTrue;

import cn.enjoy.ProductApp;
import cn.enjoy.service.IProductService;
import cn.enjoy.vo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */

@SpringBootTest(classes = ProductApp.class)
@RunWith(SpringRunner.class)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Resource
    private IProductService iProductService;
    @Test
    public void testGet() {
        System.out.println(iProductService.get(1));
    }
    @Test
    public void testAdd() {
        Product dept = new Product() ;
        dept.setProductName("lison-" + System.currentTimeMillis());
        System.out.println(iProductService.add(dept));
    }
    @Test
    public void testList() {
        System.out.println(iProductService.list());
    }
}
