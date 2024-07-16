package com.zhy.blogbackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 随缘而愈
 * @version 2.0
 * @description 用户注册实体类
 * @date 2024/7/16 13.14
 */
@Data
public class UserDTO implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 校验密码
     */
    private String rePassword;

}
