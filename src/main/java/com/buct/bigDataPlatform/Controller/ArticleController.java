package com.buct.bigDataPlatform.Controller;

import com.buct.bigDataPlatform.Pojo.Article;
import com.buct.bigDataPlatform.Pojo.Category;
import com.buct.bigDataPlatform.Pojo.PageBean;
import com.buct.bigDataPlatform.Service.ArticleService;
import com.buct.bigDataPlatform.Util.JwtUtil;
import com.buct.bigDataPlatform.Pojo.Enums.ResultStatus;
import com.buct.bigDataPlatform.Pojo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public Result<String> list(){
        return Result.OK("我现在什么都不缺了");
    }

    @PostMapping
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.OK();
    }
    @PutMapping
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.OK();
    }
    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id) {
        System.out.println("你好CONTROLLER");
        Article article = articleService.getById(id);
        return Result.OK(article);
    }
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,
                                          Integer pageSize,
                                          @RequestParam(required = false)String categoryld,
                                          @RequestParam(required = false)String state){
        PageBean<Article> pb=articleService.list(pageNum,pageSize,categoryld,state);
        return Result.OK(pb);
    }
}