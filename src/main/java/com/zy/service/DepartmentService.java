package com.zy.service;


import com.zy.entity.Department;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.DeleteDescribeRequest;
import com.zy.vo.request.PaginationRequest;

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
     * 得到部门的树形图
     *
     * @return
     */
    public RespBean getDepartments();

    /**
     * 得到形如：部门-总部门-职位-描述的形式
     *
     * @return
     */
    public RespBean getGrades();

    /**
     * 删除职位
     *
     * @param id
     * @return
     */
    public RespBean deleteGrade(int id);

    /**
     * 删除对改职位的评论(实际是修改评论)
     *
     * @param deleteDescribeRequest
     * @return
     */
    public int deleteGradeDescribe(DeleteDescribeRequest deleteDescribeRequest);

    /**
     * 修改职位名称
     *
     * @param id
     * @param name
     * @return
     */
    public int updateGrade(int id, String name);

    /**
     * 更新职位名称时，先要获取其部门信息，形成链式结构
     *
     * @param id
     * @return
     */
    public Department getUpdateDepartment(int id);

    /**
     * 获取部门信息（level为0和1的），形成树结构
     *
     * @return
     */
    public RespBean getDepartmentsNotLevel2();

    /**
     * 添加新职位
     *
     * @param name
     * @param pId
     * @param describes
     * @param scale
     * @return
     */
    public int addGrade(String name, int pId, String describes, String scale);

    /**
     * 添加新职位时，先判断是否已经存在
     *
     * @param name
     * @param pId
     * @return
     */
    Department getDepartmentByNameAndPId(String name, int pId);

    /**
     * 获取总部门以及子部门，形成链结构
     *
     * @return
     */
    List<Department> departmentAndNumber();

    /**
     * 根据level获取对应的职位
     *
     * @param level1
     * @return
     */
    List<String> getGradeTotal(String level1);


    //分配权限使用的方法

    /**
     * 分页查询职位列表
     *
     * @param paginationRequest
     * @return
     */
    public RespBean getGradesByPagination(PaginationRequest paginationRequest);

}
