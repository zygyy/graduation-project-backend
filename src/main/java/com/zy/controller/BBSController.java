package com.zy.controller;

import com.zy.entity.Bbs;
import com.zy.service.BbsService;
import com.zy.vo.base.RespBean;
import com.zy.vo.request.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class BBSController
 * @create 2020-04-13 18:31
 */
@Api("对BBS的操作")
@RestController
@RequestMapping("/bbs")
public class BBSController {
    @Autowired
    BbsService bbsService;

    @ApiOperation(value = "获取所有的bbs")
    @PostMapping("/getAllBBS")
    public RespBean getAllBBS(@RequestBody Query query){
       List<Bbs> bbsList=bbsService.getAllBBSByQuery(query.getQuery());
       return RespBean.ok("数据获取成功！",bbsList);
    }

    @ApiOperation(value = "获取所有的bbs")
    @DeleteMapping("/deletedBBSByAdmin/{id}")
    public RespBean deletedBBSByAdmin(@PathVariable int id){
        int result=bbsService.deletedbbsByAdmin(id);
        if(result>0) {
            return RespBean.okMessage("删除成功！");
        }else {
            return RespBean.error("删除成功！");
        }
    }

}
