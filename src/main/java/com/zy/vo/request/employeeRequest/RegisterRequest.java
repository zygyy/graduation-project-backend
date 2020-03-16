package com.zy.vo.request.employeeRequest;

import lombok.Data;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class RegisterRequest
 * @create 2020-03-15 19:15
 */
@Data
public class RegisterRequest {
    //员工号
    private Long empId;

    private String username;

    private String password;
}
