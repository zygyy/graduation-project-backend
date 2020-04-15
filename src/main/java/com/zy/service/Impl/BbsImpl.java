package com.zy.service.Impl;

import com.zy.entity.Bbs;
import com.zy.mapper.BbsDao;
import com.zy.service.BbsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class BbsImpl
 * @create 2020-04-10 13:44
 */
@Service
public class BbsImpl implements BbsService {
    @Resource
    BbsDao bbsDao;

    @Override
    public List<Bbs> getBbs(int empId) {
        return bbsDao.getBbs(empId);
    }

    @Override
    public int deteted(int id) {
        return bbsDao.deteted(id);
    }

    @Override
    public int add(Bbs bbs) {
        return bbsDao.add(bbs);
    }

    @Override
    public Bbs getBBSById(int id) {
        return bbsDao.getBBSById(id);
    }

    @Override
    public int updateBBS(Bbs bbs) {
        return bbsDao.updateBBS(bbs);
    }

    @Override
    public List<Bbs> getAllBBSByQuery(String query) {
        return bbsDao.getAllBBSByQuery(query);
    }

    @Override
    public int deletedbbsByAdmin(int id) {
        return bbsDao.deletedbbsByAdmin(id);
    }


}
