package com.zy.service;

import com.zy.vo.base.RespBean;
import com.zy.vo.request.DeleteDescribeRequest;
import com.zy.vo.request.LoginRequest;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class ActivateempService
 * @create 2020-02-15 21:29
 */
public interface DepartmentService {

    /**
     * 获取部门和相应的职位
     *
     * @return
     */
    public RespBean getDepartments();

    public RespBean getGrades();

    public  RespBean deleteGrade(int id);

    public int  deleteGradeDescribe(DeleteDescribeRequest deleteDescribeRequest);

}
