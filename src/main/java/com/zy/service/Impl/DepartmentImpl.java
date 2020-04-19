package com.zy.service.Impl;

import com.zy.entity.Department;
import com.zy.entity.Employee;
import com.zy.mapper.DepartmentDao;
import com.zy.service.DepartmentService;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.DeleteDescribeRequest;
import com.zy.vo.request.PaginationRequest;
import com.zy.vo.response.RightsGradeResopnse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class DepartmentImpl
 * @create 2020-02-25 14:15
 */
@Service
public class DepartmentImpl implements DepartmentService {
    @Resource
    DepartmentDao departmentDao;

    /**
     * 得到部门的树形图
     * @return
     */
    @Override
    public RespBean getDepartments() {
        List<Department> departmentList = departmentDao.getDepartments();
        List<Department> departmentResult = new ArrayList<Department>();
        for (Department department : departmentList) {
            //如果父节点为0,则为一级
            if (department.getPId() == 0) {
                departmentResult.add(department);
            }
            for (Department departmentSecond : departmentList) {
                if (departmentSecond.getPId() == department.getId()) {
                    if (department.getFirstChildren() == null) {
                        department.setFirstChildren(new ArrayList<Department>());
                    }
                    department.getFirstChildren().add(departmentSecond);
                }
            }
        }
        return RespBean.ok("加载完成！", departmentResult);

    }

    /**
     * 得到形如：部门-总部门-职位-描述的形式
     * @return
     */
    @Override
    public RespBean getGrades() {
        List<Department> departmentList = departmentDao.getDepartments();
        List<Department> departmentResult2 = new ArrayList<Department>();
        List<Department> departmentResult1 = new ArrayList<Department>();
        List<Department> departmentResult0 = new ArrayList<Department>();
        for (Department department : departmentList) {
            if (department.getLevel() == 2) {
                departmentResult2.add(department);
            }
        }
        for (Department department : departmentList) {
            if (department.getLevel() == 1) {
                departmentResult1.add(department);
            }
        }
        for (Department department : departmentList) {
            if (department.getLevel() == 0) {
                departmentResult0.add(department);
            }
        }
        for (Department second : departmentResult2) {
            for (Department one : departmentResult1) {
                if (second.getPId() == one.getId()) {
                    second.setDepartment(one);
                    for (Department zero : departmentResult0) {
                        if (zero.getId() == one.getPId()) {
                            one.setDepartment(zero);
                        }
                    }
                }
            }
        }
        for (Department result : departmentResult2) {
            result.setDescribesSplit(result.getDescribes().split("，"));
        }


        return RespBean.ok("加载完成！", departmentResult2);
    }

    @Override
    public RespBean deleteGrade(int id) {
        List<Employee> employee = departmentDao.getEmployeeByGradeIdSecond(id);
        if (employee.size() != 0) {
            return RespBean.error("删除失败！");
        } else {
            int result = departmentDao.deleteGrade(id);
            if (result > 0) {
                return RespBean.okMessage("删除成功！");
            } else {
                return RespBean.error("删除失败！");
            }

        }
    }

    @Override
    public int deleteGradeDescribe(DeleteDescribeRequest deleteDescribeRequest) {
        return departmentDao.deleteGradeDescribe(deleteDescribeRequest);
    }


    @Override
    public int updateGrade(int id, String name) {
        return departmentDao.updateGrade(id, name);

    }

    @Override
    public Department getUpdateDepartment(int id) {
        List<Department> departmentList = departmentDao.getDepartments();
        Department result = new Department();
        for (Department one : departmentList) {
            if (one.getId() == id) {
                for (Department second : departmentList) {
                    if (one.getPId() == second.getId()) {
                        one.setDepartment(second);
                        for (Department thrid : departmentList) {
                            if (second.getPId() == thrid.getId()) {
                                second.setDepartment(thrid);
                            }
                        }
                    }
                }
            }
        }
        for (Department one : departmentList) {
            if (one.getId() == id) {
                result = one;
            }
        }
        return result;
    }

    /**
     * 获取总部门以及子部门，形成树结构
     * @return
     */
    @Override
    public RespBean getDepartmentsNotLevel2() {
        List<Department> departmentList = departmentDao.getDepartments();
        List<Department> departments = new ArrayList<Department>();
        List<Department> departmentResult = new ArrayList<Department>();
        for (Department department : departmentList) {
            if (department.getLevel() != 2) {
                departments.add(department);
            }
        }
        for (Department department : departments) {
            //如果父节点为0,则为一级
            if (department.getPId() == 0) {
                departmentResult.add(department);
            }
            for (Department departmentSecond : departments) {
                if (departmentSecond.getPId() == department.getId()) {
                    if (department.getFirstChildren() == null) {
                        department.setFirstChildren(new ArrayList<Department>());
                    }
                    department.getFirstChildren().add(departmentSecond);
                }
            }
        }
        return RespBean.ok("加载完成！", departmentResult);
    }

    @Override
    public int addGrade(String name, int pId, String describes, String scale) {
        return departmentDao.addGrade(name, pId, describes, scale);
    }

    @Override
    public Department getDepartmentByNameAndPId(String name, int pId) {
        return departmentDao.getDepartmentByNameAndPId(name, pId);
    }

    /**
     * 获取总部门以及子部门，形成链结构
     * @return
     */
    @Override
    public List<Department> departmentAndNumber() {
        List<Department> departmentList = departmentDao.getDepartments();
        List<Department> departmentResult1 = new ArrayList<Department>();
        List<Department> departmentResult0 = new ArrayList<Department>();
        for (Department department : departmentList) {
            if (department.getLevel() == 1) {
                departmentResult1.add(department);
            }
        }
        for (Department department : departmentList) {
            if (department.getLevel() == 0) {
                departmentResult0.add(department);
            }
        }
        for (Department one : departmentResult1) {
            for (Department zero : departmentResult0) {
                if (zero.getId() == one.getPId()) {
                    one.setDepartment(zero);
                }
            }
        }
        return departmentResult1;
    }

    @Override
    public List<String> getGradeTotal(String level1){
        return departmentDao.getGradeTotal(level1);

    }


    //分配权限使用的方法
    /**
     * 分页查询职位列表
     *
     * @param paginationRequest
     * @return
     */
    @Override
    public RespBean getGradesByPagination(PaginationRequest paginationRequest){
        //去除节点为0和1的部门
        List<Department> departmentList = departmentDao.getDepartments();
        //单独去除节点为2的职位
        List<Department> departmentListLevel2=departmentDao.getGradesByPagination(paginationRequest);
        int total=departmentDao.totalGradeByPagination(paginationRequest.getQuery());
        List<Department> departmentResult1 = new ArrayList<Department>();
        List<Department> departmentResult0 = new ArrayList<Department>();
        for (Department department : departmentList) {
            if (department.getLevel() == 1) {
                departmentResult1.add(department);
            }
        }
        for (Department department : departmentList) {
            if (department.getLevel() == 0) {
                departmentResult0.add(department);
            }
        }
        for (Department second : departmentListLevel2) {
            for (Department one : departmentResult1) {
                if (second.getPId() == one.getId()) {
                    second.setDepartment(one);
                    for(Department zero:departmentResult0){
                        if(zero.getId()==one.getPId()){
                            one.setDepartment(zero);
                        }
                    }
                }
            }
        }

        RightsGradeResopnse rightsGradeResopnse= new RightsGradeResopnse();
        rightsGradeResopnse.setDepartmentList(departmentListLevel2);
        rightsGradeResopnse.setTotal(total);
        return RespBean.ok("加载完成！", rightsGradeResopnse);
    }

}
