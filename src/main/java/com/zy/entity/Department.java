package com.zy.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class Department
 * @create 2020-02-25 14:05
 */
@Data
public class Department {
    private int id;
    private String name;
    private int pId;//父节点
    private int level;//级别
    private String describes;//level为2的grade的描述
    private String scale;//职位的级别
    private List<Department> firstChildren;
    private Department department;
    private String[] describesSplit;


    // 用户控制前端的按钮和文本框的切换
    private boolean inputVisible= false;
    private String inputValue="";

}
