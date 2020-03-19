package com.zy.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class JwtUtils
 * @create 2020-02-17 21:09
 */
public class JwtUtils {

    /**
     * 创建token
     *
     * @param id
     * @param username
     * @return
     */
    public String createJwt(long id, String username) {

        //1.创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id + "").setSubject(username).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "zhouyu");
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token获取操作人名字
     *
     * @param token
     * @return
     */
    public String parseJwtTest(String token) {
        Claims claims = Jwts.parser().setSigningKey("zhouyu").parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 解析token获取操作人名字和EmpId
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey("zhouyu").parseClaimsJws(token).getBody();
        claims.getSubject();
        claims.getId();
        return claims;
    }


}
