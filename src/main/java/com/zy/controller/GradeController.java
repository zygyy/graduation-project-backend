package com.zy.controller;

import com.zy.service.DepartmentService;
import com.zy.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
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

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/deleteGrade/{id}")
    public RespBean deleteGrade(@PathVariable int id){
        return departmentService.deleteGrade(id);
    }


}
