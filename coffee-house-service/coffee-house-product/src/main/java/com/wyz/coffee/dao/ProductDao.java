package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.ProductMapper;
import com.wyz.coffee.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:44
 **/
@Repository
public interface ProductDao extends ProductMapper {
    List<Product> selectByName(String name);
    List<Product> selectBySortId(Integer sortId);
}
