package com.buct.bigDataPlatform.Service;

import com.buct.bigDataPlatform.Pojo.Category;

import java.util.List;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 10:59
 */
public interface CategoryService {
    //新增分类
    void add(Category category);
    //列表查询
    List<Category> list();
    //删除分类
    void delete(Integer categoryId);
    //根据Id查询分类
    Category getById(Integer id);
    void update(Category category);
}
