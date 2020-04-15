package com.zy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class Bbs
 * @create 2020-04-10 10:43
 */
@Data
public class Bbs {
    private int id;
    private int empId;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date publishTime;
    private int isDeleted;
    private String chineseName;
}
