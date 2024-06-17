package com.buct.bigDataPlatform.Controller;

import com.buct.bigDataPlatform.Pojo.Article;
import com.buct.bigDataPlatform.Pojo.PageBean;
import com.buct.bigDataPlatform.Service.ArticleService;
import com.buct.bigDataPlatform.Util.JwtUtil;
import com.buct.bigDataPlatform.Pojo.Enums.ResultStatus;
import com.buct.bigDataPlatform.Pojo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-20 15:51
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * @param article:
     * @Description: add
     * @Author: CarLor
     * @Date: 16:54 2024/6/12 0012
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result
     **/
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.OK();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.OK(pb);
    }
}