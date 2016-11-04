package com.llf.springboot.controller;

import com.llf.springboot.dao.UserDao;
import com.llf.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PhychoLee on 2016/10/13.
 * Description:
 */
@RestController
public class HelloController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public User getUser(){
        return userDao.findById(1);
    }


}
