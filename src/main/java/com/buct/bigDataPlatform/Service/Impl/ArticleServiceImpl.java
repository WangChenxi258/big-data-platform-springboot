package com.buct.bigDataPlatform.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.bigDataPlatform.Mapper.ArticleMapper;
import com.buct.bigDataPlatform.Pojo.Article;
import com.buct.bigDataPlatform.Pojo.PageBean;
import com.buct.bigDataPlatform.Service.ArticleService;
import com.buct.bigDataPlatform.Util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.extension.toolkit.Db.page;

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

        article.setCreateUser(id);

        articleMapper.insert(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        IPage<Article> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user", id);
        if(categoryId!=null){
            queryWrapper.eq("category_id", categoryId);
        }
        if(state!=null){
            queryWrapper.eq("state", state);
        }

        IPage<Article> articlePage = articleMapper.selectPage(page,queryWrapper);
        pageBean.setItems(articlePage.getRecords());
        pageBean.setTotal(articlePage.getTotal());
        return pageBean;
    }
}