package com.zhy.blogbackend.controller;

import com.zhy.blogbackend.common.BaseResponse;
import com.zhy.blogbackend.common.ErrorCode;
import com.zhy.blogbackend.common.ResultUtils;
import com.zhy.blogbackend.exception.BusinessException;
import com.zhy.blogbackend.model.User;
import com.zhy.blogbackend.model.UserDTO;
import com.zhy.blogbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.zhy.blogbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description 用户控制层
 * @date 2024/7/16 13:12
 */
@Api(tags = "登录注册")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 用户注册
     *
     * @param userDTO      用户注册实体类
     * @return            自定义返回类型
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户数据")
    public BaseResponse<Long> userRegister(@RequestBody UserDTO userDTO) {
        // 校验
        if (userDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = userDTO.getUsername();
        String userPassword = userDTO.getPassword();
        String rePassword = userDTO.getRePassword();
        if (StringUtils.isAnyBlank(username, userPassword, rePassword)) {
            return null;
        }
        long result = userService.userRegister(username, userPassword, rePassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userDTO     用户登录实体类
     * @param request       请求
     * @return              自定义返回类型
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户数据")
    public BaseResponse<User> userLogin(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        if (userDTO == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(username, password, request);

//        String token = TokenUtils.getToken(userDTO.getUsername(),userDTO.getPassword());
//        user.setToken(token);
//
//        //将用户信息存入session
//        request.getSession().setAttribute(USER_LOGIN_STATE, user);

        return ResultUtils.success(user);
    }

    /**
     * 获取当前用户
     *
     * @param request    请求
     * @return           统一返回类型
     */
    @GetMapping("/me")
    @ApiOperation(value = "获取当前用户", notes = "请求")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        //是否登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getUserId();
        // TODO 校验用户是否合法
        User user = userService.getById(userId);
        //数据脱敏
        User safetyUser = new User();
        safetyUser.setUserId(user.getUserId());
        safetyUser.setUsername(user.getUsername());
        return ResultUtils.success(safetyUser);
    }
}
