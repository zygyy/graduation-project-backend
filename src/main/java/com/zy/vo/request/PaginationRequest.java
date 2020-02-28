package com.zy.vo.request;

import lombok.Data;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class PaginationRequest
 * @create 2020-02-21 17:08
 */
@Data
public class PaginationRequest {
    private String query;
    private int pagenum;
    private int pagesize;
}
