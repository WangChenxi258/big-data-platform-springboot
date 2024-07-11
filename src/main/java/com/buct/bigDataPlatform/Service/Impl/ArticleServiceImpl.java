package com.buct.bigDataPlatform.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buct.bigDataPlatform.Mapper.ArticleMapper;
import com.buct.bigDataPlatform.Pojo.Article;
import com.buct.bigDataPlatform.Pojo.Category;
import com.buct.bigDataPlatform.Pojo.PageBean;
import com.buct.bigDataPlatform.Pojo.User;
import com.buct.bigDataPlatform.Service.ArticleService;
import com.buct.bigDataPlatform.Util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 14:43
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        article.setCreateuser(id);

        articleMapper.insert(article);
    }

    @Override
    public PageBean list(Integer pageNum, Integer pageSize, String categoryld, String state) {
        //创建
        PageBean<Article> pb =new PageBean();

        return null;
    }

    @Override
    public void update(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        System.out.println("更新！！！！！！！！！！！！");
        Article update_article = new Article();
        update_article.setTitle(article.getTitle());
        update_article.setContent(article.getContent());
        update_article.setCoverimg(article.getCoverimg());
        update_article.setState(article.getState());
//        update_article.setCategoryid(article.getCategoryid());
        update_article.setCreateuser(id);
        System.out.println(update_article);
        System.out.println(article);
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("id",article.getId());
        articleMapper.update(update_article,updateWrapper);


    }

    @Override
    public Article getById(Integer id) {
        System.out.println("你好");
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return articleMapper.selectOne(queryWrapper);
    }
}