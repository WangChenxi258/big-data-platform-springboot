package com.buct.bigDataPlatform.Service;

import com.buct.bigDataPlatform.Pojo.User;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:27
 */
public interface UserService {
    //根据用户名查询用户
    User getByUsername(String username);
    //注册
    void register(String username, String password);
    //更新
    void update(User user);
    //更新头像
    void updateAvatar(String avatarUrl);
    //修改密码
    void updatePwd(String newPwd);
}
