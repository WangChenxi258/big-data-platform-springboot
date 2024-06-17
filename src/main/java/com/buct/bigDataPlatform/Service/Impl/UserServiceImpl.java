package com.buct.bigDataPlatform.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.buct.bigDataPlatform.Mapper.UserMapper;
import com.buct.bigDataPlatform.Pojo.User;
import com.buct.bigDataPlatform.Service.UserService;
import com.buct.bigDataPlatform.Util.Md5Util;
import com.buct.bigDataPlatform.Util.ThreadLocalUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void register(String username, String password) {
        //加密
        val md5String = Md5Util.getMD5String(password);
        //添加
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5String);
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        User updateUser = new User();
        updateUser.setNickname(user.getNickname());
        updateUser.setEmail(user.getEmail());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", user.getId());
        userMapper.update(updateUser, updateWrapper);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        User updateUser = new User();
        updateUser.setUserPic(avatarUrl);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", id);
        userMapper.update(updateUser, updateWrapper);
    }

    @Override
    public void updatePwd(String newPwd) {
        User updateUser = new User();
        updateUser.setPassword(Md5Util.getMD5String(newPwd));

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", id);

        userMapper.update(updateUser, updateWrapper);
    }
}