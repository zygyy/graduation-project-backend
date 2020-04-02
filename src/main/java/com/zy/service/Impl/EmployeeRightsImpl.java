package com.zy.service.Impl;

import com.zy.entity.EmployeeRights;
import com.zy.mapper.EmployeeRightsDao;
import com.zy.service.EmployeeRightsService;
import com.zy.vo.request.UpdateRightsRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class EmployeeRightsImpl
 * @create 2020-04-02 15:45
 */
@Service
public class EmployeeRightsImpl implements EmployeeRightsService {
    @Resource
    EmployeeRightsDao employeeRightsDao;

    @Override
    public EmployeeRights getEmployeeRight(int gradeId) {
        return employeeRightsDao.getEmployeeRight(gradeId);
    }

    /**
     * 分配权限
     * @param updateRightsRequest
     * @return
     */
    @Override
    public int updateRights(UpdateRightsRequest updateRightsRequest){
        return employeeRightsDao.updateRights(updateRightsRequest);
    }

    @Override
    public int insertRights(UpdateRightsRequest updateRightsRequest) {
        return employeeRightsDao.insertRights(updateRightsRequest);
    }
}
