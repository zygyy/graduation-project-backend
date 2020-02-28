package com.zy.vo.request;

import lombok.Data;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class LoginRequest
 * @create 2020-02-16 13:44
 */
@Data
public class LoginRequest {
    //用户昵称
    private String username;
    //用户密码
    private String password;
}
