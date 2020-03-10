package com.zy.service.Impl;

import com.zy.entity.Employee;
import com.zy.mapper.EmployeeDao;
import com.zy.service.EmployeeService;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddEmployeerequest;
import com.zy.vo.request.PaginationRequest;
import com.zy.vo.response.EmployeeResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class EmployeeImpl
 * @create 2020-02-21 18:21
 */
@Service
public class EmployeeImpl implements EmployeeService {
    @Resource
    EmployeeDao employeeDao;

    @Override
    public RespBean getEmployees(PaginationRequest paginationRequest) {
        List<Employee> employeeList=employeeDao.getEmployees(paginationRequest);
        EmployeeResponse employeeResponse=new EmployeeResponse();
        employeeResponse.setTotalpage(employeeDao.totalEmployee(paginationRequest.getQuery()));
        employeeResponse.setEmployees(employeeList);
        return RespBean.ok("查询成功！",employeeResponse);
    }

    @Override
    public int deleteEmployee(long empId, Date leaveDate, String terminationReason, String updateUser, Date updateTime) {
        return employeeDao.deleteEmployee(empId,leaveDate,terminationReason,updateUser,updateTime);
    }

    @Override
    public Employee getEmployeeByEmpId(long empId) {
        return employeeDao.getEmployeeByEmpId(empId);
    }

    @Override
    public int updateEmployeeNotDelete(Employee employee) {
        return employeeDao.updateEmployeeNotDelete(employee);
    }

    @Override
    public int updateEmployeeAndDelete(Employee employee) {
        return employeeDao.updateEmployeeAndDelete(employee);
    }

    @Override
    public int addEmployee(AddEmployeerequest addEmployeerequest){
        return employeeDao.addEmployee(addEmployeerequest);
    }

    @Override
    public Long selectEmpId(){
        return employeeDao.selectEmpId();
    }

    @Override
    public int updateGradeByDepartment(String grade2,String grade1, String department) {
        return employeeDao.updateGradeByDepartment(grade2,grade1,department);
    }

    @Override
    public Integer employeeNumber(String department){
        return employeeDao.employeeNumber(department);
    }
}
