package com.zy.mapper;

import com.zy.entity.Department;
import com.zy.entity.Employee;
import com.zy.vo.request.DeleteDescribeRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class DepartmentDao
 * @create 2020-02-25 14:10
 */
@Repository
public interface DepartmentDao {

    /**
     * 获取部门和相应的职位
     *
     * @return
     */
    List<Department> getDepartments();

    /**
     * 根据id删除level为2的职位
     *
     * @param id
     * @return
     */
    int deleteGrade(int id);

    /**
     * 根据部门id获取部门名称，再根据名称查找员工表
     * @param id
     * @return
     */
    Employee getEmployeeByGradeId(int id);


    /**
     * 删除职位描述
     * @param deleteDescribeRequest
     * @return
     */
    int  deleteGradeDescribe(DeleteDescribeRequest deleteDescribeRequest);

    /**
     * 修改职位名称
     * @param id
     * @param name
     * @return
     */
    int updateGrade(int id,String name);


    /**
     * 新增部门
     * @param name
     * @param pId
     * @param describes
     * @param scale
     * @return
     */
    int addGrade(String name,int pId,String describes,String scale);

    /**
     * 根据名称和pId寻找部门信息
     * @param name
     * @param pId
     * @return
     */
    Department getDepartmentByNameAndPId(String name,int pId);

    /**
     * 根据level获取对应的职位
     * @param level1
     * @return
     */
    List<String> getGradeTotal(String level1);
}
