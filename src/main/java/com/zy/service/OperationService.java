package com.zy.service;

import com.zy.vo.Bean.Operation;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class OperationService
 * @create 2020-04-02 12:44
 */
public interface OperationService {
    /**
     * 获取所有属于员工的权限
     * @return
     */
    public List<Operation> getUserOperation();
}
