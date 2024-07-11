package com.buct.bigDataPlatform.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buct.bigDataPlatform.Mapper.CategoryMapper;
import com.buct.bigDataPlatform.Pojo.Category;
import com.buct.bigDataPlatform.Service.CategoryService;
import com.buct.bigDataPlatform.Util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 10:59
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        category.setCreateuser(id);
        categoryMapper.insert(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("createuser", id);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public void delete(Integer categoryId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", categoryId)
                .eq("createuser", id);
        categoryMapper.delete(queryWrapper);
    }

    @Override
    public Category getById(Integer id) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public void update(Category category) {
        Category updateCategory = new Category();
        updateCategory.setCategoryname(category.getCategoryname());
        updateCategory.setCategoryalias(category.getCategoryalias());
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", category.getId());
        categoryMapper.update(updateCategory, updateWrapper);
    }
}