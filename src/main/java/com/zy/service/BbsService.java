package com.zy.service;

import com.zy.entity.Bbs;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class BbsService
 * @create 2020-04-10 13:43
 */
public interface BbsService {
    /**
     * 根据empId获取信息
     *
     * @param empId
     * @return
     */
    public List<Bbs> getBbs(int empId);

    /**
     * 根据id删除文章
     *
     * @param id
     * @return
     */
    public int deteted(int id);

    /**
     * 添加文章
     *
     * @param bbs
     * @return
     */
    public int add(Bbs bbs);

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    public Bbs getBBSById(int id);

    /**
     * 根据id修改文章
     *
     * @param bbs
     * @return
     */
    public int updateBBS(Bbs bbs);
    /**
     * 模糊查询所有的bbs
     *
     * @param query
     * @return
     */
    public List<Bbs> getAllBBSByQuery(String query);

    /**
     * 管理员删除文章（物理删除）
     *
     * @param id
     * @return
     */
    public int deletedbbsByAdmin(int id);
}
