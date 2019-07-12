package com.rainbow.machinelearning.service.impl;

import com.rainbow.machinelearning.dao.UserDao;
import com.rainbow.machinelearning.model.User;
import com.rainbow.machinelearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ${xiami}
 * @version $Id: UserSerivceImpl.java, v 0.1 2019年05月09日 14:59 Exp $
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UserDao userDao;
    @Override
    public void insert(User user) {
        userDao.insert(user);
        //重点在于此处对事务进行侦听
        publisher.publishEvent(user);
    }
}
