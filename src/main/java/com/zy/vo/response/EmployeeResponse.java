package com.zy.vo.response;

import com.zy.entity.Employee;
import lombok.Data;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class EmployeeResponse
 * @create 2020-02-21 18:19
 */
@Data
public class EmployeeResponse {
    private int  totalpage;
    private List<Employee> employees;
}
