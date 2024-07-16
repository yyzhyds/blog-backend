package com.zhy.blogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zhy.blogbackend.model.Article;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description TODO
 * @date 2024/7/16 13:33
 */

public interface ArticleService extends IService<Article> {

    /**
     * 获取用户的所有文章
     * @return com.zhy.blogbackend.model.Article
     */
    PageInfo<Article> getAllByUserId(int userId, int pageNum, int pageSize);
}
