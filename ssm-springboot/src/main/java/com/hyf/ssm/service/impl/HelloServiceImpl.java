package com.hyf.ssm.service.impl;

import com.hyf.ssm.mapper.HelloMapper;
import com.hyf.ssm.pojo.Hello;
import com.hyf.ssm.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@Service
public class HelloServiceImpl implements IHelloService {

    @Autowired
    private HelloMapper helloMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Hello hello(Integer id) {
        return helloMapper.hello(id);
    }
}
