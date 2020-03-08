package com.wyz.coffee.service;

import com.wyz.coffee.entity.Product;
import com.wyz.coffee.entity.ProductSort;
import com.wyz.coffee.http.bean.dto.Pagination;

import java.util.List;

public interface ProductService {
    Pagination<Product> getPage(int pageNum, int pageSize, String name);

    List<Product> getProductBySortId(Integer id);

    Pagination<ProductSort> getSort(int pageNum, int pageSize, String name);

    List<Product> getByName(String name);

    int insert(Product product);
    int update(Product product);
    int delete(Integer id);
    Product getProductById(Integer id);

    List<ProductSort> getAllSort();
    int insertSort(ProductSort productSort);
    int updateSort(ProductSort productSort);
    int deleteSort(Integer id);
}
