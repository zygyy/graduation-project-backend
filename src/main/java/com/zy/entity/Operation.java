package com.zy.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class AdminOperation
 * @create 2020-02-18 21:19
 */
@Data
public class Operation {
    private int operationId;
    private String authName;
    private int pId;
    private int level;
    private String path;
    private List<Operation> children;
}
