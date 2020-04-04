package com.zy.mapper;


import com.zy.vo.Bean.Operation;
import com.zy.vo.request.AddRightsRequest;

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
     *
     * @return
     */
    public List<Operation> getUserOperation();

    /**
     * 增加新权限
     *
     * @param addRightsRequest
     * @return
     */
    public int addRights(AddRightsRequest addRightsRequest);

    /**
     * 根据Id获取权限
     *
     * @param operationId
     * @return
     */
    public Operation getOperationById(int operationId);

}
