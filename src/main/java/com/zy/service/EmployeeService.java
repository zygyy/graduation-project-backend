package com.zy.service;

import com.zy.entity.Employee;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddEmployeerequest;
import com.zy.vo.request.PaginationRequest;


import java.sql.Date;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 员工信息的处理方法
 * @class EmployeeDao
 * @create 2020-02-21 17:10
 */
public interface EmployeeService {
    /**
     * 获取员工信息
     *
     * @param paginationRequest
     * @return
     */
    public RespBean getEmployees(PaginationRequest paginationRequest);

    public int deleteEmployee(long empId, Date leaveDate, String terminationReason, String updateUser, Date updateTime);

    public Employee getEmployeeByEmpId(long empId);

    public int updateEmployeeNotDelete(Employee employee);

    public int updateEmployeeAndDelete(Employee employee);

    public int addEmployee(AddEmployeerequest addEmployeerequest);

    public long selectEmpId();
}
