package com.zhy.blogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhy.blogbackend.model.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description 用户服务接口
 * @date 2024/7/16 13:04
 */

public interface UserService extends IService<User>  {

    /**
     * 用户注册
     * @param username   用户名称
     * @param password  密码
     * @param rePassword    二次密码
     * @return 新用户 id
     */
    long userRegister(String username, String password, String rePassword);

    /**
     * 用户登录
     * @param username   用户名
     * @param password   密码
     * @param request
     * @return 脱敏之后的用户信息
     */
    User userLogin(String username, String password, HttpServletRequest request);
}
