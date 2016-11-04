package com.llf.springboot.dao;


import com.llf.springboot.model.User;

public interface UserDao {

//    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}