package com.zy.service.Impl;

import com.zy.mapper.OperationDao;
import com.zy.service.OperationService;
import com.zy.vo.Bean.Operation;
import com.zy.vo.request.AddRightsRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description
 * @class OperationImpl
 * @create 2020-04-02 12:45
 */
@Service
public class OperationImpl implements OperationService {
    @Resource
    OperationDao operationDao;

    @Override
    public List<Operation> getUserOperation() {
        return operationDao.getUserOperation();
    }


    @Override
    public int addRights(AddRightsRequest addRightsRequest) {
        return operationDao.addRights(addRightsRequest);
    }
}
