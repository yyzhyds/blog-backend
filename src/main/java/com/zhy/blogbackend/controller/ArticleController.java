package com.zhy.blogbackend.controller;

import com.github.pagehelper.PageInfo;
import com.zhy.blogbackend.common.BaseResponse;
import com.zhy.blogbackend.common.ErrorCode;
import com.zhy.blogbackend.common.ResultUtils;
import com.zhy.blogbackend.exception.BusinessException;
import com.zhy.blogbackend.model.Article;
import com.zhy.blogbackend.model.User;
import com.zhy.blogbackend.model.UserDTO;
import com.zhy.blogbackend.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zhy.blogbackend.constant.UserConstant.ADMIN_ROLE;
import static com.zhy.blogbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description 文章控制层
 * @date 2024/7/16 13:48
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;


    /**
     * 创建新文章
     *
     * @param article      文章实体类
     * @return            自定义返回类型
     */
    @PostMapping("/posts")
    @ApiOperation(value = "创建新文章",notes = "文章数据")
    public BaseResponse<Boolean> createNewArticle(@RequestBody Article article, HttpServletRequest request) {
        //是否登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }

        // 校验
        if (article == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean save = articleService.save(article);
        return ResultUtils.success(save);
    }

    /**
     * 获取用户所有文章
     *
     * @param userId      用户id
     * @return            自定义返回类型
     */
    @GetMapping("/posts")
    @ApiOperation(value = "获取用户所有文章",notes = "文章数据")
    public BaseResponse<PageInfo<Article>> getAllArticleByUserId(
            @RequestParam(value = "uid") int userId,
            @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize) {
        PageInfo<Article> result = articleService.getAllByUserId(userId, pageNum, pageSize);
        return ResultUtils.success(result);
    }

    /**
     * 查询单篇文章
     *
     * @param id            文章id
     * @return            自定义返回类型
     */
    @GetMapping("/posts/{id}")
    @ApiOperation(value = "查询单篇文章",notes = "文章数据")
    public BaseResponse<Article> getArticleById(@PathVariable("id") int id) {
        Article article = articleService.getById(id);
        return ResultUtils.success(article);
    }

    /**
     * 更新文章
     *
     * @param id            文章id
     * @return            自定义返回类型
     */
    @PutMapping("/posts/{id}")
    @ApiOperation(value = "更新文章",notes = "文章数据")
    public BaseResponse<Article> updateArticle(@PathVariable("id") Long id,
                                               @RequestParam(value = "article") Article article,
                                               HttpServletRequest request)  {
        //是否登录
        Object obj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) obj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "缺少管理员权限");
        }
        article.setPostId(id);
        articleService.updateById(article);
        return ResultUtils.success(article);
    }

    /**
     * 删除文章
     *
     * @param id            文章id
     * @return            自定义返回类型
     */
    @DeleteMapping("/posts/{id}")
    @ApiOperation(value = "更新文章",notes = "文章数据")
    public BaseResponse<Boolean> deleteArticle(@PathVariable("id") Long id,
                                               HttpServletRequest request)  {
        //登录成功并且拥有权限
        if (!isAdmin(request) && isLogin(request))  {
            throw new BusinessException(ErrorCode.NO_AUTH, "缺少管理员权限");
        }
        boolean result = articleService.removeById(id);
        return ResultUtils.success(result);
    }


    /**
     * 是否为管理员
     *
     * @param request   请求
     * @return          是否成功
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅超级管理员可操作
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getRole() == ADMIN_ROLE;
    }

    /**
     * 是否登录
     *
     * @param request   请求
     * @return          是否成功
     */
    private boolean isLogin(HttpServletRequest request) {
        // 仅超级管理员可操作
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return true;
    }
}
