package com.buct.bigDataPlatform.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buct.bigDataPlatform.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
