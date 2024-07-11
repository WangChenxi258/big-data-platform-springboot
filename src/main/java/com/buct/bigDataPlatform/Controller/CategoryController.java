package com.buct.bigDataPlatform.Controller;

import com.buct.bigDataPlatform.Pojo.Category;
import com.buct.bigDataPlatform.Pojo.Result;
import com.buct.bigDataPlatform.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 10:54
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @param category:
     * @Description: add
     * @Author: CarLor
     * @Date: 11:14 2024/6/12 0012
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result
     **/
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.OK();
    }

    /**
     * @Description: list
     * @Author: CarLor
     * @Date: 11:14 2024/6/12 0012
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result<java.util.List < com.buct.bigDataPlatform.Pojo.Category>>
     **/
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return Result.OK(categoryList);
    }

    /**
     * @param categoryId:
     * @Description: delete
     * @Author: CarLor
     * @Date: 11:31 2024/6/12 0012
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result
     **/
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer categoryId) {
        categoryService.delete(categoryId);
        return Result.OK();
    }

    /**
     * @param id:
     * @Description: detail
     * @Author: CarLor
     * @Date: 11:31 2024/6/12 0012
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result<com.buct.bigDataPlatform.Pojo.Category>
     **/
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category category = categoryService.getById(id);
        return Result.OK(category);
    }

    /**
    * @Description: update
    * @Author: CarLor
    * @Date: 14:42 2024/6/12 0012
    * @Param:
     * @param category:
    * @return: com.buct.bigDataPlatform.Pojo.Result
    **/
    @PutMapping()
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.OK();
    }
}
