package com.zy.controller;

import com.zy.entity.EmployeeRights;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeRightsService;
import com.zy.service.OperationService;
import com.zy.vo.Bean.Operation;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddRightsRequest;
import com.zy.vo.request.PaginationRequest;
import com.zy.vo.request.UpdateRightsRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class RightsController
 * @create 2020-04-01 17:42
 */
@Api("管理员端分配权限")
@RestController
@RequestMapping("/rights")
public class RightsController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    OperationService operationService;

    @Autowired
    EmployeeRightsService employeeRightsService;


    @ApiOperation(value = "分页获取职位信息")
    @PostMapping("/getGrades")
    public RespBean getGrades(@RequestBody PaginationRequest paginationRequest) {
        //计算起始数据的下标并重新赋值
        paginationRequest.setPagenum((paginationRequest.getPagenum() - 1) * paginationRequest.getPagesize());
        return departmentService.getGradesByPagination(paginationRequest);
    }

    @ApiOperation(value = "获取可以给员工分配的权限")
    @GetMapping("/getOperations")
    public RespBean getOperations() {
        List<Operation> operationList = operationService.getUserOperation();
        if (operationList.size() > 0) {
            return RespBean.ok("获取数据成功！", operationList);
        } else {
            return RespBean.error("获取失败！请重试。");
        }
    }

    @ApiOperation(value = "获取已经分配到的权限")
    @GetMapping("/getAchieveRights/{gradeId}")
    public RespBean getAchieveRights(@PathVariable int gradeId) {
        EmployeeRights employeeRights = employeeRightsService.getEmployeeRight(gradeId);
        return RespBean.ok(employeeRights);
    }


    @ApiOperation(value = "分配权限")
    @PostMapping("/updateRights")
    public RespBean updateRights(@RequestBody UpdateRightsRequest updateRightsRequest) {
        EmployeeRights employeeRights = employeeRightsService.getEmployeeRight(updateRightsRequest.getGradeId());
        if (employeeRights == null) {
            int resultInsert = employeeRightsService.insertRights(updateRightsRequest);
            if (resultInsert > 0) {
                return RespBean.okMessage("分配权限成功！");
            } else {
                return RespBean.error("分配权限失败！");
            }
        } else {
            int resultUpdate = employeeRightsService.updateRights(updateRightsRequest);
            if (resultUpdate > 0) {
                return RespBean.okMessage("分配权限成功！");
            } else {
                return RespBean.error("分配权限失败！");
            }
        }
    }

    @ApiOperation(value = "新增权限")
    @PostMapping("/addRights")
    public RespBean addRights(@RequestBody AddRightsRequest addRightsRequest){
        int result=operationService.addRights(addRightsRequest);
        if(result>0){
            return RespBean.okMessage("新增成功！");
        }else{
            return RespBean.error("新增失败！");
        }

    }

}
