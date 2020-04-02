package com.zy.service;

import com.zy.entity.EmployeeRights;
import com.zy.vo.request.UpdateRightsRequest;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class EmployeeRightsService
 * @create 2020-04-02 15:44
 */
public interface EmployeeRightsService {
    //管理员分配权限的方法

    /**
     * 获取已经分配的权限
     * @param gradeId
     * @return
     */
    public EmployeeRights getEmployeeRight(int gradeId);

    /**
     * 分配权限(修改)
     * @param updateRightsRequest
     * @return
     */
    public int updateRights(UpdateRightsRequest updateRightsRequest);

    /**
     * 分配权限(添加)
     * @param updateRightsRequest
     * @return
     */
    public int insertRights(UpdateRightsRequest updateRightsRequest);


}
