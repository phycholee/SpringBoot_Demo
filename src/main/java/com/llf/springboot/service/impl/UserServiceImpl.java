package com.llf.springboot.service.impl;

import com.llf.springboot.dao.UserDao;
import com.llf.springboot.model.User;
import com.llf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by PhychoLee on 2016/11/4 17:18.
 * Description: 用户service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Integer id) throws SQLException {
        return userDao.findById(id);
    }
}
