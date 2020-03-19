package com.zy.service;

import com.zy.entity.Activateemp;
import com.zy.entity.Department;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.LoginRequest;
import com.zy.vo.request.MyselfUpdateRequest;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class ActivateempService
 * @create 2020-02-15 21:29
 */
public interface ActivateempService {

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    public RespBean login(LoginRequest loginRequest)throws Exception;

    /**
     * 登录之后获取对应的操作
     * @return
     */
    public RespBean getOperations();

    public Activateemp getInfoByEmpId(long empId);

    public int insertActivatemp(Activateemp activateemp);

    public Activateemp judgeUserName(String username);

    public int myselfUpdate(MyselfUpdateRequest myselfUpdateRequest);

    public int passwordUpdate(Long empId,String name,String password);

}
