package com.zy.controller;

import com.zy.service.ActivateempService;
import com.zy.util.ASEEncrypt;
import com.zy.util.JwtUtils;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.MyselfUpdateRequest;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class MyselfController
 * @create 2020-03-18 14:18
 */
@Api("管理员端个人信息界面")
@RestController
@RequestMapping("/admin")
public class MyselfController {
    @Autowired
    ActivateempService activateempService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ASEEncrypt aSEEncrypt;


    @ApiOperation(value = "修改基本信息")
    @PutMapping("/myselfUpdate")
    public RespBean myselfUpdate(@RequestBody MyselfUpdateRequest myselfUpdateRequest) {
        int result = activateempService.myselfUpdate(myselfUpdateRequest);
        if (result > 0) {
            return RespBean.okMessage("修改成功！");
        } else {
            return RespBean.error("修改失败！");
        }
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword/{password}")
    public RespBean updatePassword(@PathVariable String password, HttpServletRequest request) throws Exception {
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            Claims result = jwtUtils.parseJwt(headValue);
            System.out.println("解析是：" + result.getId());
            int updateResult = activateempService.passwordUpdate(Long.parseLong(result.getId()), result.getSubject(), aSEEncrypt.passwordEncrypt(password));
            if (updateResult > 0) {
                return RespBean.okMessage("修改成功！");
            } else {
                return RespBean.error("修改失败");
            }
        }
        return null;

    }
}
