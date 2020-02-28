package com.zy.service.Impl;

import com.zy.entity.Department;
import com.zy.mapper.DepartmentDao;
import com.zy.service.DepartmentService;
import com.zy.vo.base.RespBean;
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



    @Override
    public RespBean getDepartments() {
        List<Department> departmentList=departmentDao.getDepartments();
        List<Department> departmentResult=new ArrayList<Department>();
        for(Department department:departmentList){
            //如果父节点为0,则为一级
            if(department.getPId()==0){
                departmentResult.add(department);
            }
            for(Department departmentSecond:departmentList){
                if(departmentSecond.getPId()==department.getId()){
                    if(department.getFirstChildren()==null){
                        department.setFirstChildren(new ArrayList<Department>());
                    }
                        department.getFirstChildren().add(departmentSecond);
                }
            }
        }
        return RespBean.ok("加载完成！",departmentResult);

    }
}
