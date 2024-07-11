package com.buct.bigDataPlatform.Service;

import com.buct.bigDataPlatform.Pojo.Article;
import com.buct.bigDataPlatform.Pojo.PageBean;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 14:42
 */
public interface ArticleService{
    void add(Article article);

    PageBean list(Integer pageNum, Integer pageSize, String categoryld, String state);

    void update(Article article);

    Article getById(Integer id);
}
