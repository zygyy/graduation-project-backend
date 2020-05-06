package com.zy.mapper;

import com.zy.entity.Activateemp;
import com.zy.entity.Operation;
import com.zy.vo.request.MyselfUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 管理员用户登录
 * @class Login
 * @create 2020-02-15 18:48
 */
@Repository
public interface ActivateempDao {

    /**
     * 用户和管理员登录
     *
     * @param username
     * @return
     */
    Activateemp loadUserByUsername(String username);

    /**
     * 管理员获取操作
     *
     * @return
     */
    List<Operation> getOperationByAdmin();

    /**
     * 根据员工号查看注册的信息
     *
     * @param empId
     * @return
     */
    public Activateemp getInfoByEmpId(long empId);

    /**
     * 注册
     *
     * @param activateemp
     * @return
     */
    public int insertActivatemp(Activateemp activateemp);

    /**
     * 管理员修改个人基本信息
     *
     * @param myselfUpdateRequest
     * @return
     */
    public int myselfUpdate(MyselfUpdateRequest myselfUpdateRequest);

    /**
     * 同步更新激活表信息
     *
     * @param empId
     * @param chineseName
     * @param phone
     * @param address
     * @return
     */
    public int employeeUpdate(Long empId,String chineseName,String phone,String address);


    /**
     * 管理员修改密码
     *
     * @param empId
     * @param name
     * @param password
     * @return
     */
    public int passwordUpdate(Long empId, String name, String password);

    /**
     * 根据empId和姓名更新头像地址
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
