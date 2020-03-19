package com.zy.controller.employeeController;

import com.zy.entity.Activateemp;
import com.zy.entity.Employee;
import com.zy.service.ActivateempService;
import com.zy.service.EmployeeService;
import com.zy.util.ASEEncrypt;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.employeeRequest.RegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "确保用户名唯一")
    @GetMapping("/usernameJudge/{username}")
    public RespBean usernameJudge(@PathVariable String username){
        Activateemp activateemp=activateempService.judgeUserName(username);
        if(activateemp==null){
            return RespBean.okMessage("此昵称可用！");
        }else{
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
        }
    }
}