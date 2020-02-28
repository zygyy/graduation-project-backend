package com.zy.mapper;

import com.zy.entity.Employee;
import com.zy.vo.request.PaginationRequest;
import com.zy.vo.request.AddEmployeerequest;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 员工信息的处理方法
 * @class EmployeeDao
 * @create 2020-02-21 17:10
 */
@Repository
public interface EmployeeDao {
    /**
     * 获取员工信息
     *
     * @param paginationRequest
     * @return
     */
    public List<Employee> getEmployees(PaginationRequest paginationRequest);

    /**
     * 获取总条数
     *
     * @return
     */
    public int totalEmployee(String query);

    /**
     * 删除员工
     *
     * @param empId
     * @return
     */
    public int deleteEmployee(long empId, String updateUser, Date updateTime);

    /**
     * 根据员工号查找员工
     *
     * @param empId
     * @return
     */
    public Employee getEmployeeByEmpId(long empId);

    /**
     * 修改员工信息
     *
     * @param employee
     * @return
     */
    public int updateEmployeeNotDelete(Employee employee);

    /**
     * 修改并删除员工信息
     *
     * @param employee
     * @return
     */
    public int updateEmployeeAndDelete(Employee employee);

    /**
     * 添加新员工
     *
     * @param addEmployeerequest
     * @return
     */
    public int addEmployee(AddEmployeerequest addEmployeerequest);

    /**
     * 设置员工号自动递增
     *
     * @return
     */
    public long selectEmpId();
}
