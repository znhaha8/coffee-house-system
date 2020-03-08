package com.wyz.coffee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyz.coffee.dao.ProductDao;
import com.wyz.coffee.dao.ProductSortDao;
import com.wyz.coffee.entity.Product;
import com.wyz.coffee.entity.ProductSort;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:54
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Lazy
    ProductDao productDao;
    @Autowired
    @Lazy
    ProductSortDao productSortDao;
    @Override
    public Pagination<Product> getPage(int pageNum, int pageSize, String name) {
        Page<Product> products = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> productDao.selectByName(name));
        Pagination<Product> res = new Pagination<>();
        BeanUtils.copyProperties(products, res);
        return res;
    }

    @Override
    public List<Product> getByName(String name) {
        return productDao.selectByName(name);
    }

    @Override
    public int insert(Product product) {
        return productDao.insert(product);
    }

    @Override
    public int update(Product product) {
        return productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public int delete(Integer id) {
        return productDao.deleteByPrimaryKey(id);
    }

    @Override
    public Product getProductById(Integer id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> getProductBySortId(Integer id) {
        return productDao.selectBySortId(id);
    }

    @Override
    public Pagination<ProductSort> getSort(int pageNum, int pageSize, String name) {
        Page<ProductSort> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> productSortDao.selectByName(name));
        Pagination<ProductSort> res = new Pagination<>();
        BeanUtils.copyProperties(page, res);
        return res;
    }

    @Override
    public List<ProductSort> getAllSort() {
        return productSortDao.selectAll();
    }

    @Override
    public int insertSort(ProductSort productSort) {
        return productSortDao.insert(productSort);
    }

    @Override
    public int updateSort(ProductSort productSort) {
        return productSortDao.updateByPrimaryKeySelective(productSort);
    }

    @Override
    public int deleteSort(Integer id) {
        return productSortDao.deleteByPrimaryKey(id);
    }


}
