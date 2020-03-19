package com.zy.vo.request;

import lombok.Data;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class myselfUpdateRequest
 * @create 2020-03-18 14:21
 */
@Data
public class MyselfUpdateRequest {
    private Long empId;
    private String name;
    private String phone;
    private String address;
    //用户昵称
    private String username;
}
