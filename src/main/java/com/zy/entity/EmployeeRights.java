package com.zy.entity;

import lombok.Data;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 分配权限表
 * @class EmployeeRights
 * @create 2020-04-01 12:04
 */

@Data
public class EmployeeRights {
    private int id;
    private int gradeId;
    private String operationId;
}
