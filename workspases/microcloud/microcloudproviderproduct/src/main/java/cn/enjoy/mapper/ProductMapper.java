package cn.enjoy.mapper;

import cn.enjoy.vo.Product;

import java.util.List;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-15 18:02
 **/
public interface ProductMapper {
    boolean create(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
}
