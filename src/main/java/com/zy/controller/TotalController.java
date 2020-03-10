package com.zy.controller;

import com.zy.entity.Department;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import com.zy.vo.base.RespBean;
import com.zy.vo.response.TotalResopnse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class TotalController
 * @create 2020-03-08 16:21
 */
@Api("管理员端数据统计")
@RestController
@RequestMapping("/total")
public class TotalController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;



    @ApiOperation(value = "获取各个部门人数")
    @GetMapping("/report")
    public RespBean report() {
        List<Department> result = departmentService.departmentAndNumber();
        TotalResopnse totalResopnse = new TotalResopnse();
        List<String> temp = new ArrayList();
        List<Integer> number = new ArrayList();

        for (Department department : result) {
            temp.add(department.getDepartment().getName() + "/" + department.getName());
        }
        //处理总人数
        for (int i = 0; i < temp.size(); i++) {
            number.add(employeeService.employeeNumber(temp.get(i)));
        }
        totalResopnse.setNumber(number);

        totalResopnse.setDepartment(temp);
        return RespBean.ok(totalResopnse);
    }



}
