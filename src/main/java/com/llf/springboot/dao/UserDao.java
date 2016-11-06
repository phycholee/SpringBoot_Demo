package com.llf.springboot.dao;


import com.llf.springboot.model.User;

import java.sql.SQLException;

public interface UserDao {

//    @Select("select * from user where id = #{id}")
    User findById(Integer id) throws SQLException;

    void insert(User user) throws SQLException;
}