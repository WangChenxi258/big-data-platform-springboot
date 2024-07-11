package com.buct.bigDataPlatform.Pojo;



import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:27
 */
@Data
public class User {
    @NotNull
    @TableId(type = IdType.AUTO)
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//把当前对象转换成Json字符串时，忽略password
    private String password;//密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称
    @NotEmpty
    @Email
    private String email;//邮箱
    private String userpic;//用户头像地址
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createtime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatetime;//更新时间
}
