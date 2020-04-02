package com.zy.controller;

import com.zy.entity.Department;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddGraderequest;
import com.zy.vo.request.DeleteDescribeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class GradeController
 * @create 2020-02-29 21:35
 */
@Api("对职位的操作")
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/deleteGrade/{id}")
    public RespBean deleteGrade(@PathVariable int id) {
        return departmentService.deleteGrade(id);
    }

    @ApiOperation(value = "删除职位描述")
    @PutMapping("/deleteDescribe")
    public RespBean deleteDescribe(@RequestBody DeleteDescribeRequest deleteDescribeRequest) {
        int result = departmentService.deleteGradeDescribe(deleteDescribeRequest);
        if (result > 0) {
            return RespBean.ok("删除成功！", departmentService.getGrades());
        } else {
            return RespBean.error("删除失败！");
        }
    }


    @ApiOperation(value = "根据id更新职位")
    @PutMapping("/updateGrade")
    public RespBean updateGrade(@RequestBody Department department) {
        Department updateInformation = departmentService.getUpdateDepartment(department.getId());
        String grade2 = department.getName();
        String grade1 = updateInformation.getName();
        String employeeDepartment = updateInformation.getDepartment().getDepartment().getName() + "/" + updateInformation.getDepartment().getName();
        int result = departmentService.updateGrade(department.getId(), department.getName());
        int res = employeeService.updateGradeByDepartment(grade2, grade1, employeeDepartment);
        if (result > 0 && res > 0) {
            return RespBean.okMessage("更新成功！");
        } else {
            return RespBean.error("更新失败！");
        }
    }

    @ApiOperation(value = "获取部门的级联选择器")
    @GetMapping("/getDepartmentsNotlevel2")
    public RespBean getDepartments() {
        return departmentService.getDepartmentsNotLevel2();
    }


    @ApiOperation(value = "添加职位")
    @PostMapping("/addGrade/{pId}")
    public RespBean addGrade(@RequestBody AddGraderequest addGraderequest, @PathVariable int pId) {
        Department department = departmentService.getDepartmentByNameAndPId(addGraderequest.getName(), pId);
        if (department == null) {
            int result = departmentService.addGrade(addGraderequest.getName(), pId, addGraderequest.getDescribes(), addGraderequest.getScale());
            if (result > 0) {
                return RespBean.okMessage("新增成功！");
            } else {
                return RespBean.error("新增失败!");
            }
        } else {
            return RespBean.error("该职位已存在!");
        }

    }

}
