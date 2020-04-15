package com.zy.controller.employeeController;

import com.zy.entity.Activateemp;
import com.zy.entity.Bbs;
import com.zy.entity.Employee;
import com.zy.service.ActivateempService;
import com.zy.service.BbsService;
import com.zy.service.EmployeeService;
import com.zy.util.ASEEncrypt;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.employeeRequest.BBSRequest;
import com.zy.vo.request.employeeRequest.RegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class EmployeeController
 * @create 2020-02-21 17:28
 */
@Api("员工Controller")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    ActivateempService activateempService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ASEEncrypt aSEEncrypt;

    @Autowired
    BbsService bbsService;

    @ApiOperation(value = "确保用户名唯一")
    @GetMapping("/usernameJudge/{username}")
    public RespBean usernameJudge(@PathVariable String username) {
        Activateemp activateemp = activateempService.judgeUserName(username);
        if (activateemp == null) {
            return RespBean.okMessage("此昵称可用！");
        } else {
            return RespBean.error("此昵称重复,请从新设置！");
        }
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public RespBean register(@RequestBody RegisterRequest registerRequest) throws Exception {
        //1.先根据员工号查看有没有注册过
        Activateemp activateemp = activateempService.getInfoByEmpId(registerRequest.getEmpId());
        if (activateemp != null) {
            return RespBean.error("此账号注册过,可直接登录！");
        } else {
            //2.如果没注册过,则根据员工号将查询到的信息填入注册表中
            Employee employee = employeeService.getEmployeeByEmpId(registerRequest.getEmpId());
            if (employee.getIsDeleted() == 1) {
                if (employee != null) {
                    Activateemp activateemInsert = new Activateemp();
                    activateemInsert.setEmpId(registerRequest.getEmpId());
                    activateemInsert.setName(employee.getChineseName());
                    activateemInsert.setPhone(employee.getPhone());
                    activateemInsert.setAddress(employee.getAddress());
                    activateemInsert.setUsername(registerRequest.getUsername());
                    activateemInsert.setPassword(aSEEncrypt.passwordEncrypt(registerRequest.getPassword()));
                    int result = activateempService.insertActivatemp(activateemInsert);
                    if (result > 0) {
                        return RespBean.okMessage("注册成功");
                    } else {
                        return RespBean.error("注册失败,请重新注册！");
                    }
                } else {
                    return RespBean.error("您并非公司员工,请先联系管理员！");
                }
            } else {
                return RespBean.error("您已并非公司员工,注册失败！");
            }

        }
    }

    @ApiOperation(value = "获取左侧权限")
    @GetMapping("/getOperationsByEmployee/{empId}")
    public RespBean getOperationsByEmployee(@PathVariable Long empId) {
        return employeeService.getOperationByEmployee(empId);
    }


    @ApiOperation(value = "获取个人信息")
    @GetMapping("/getEmployeeInformation/{empId}")
    public RespBean getEmployeeInformation(@PathVariable Long empId) {
        Employee employee = employeeService.getEmployeeByEmpId(empId);
        return RespBean.ok("获取个人信息成功！", employee);

    }

    @ApiOperation(value = "获取本团队其他成员的信息")
    @GetMapping("/getOthersInformation/{empId}")
    public RespBean getOthersInformation(@PathVariable Long empId) {
        return employeeService.getOthersInformation(empId);
    }

    @ApiOperation(value = "员工修改自己的信息")
    @PutMapping("/informationUpdate")
    public RespBean informationUpdate(@RequestBody Employee employee) {
        System.out.println("hello" + employee.getUrl());
        int result = employeeService.updateEmployeeNotDelete(employee);
        if (result > 0) {
            return RespBean.okMessage("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @ApiOperation(value = "获取BBS的信息")
    @GetMapping("/getBBS/{empId}")
    public RespBean getBBS(@PathVariable int empId) {
        List<Bbs> bbsList = bbsService.getBbs(empId);
        return RespBean.ok("数据获取成功！", bbsList);
    }

    @ApiOperation(value = "删除BBS的信息")
    @PutMapping("/bbsDeteted/{id}")
    public RespBean bbsDeteted(@PathVariable int id) {
        int result = bbsService.deteted(id);
        if (result > 0) {
            return RespBean.okMessage("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @ApiOperation(value = "添加文章")
    @PostMapping("/addBBS")
    public RespBean addBBs(@RequestBody Bbs bbs) {
        bbs.setPublishTime(new Date(System.currentTimeMillis()));
        int result = bbsService.add(bbs);
        if (result > 0) {
            return RespBean.okMessage("发布成功！");
        } else {
            return RespBean.error("发布失败");
        }
    }

    @ApiOperation(value = "根据id获取文章")
    @GetMapping("/getBBSById/{id}")
    public RespBean getBBSById(@PathVariable int id) {
        Bbs bbs = bbsService.getBBSById(id);
        if (bbs == null) {
            return RespBean.error("获取数据失败！");
        } else {
            return RespBean.ok("获取数据成功！", bbs);
        }
    }


    @ApiOperation(value = "根据id修改文章")
    @PutMapping("/updateBBS")
    public RespBean updateBBS(@RequestBody Bbs bbs){
        bbs.setPublishTime(new Date(System.currentTimeMillis()));
        int result=bbsService.updateBBS(bbs);
        if(result>0){
            return RespBean.okMessage("修改成功！");
        }else{
            return RespBean.error("修改失败！");
        }


    }
}
