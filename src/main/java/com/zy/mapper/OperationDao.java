package com.zy.mapper;


import com.zy.vo.Bean.Operation;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class OperationDao
 * @create 2020-02-18 21:19
 */
public interface OperationDao {
    //管理员分配权限的方法

    /**
     * 获取所有属于员工的权限
     * @return
     */
    public List<Operation> getUserOperation();

}
