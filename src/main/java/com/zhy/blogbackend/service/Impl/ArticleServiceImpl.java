package com.zhy.blogbackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhy.blogbackend.mapper.ArticleMapper;
import com.zhy.blogbackend.model.Article;
import com.zhy.blogbackend.model.User;
import com.zhy.blogbackend.service.ArticleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zhy.blogbackend.constant.UserConstant.ADMIN_ROLE;
import static com.zhy.blogbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description TODO
 * @date 2024/7/16 13:34
 */
@Service("articleService")
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Resource
    private ArticleMapper articleMapper;

    /**
     * 获取用户的所有文章
     * @return com.zhy.blogbackend.model.Article
     */
    @Override
    public PageInfo<Article> getAllByUserId(int userId,int pageNum, int pageSize) {
        log.info("get all article by user id: {}", userId);
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.getAllByUserId(userId);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }


}
