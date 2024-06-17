package com.buct.bigDataPlatform.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buct.bigDataPlatform.Pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 14:43
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
