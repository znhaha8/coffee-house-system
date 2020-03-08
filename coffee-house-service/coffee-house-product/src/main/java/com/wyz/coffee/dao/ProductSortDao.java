package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.ProductSortMapper;
import com.wyz.coffee.entity.ProductSort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/5 10:20
 **/
@Repository
public interface ProductSortDao extends ProductSortMapper {
    List<ProductSort> selectByName(String name);
    List<ProductSort> selectAll();

}
