package com.zhy.blogbackend.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author 随缘而愈
 * @version 1.0
 * @description 生成token
 * @date 2024/7/16 10:56
 */

public class TokenUtils {

    /**
     * 生成token
     * @param userId
     * @param sign
     * @return java.lang.String
     * @description TODO
     */
    public static String getToken(String userId,String sign){
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面 作为载荷
                //两小时后token过期
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign)); // 以 sign 作为 token 的密钥
    }

}
