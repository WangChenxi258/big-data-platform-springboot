package com.buct.bigDataPlatform.Pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:27
 */
@Data
public class Category {
    @NotNull(groups = Update.class)
    @TableId(type = IdType.AUTO)
    private Integer id;//主键ID
    @NotEmpty
    private String categoryname;//分类名称
    @NotEmpty
    private String categoryalias;//分类别名
    private Integer createuser;//创建人ID
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatetime;//更新时间

    //如果说某个校验项没有指定分组，默认属于Default分组
    // 分组之间可以继承，A extends B那么A中拥有B中所有的校验项

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }
}
