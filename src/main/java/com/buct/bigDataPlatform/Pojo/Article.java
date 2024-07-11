package com.buct.bigDataPlatform.Pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.buct.bigDataPlatform.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:27
 */
@Data
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\${1,10}$")
    private String title;//文章标题
    private String content;//文章内容
    private String coverimg;//封面图像
    @State
    private String state;//发布状态 已发布|草稿
    private Integer categoryid;//文章分类id
    private Integer createuser;//创建人ID
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createtime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatetime;//更新时间
}



