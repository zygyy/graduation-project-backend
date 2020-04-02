package com.zy.vo.response;

import com.zy.entity.Department;
import lombok.Data;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class RightsGradeResopnse
 * @create 2020-04-01 21:35
 */
@Data
public class RightsGradeResopnse {
    private int total;
    private List<Department> departmentList;
}
