package com.zy.controller;

import com.zy.entity.Employee;
import com.zy.service.ActivateempService;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import com.zy.util.JwtUtils;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddEmployeerequest;
import com.zy.vo.request.LoginRequest;
import com.zy.vo.request.PaginationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Enumeration;


/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 登录
 * @class LoginByAdmin
 * @create 2020-02-15 16:28
 */
@Api("登录")
@RestController
@RequestMapping("/admin")
public class LoginByAdmin {

    @Autowired
    ActivateempService activateempService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    JwtUtils jwtUtils;


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public RespBean login(@RequestBody LoginRequest loginRequest)throws Exception {
        return activateempService.login(loginRequest);
    }

    @ApiOperation(value = "获取侧边栏的操作")
    @GetMapping("/getOperationsByAdmin")
    public RespBean getOperations() {
        return activateempService.getOperations();
    }

    @ApiOperation(value = "获取用户数据")
    @PostMapping("/users")
    public RespBean getUsers(@RequestBody PaginationRequest paginationRequest) {
        //计算起始数据的下标并重新赋值
        paginationRequest.setPagenum((paginationRequest.getPagenum()-1)*paginationRequest.getPagesize());
        return employeeService.getEmployees(paginationRequest);
    }


    @ApiOperation(value = "删除离职员工")
    @DeleteMapping("/deleteUser/{id}")
    public RespBean deleteUsers(@PathVariable long id, HttpServletRequest request){
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            int result=employeeService.deleteEmployee(id,jwtUtils.parseJwtTest(headValue),new Date(System.currentTimeMillis()));
            if(result>0){
                return RespBean.okMessage("删除员工成功!");
            }else{
                return RespBean.error("删除员工失败!");
            }
        }
        return null;

    }

    @ApiOperation(value = "根据员工号查找员工")
    @GetMapping("/getUserByEmpId/{empId}")
    public RespBean getUserByEmpId(@PathVariable long empId){
        Employee employee=employeeService.getEmployeeByEmpId(empId);
        if(employee!=null){
            return RespBean.ok("查询成功！",employee);
        }else{
            return RespBean.error("查询失败！");
        }
    }

    @ApiOperation(value = "获取部门和相应的职位")
    @GetMapping("/getDepartments")
    public RespBean getDepartments() {
        return departmentService.getDepartments();
    }


    @ApiOperation(value = "修改员工信息或者删除员工")
    @PutMapping("/updateEmployee")
    public RespBean updateOrDeleteEmployee(@RequestBody Employee employee,HttpServletRequest request){
        //解析更新的操作人
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            employee.setUpdateUser(jwtUtils.parseJwtTest(headValue));
            employee.setUpdateTime(new Date(System.currentTimeMillis()));
            if(employee.getLeaveDate()==null&&employee.getTerminationReason()==null){
                int result=employeeService.updateEmployeeNotDelete(employee);
                if(result>0){
                    return RespBean.okMessage("更新成功！");
                }else{
                    return RespBean.error("更新失败！");
                }
            }else {
                int result=employeeService.updateEmployeeAndDelete(employee);
                if(result>0){
                    return RespBean.okMessage("删除成功！");
                }else{
                    return RespBean.error("删除失败！");
                }
            }
        }
        return null;
    }

    @ApiOperation(value = "新增员工")
    @PostMapping("/addEmployee")
    public RespBean addEmployee(@RequestBody AddEmployeerequest addEmployeerequest,HttpServletRequest request){
        //解析更新的操作人
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            addEmployeerequest.setEmpId(employeeService.selectEmpId()+1);
            addEmployeerequest.setCreateUser(jwtUtils.parseJwtTest(headValue));
            addEmployeerequest.setCreateTime(new Date(System.currentTimeMillis()));
            int result=employeeService.addEmployee(addEmployeerequest);
            if(result>0){
                return RespBean.ok("创建新员工成功！",result);
            }else{
                return RespBean.error("创建失败！");
            }
        }
        return null;
    }


}
