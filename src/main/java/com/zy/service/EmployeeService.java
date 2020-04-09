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

    /**
     * 根据empId删除员工（逻辑删除），并添加操作人，原因以及时间等信息
     *
     * @param empId
     * @param leaveDate
     * @param terminationReason
     * @param updateUser
     * @param updateTime
     * @return
     */
    public int deleteEmployee(long empId, Date leaveDate, String terminationReason, String updateUser, Date updateTime);

    /**
     * 根据empId获取员工信息
     *
     * @param empId
     * @return
     */
    public Employee getEmployeeByEmpId(long empId);

    /**
     * 更新员工信息（不离职）
     *
     * @param employee
     * @return
     */
    public int updateEmployeeNotDelete(Employee employee);

    /**
     * 更新员工信息（离职）
     *
     * @param employee
     * @return
     */
    public int updateEmployeeAndDelete(Employee employee);

    /**
     * 录入员工信息
     *
     * @param addEmployeerequest
     * @return
     */
    public int addEmployee(AddEmployeerequest addEmployeerequest);

    /**
     * 查询最大的empId，为了录入员工时自动分配empId（最大数+1）
     *
     * @return
     */
    public Long selectEmpId();

    /**
     * 更新职位名称时，employee表的职位名称也需要修改
     *
     * @param grade2
     * @param grade1
     * @param department
     * @return
     */
    public int updateGradeByDepartment(String grade2, String grade1, String department);

    /**
     * 查询某个部门下的总人数
     *
     * @param department
     * @return
     */
    public Integer employeeNumber(String department);

    /**
     * 获取每个部门下对应职位的人数
     *
     * @param grade
     * @param department
     * @return
     */
    public Integer employeeGradeNumber(String grade, String department);


    //员工端

    /**
     * 员工获取左侧操作权限
     *
     * @param empId
     * @return
     */
    public RespBean getOperationByEmployee(Long empId);

    /**
     * 获取本团队其他成员的信息
     *
     * @param empId
     * @return
     */
    public RespBean getOthersInformation(Long empId);
}
