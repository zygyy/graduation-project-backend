package com.zy.service;


import com.zy.entity.Department;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.DeleteDescribeRequest;

import java.util.List;


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

    public  int updateGrade(int id,String name);

    public Department getUpdateDepartment(int id);

    public RespBean getDepartmentsNotLevel2();

    public int addGrade(String name,int pId,String describes,String scale);

    Department getDepartmentByNameAndPId(String name,int pId);

    List<Department> departmentAndNumber();
}
