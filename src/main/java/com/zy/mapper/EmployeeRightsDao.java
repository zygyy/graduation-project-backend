package com.zy.mapper;

import com.zy.entity.EmployeeRights;
import com.zy.vo.request.UpdateRightsRequest;


/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class EmployeeRightsDao
 * @create 2020-04-01 12:04
 */
public interface EmployeeRightsDao {
    //管理员分配权限的方法

    /**
     * 获取已经分配的权限
     *
     * @param gradeId
     * @return
     */
    public EmployeeRights getEmployeeRight(int gradeId);


    /**
     * 分配权限（修改权限）
     *
     * @param updateRightsRequest
     * @return
     */
    public int updateRights(UpdateRightsRequest updateRightsRequest);


    /**
     * 分配权限（新增权限）
     *
     * @param updateRightsRequest
     * @return
     */
    public int insertRights(UpdateRightsRequest updateRightsRequest);


    //员工端
    /**
     * 获取员工的操作权限
     *
     * @param gradeId
     * @return
     */
    public String getOperationId(int gradeId);
}
