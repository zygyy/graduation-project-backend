package com.zy.service;

import com.zy.vo.base.RespBean;
import com.zy.vo.request.LoginRequest;

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
}
