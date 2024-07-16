package com.zhy.blogbackend.swagger.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 随缘而愈
 * @version 2.0
 * @description SwaggerInfo实体类
 * @date 25/1/2024 下午6:36
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {

    private String basePackage;
    private String title;
    private String contactName;
    private String contactUrl;
    private String contactEmil;
    private String version;
    private String description;

}
