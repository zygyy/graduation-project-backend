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

    Employee getEmployeeByGradeId(int id);

    int  deleteGradeDescribe(DeleteDescribeRequest deleteDescribeRequest);
}
