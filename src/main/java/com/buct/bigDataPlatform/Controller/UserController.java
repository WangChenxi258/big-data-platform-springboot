package com.buct.bigDataPlatform.Controller;

import com.buct.bigDataPlatform.Pojo.Enums.ResultStatus;
import com.buct.bigDataPlatform.Util.JwtUtil;
import com.buct.bigDataPlatform.Util.Md5Util;
import com.buct.bigDataPlatform.Pojo.Result;
import com.buct.bigDataPlatform.Pojo.User;
import com.buct.bigDataPlatform.Service.UserService;
import com.buct.bigDataPlatform.Util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-05-09 16:24
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param username: 用户名
     * @param password: 密码
     * @Description: register 注册
     * @Author: CarLor
     * @Date: 14:32 2024/5/15 0015
     * @Param:
     * @return: com.buct.bigDataPlatform.Common.Config.ReturnResponse<java.lang.String>
     **/
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User user = userService.getByUsername(username);
        if (user == null) {
            //没有占用，注册
            userService.register(username, password);
            return Result.OK();
        } else {
            //占用
            return Result.FAIL("用户名已存在");
        }
    }

    /**
     * @param username: 用户名
     * @param password: 密码
     * @Description: login 登录
     * @Author: CarLor
     * @Date: 14:31 2024/5/15 0015
     * @Param:
     * @return: com.buct.bigDataPlatform.Common.Config.ReturnResponse<java.lang.String>
     **/
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.getByUsername(username);
        if (loginUser == null) {
            return Result.FAIL("用户名错误");
        }
        //判断密码是否正确，其中password为密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.OK(token);
        } else {
            return Result.FAIL("密码错误");
        }
    }

    /**
     * @Description: userInfo
     * @Author: CarLor
     * @Date: 14:56 2024/6/11 0011
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result<com.buct.bigDataPlatform.Pojo.User>
     **/
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.getByUsername(username);
        return Result.OK(user);
    }

    /**
     * @param user: 参数校验的用户信息
     * @Description: update
     * @Author: CarLor
     * @Date: 15:42 2024/6/11 0011
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result<java.lang.String>
     **/
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.OK();
    }

    /**
     * @param avatarUrl: 头像存放的url地址
     * @Description: updateAvatar
     * @Author: CarLor
     * @Date: 16:02 2024/6/11 0011
     * @Param:
     * @return: com.buct.bigDataPlatform.Pojo.Result<java.lang.String>
     **/
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.OK();
    }

    /**
    * @Description: updatePwd
    * @Author: CarLor
    * @Date: 10:50 2024/6/12 0012
    * @Param:
     * @param params:  旧密码、新密码、确认新密码的组合
    * @return: com.buct.bigDataPlatform.Pojo.Result<java.lang.String>
    **/
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        //参数校验
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.FAIL("缺少必要参数", ResultStatus.VALIDATION_FAILED);
        }

        //原密码
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.getByUsername(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.FAIL("原密码填写不正确",ResultStatus.VALIDATION_FAILED);
        }

        //newPwd与rePwd
        if(!rePwd.equals(newPwd)){
            return Result.FAIL("两次填写的新密码不一致",ResultStatus.VALIDATION_FAILED);
        }

        userService.updatePwd(newPwd);
        return Result.OK();
    }
}