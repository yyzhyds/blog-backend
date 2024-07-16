package com.zhy.blogbackend.constant;

/**
 * @author 随缘而愈
 * @version 2.0
 * @description 用户常量
 * @date 2024/7/16 13:17
 */

public interface UserConstant {

    /**
     * 用户登录态键
    */
    String USER_LOGIN_STATE = "userLoginState";


    /**
     * 默认权限（普通用户）
     */
    int DEFAULT_ROLE = 0;

    /**
     * 管理员权限
     */
    int ADMIN_ROLE = 1;
    
}
