package com.zhy.blogbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.blogbackend.model.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description 文章Mapper接口
 * @date 2024/7/16 13:30
 */

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取用户的所有文章
     * @return com.zhy.blogbackend.model.Article
     */
    List<Article> getAllByUserId(int userId);
}
