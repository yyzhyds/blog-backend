package com.zhy.blogbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description 文章实体类
 * @date 2024/7/16 12:42
 */
@Data
@TableName("value = blog_article")
public class Article {

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
