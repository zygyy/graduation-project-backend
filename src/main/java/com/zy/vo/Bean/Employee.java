package com.zy.vo.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 员工信息表
 * @class Employee
 * @create 2020-02-15 15:35
 */
@Data
public class Employee {
    private Long id;
    //员工号
    private Long empId;
    //名字
    private String chineseName;
    //性别
    private String gender;
    //等级
    private String grade;
    //开始工作的时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date hireDate;
    //部门
    private String department;
    //工作地点
    private String workingLocation;
    //联系电话
    private String phone;
    //家庭住址
    private String address;
    //邮箱
    private String email;
    //毕业学校
    private String school;
    //专业
    private String marjor;
    //学位
    private String degree;
    //毕业时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date graduationTime;
    //离职日期
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date leaveDate;
    //离职原因
    private String terminationReason;
    //生日
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthday;
    //该用户由哪个管理员创建(填入的是管理员的编号)
    private String createUser;
    //创建的时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date createTime;
    //该用户由哪个管理员修改(填入的是管理员的编号)
    private String updateUser;
    //修改时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date updateTime;
    //是否有效(1代表有效,0代表无效)
    private int isDeleted;

}
