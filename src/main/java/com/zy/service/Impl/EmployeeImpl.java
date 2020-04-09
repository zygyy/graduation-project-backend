package com.zy.service.Impl;

import com.zy.entity.Department;
import com.zy.entity.Employee;
import com.zy.mapper.DepartmentDao;
import com.zy.mapper.EmployeeDao;
import com.zy.mapper.EmployeeRightsDao;
import com.zy.mapper.OperationDao;
import com.zy.service.EmployeeService;
import com.zy.vo.Bean.Operation;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddEmployeerequest;
import com.zy.vo.request.PaginationRequest;
import com.zy.vo.response.EmployeeResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
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

    @Resource
    DepartmentDao departmentDao;

    @Resource
    EmployeeRightsDao employeeRightsDao;

    @Resource
    OperationDao operationDao;

    @Override
    public RespBean getEmployees(PaginationRequest paginationRequest) {
        List<Employee> employeeList = employeeDao.getEmployees(paginationRequest);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setTotalpage(employeeDao.totalEmployee(paginationRequest.getQuery()));
        employeeResponse.setEmployees(employeeList);
        return RespBean.ok("查询成功！", employeeResponse);
    }

    @Override
    public int deleteEmployee(long empId, Date leaveDate, String terminationReason, String updateUser, Date updateTime) {
        return employeeDao.deleteEmployee(empId, leaveDate, terminationReason, updateUser, updateTime);
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
    public int addEmployee(AddEmployeerequest addEmployeerequest) {
        return employeeDao.addEmployee(addEmployeerequest);
    }

    @Override
    public Long selectEmpId() {
        return employeeDao.selectEmpId();
    }

    @Override
    public int updateGradeByDepartment(String grade2, String grade1, String department) {
        return employeeDao.updateGradeByDepartment(grade2, grade1, department);
    }

    @Override
    public Integer employeeNumber(String department) {
        return employeeDao.employeeNumber(department);
    }

    @Override
    public Integer employeeGradeNumber(String grade, String department) {
        return employeeDao.employeeGradeNumber(grade, department);
    }

    @Override
    public RespBean getOperationByEmployee(Long empId) {
        Employee employee = employeeDao.getEmployeeByEmpId(empId);
        String[] departmentName = employee.getDepartment().split("/");
        int one = departmentDao.getDepartmentId(departmentName[0]);
        Department department = departmentDao.getDepartmentByNameAndPId(departmentName[1], one);
        Department resultDepartment = departmentDao.getDepartmentByNameAndPId(employee.getGrade(), department.getId());
        //获取到操作权限
        String operationIds = employeeRightsDao.getOperationId(resultDepartment.getId());

        String[] ids = operationIds.split(",");
        List<Operation> operationResult = new ArrayList<Operation>();
        for (int i = 0; i < ids.length; i++) {
            Operation operation = operationDao.getOperationById(Integer.parseInt(ids[i]));
            operationResult.add(operation);
        }
        return RespBean.ok("获取操作权限成功！", operationResult);
    }

    @Override
    public RespBean getOthersInformation(Long empId) {
        Employee employee=employeeDao.getEmployeeByEmpId(empId);
        List<Employee> employeeList=employeeDao.getOthers(employee.getDepartment(),empId);
        return RespBean.ok("获取信息成功！",employeeList);
    }

}
