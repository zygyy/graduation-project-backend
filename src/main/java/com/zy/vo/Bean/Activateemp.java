package com.zy.vo.Bean;

import lombok.Data;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 将激活的用户的信息存放到此表中(用户需要先激活, 管理员则不需要激活)
 * @class Activateemp
 * @create 2020-02-15 15:35
 */
@Data
public class Activateemp {
    private int id;
    private Long empId;
    private String name;
    private String phone;
    private String address;
    //用户昵称
    private String username;
    //用户密码
    private String password;
    //角色id
    private int roleId;
    //头像地址
    private String photoUrl;
}
