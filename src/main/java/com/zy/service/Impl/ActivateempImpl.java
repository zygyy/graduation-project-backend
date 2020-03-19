package com.zy.service.Impl;

import com.zy.entity.Activateemp;
import com.zy.entity.Operation;
import com.zy.mapper.ActivateempDao;
import com.zy.service.ActivateempService;
import com.zy.util.ASEEncrypt;
import com.zy.util.JwtUtils;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.LoginRequest;
import com.zy.vo.request.MyselfUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class ActivateempImpl
 * @create 2020-02-15 21:29
 */
@Service
public class ActivateempImpl implements ActivateempService {
    @Resource
    ActivateempDao activateempDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ASEEncrypt aSEEncrypt;

    /**
     * 登录的一系列操作
     *
     * @param loginRequest
     * @return
     */
    @Override
    public RespBean login(LoginRequest loginRequest) throws Exception {
        Activateemp activateemp = activateempDao.loadUserByUsername(loginRequest.getUsername());
        if (activateemp == null) {
            return RespBean.error("您并非公司员工，请重新登录！");
        } else {
            //验证密码的正确
            String newpassword = aSEEncrypt.passwordEncrypt(loginRequest.getPassword());
            if (activateemp.getPassword().equals(newpassword)) {
                //添加token
                String token = jwtUtils.createJwt(activateemp.getEmpId(), activateemp.getName());
                activateemp.setToken(token);
                if (activateemp.getRoleId() != 1) {
                    return RespBean.ok("员工登录成功！", activateemp);
                } else {
                    return RespBean.ok("管理员登录成功！", activateemp);
                }
            } else {
                return RespBean.error("密码错误！请重新确认！");
            }
        }
    }

    /**
     * 登录之后获取对应的操作
     *
     * @return
     */
    @Override
    public RespBean getOperations() {
        //查询到的操作集合
        List<Operation> activateempDaoList = activateempDao.getOperationByAdmin();
        //创建一个空的集合,将来把树形结构放进去
        List<Operation> operationResponseList = new ArrayList<Operation>();
        //循环遍历集合
        for (Operation operation : activateempDaoList) {
            //如果父节点为0,则为一级操作
            if (operation.getPId() == 0) {
                //把一级操作添加到空的集合
                operationResponseList.add(operation);
            }
            //再次遍历
            for (Operation operationSecond : activateempDaoList) {
                if (operationSecond.getPId() == operation.getOperationId()) {
                    if (operation.getChildren() == null) {
                        operation.setChildren(new ArrayList<Operation>());
                    }
                    operation.getChildren().add(operationSecond);
                }
            }
        }
        return RespBean.ok("管理员操作加载完毕！", operationResponseList);
    }

    @Override
    public Activateemp getInfoByEmpId(long empId) {
        return activateempDao.getInfoByEmpId(empId);
    }

    @Override
    public int insertActivatemp(Activateemp activateemp) {
        return activateempDao.insertActivatemp(activateemp);
    }

    @Override
    public Activateemp judgeUserName(String username) {
        return activateempDao.loadUserByUsername(username);
    }

    @Override
    public int myselfUpdate(MyselfUpdateRequest myselfUpdateRequest){
        return activateempDao.myselfUpdate(myselfUpdateRequest);
    }

    @Override
    public int passwordUpdate(Long empId,String name,String password){
        return activateempDao.passwordUpdate(empId,name,password);
    }
    @Override
    public int updatePhoto(long empId,String name,String photoUrl){
        return activateempDao.updatePhoto(empId,name,photoUrl);
    }
}
