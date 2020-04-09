package com.zy.service;

import com.zy.entity.Activateemp;
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
     *
     * @param loginRequest
     * @return
     */
    public RespBean login(LoginRequest loginRequest) throws Exception;

    /**
     * 登录之后获取对应的操作
     *
     * @return
     */
    public RespBean getOperations();

    /**
     * 根据empId获取激活的用户信息
     *
     * @param empId
     * @return
     */
    public Activateemp getInfoByEmpId(long empId);

    /**
     * 用户注册（把用户信息插入激活表）
     *
     * @param activateemp
     * @return
     */
    public int insertActivatemp(Activateemp activateemp);

    /**
     * 根据username查找，为了判断是否username重复
     *
     * @param username
     * @return
     */
    public Activateemp judgeUserName(String username);

    /**
     * 自己信息的更新操作
     *
     * @param myselfUpdateRequest
     * @return
     */
    public int myselfUpdate(MyselfUpdateRequest myselfUpdateRequest);

    /**
     * 修改密码操作
     *
     * @param empId
     * @param name
     * @param password
     * @return
     */
    public int passwordUpdate(Long empId, String name, String password);

    /**
     * 修改头像操作
     *
     * @param empId
     * @param name
     * @param photoUrl
     * @return
     */
    public int updatePhoto(long empId, String name, String photoUrl);


    /**
     * 离职时顺便删除以及激活的账号
     *
     * @param empId
     * @return
     */
    public int deleteEmployee(long empId);
}
