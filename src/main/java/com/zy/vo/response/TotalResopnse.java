package com.zy.vo.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class TotalResopnse
 * @create 2020-03-08 17:46
 */
@Data
public class TotalResopnse {
    private List<String> department;
    private List<Integer> number;
}
