package com.buct.bigDataPlatform.Pojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.FieldFill;

import java.time.LocalDateTime;
@lombok.Data
public class Data {
        @TableId(type = IdType.AUTO)
        private Integer id;//主键ID
        @NotEmpty
        @Pattern(regexp = "^\\${1,10}$")
        private String name;//名字
        private String dir;//存放的路径
        private Integer task_id;//任务id
        private Integer createuser;//创建人ID
        @TableField(fill = FieldFill.INSERT)
        private LocalDateTime createtime;//创建时间
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private LocalDateTime updatetime;//更新时间
}
