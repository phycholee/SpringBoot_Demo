package com.llf.springboot.service;

import com.llf.springboot.model.User;

import java.sql.SQLException;

/**
 * Created by PhychoLee on 2016/11/4 17:15.
 * Description: 用户Service接口
 */
public interface UserService {

    User findById(Integer id) throws SQLException;
}