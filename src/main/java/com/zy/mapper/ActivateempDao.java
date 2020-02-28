package com.zy.mapper;

import com.zy.entity.Activateemp;
import com.zy.entity.Operation;
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



}
