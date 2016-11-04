package com.llf.springboot.controller;

import com.llf.springboot.model.User;
import com.llf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by PhychoLee on 2016/10/13.
 * Description:
 */
@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public User getUser(){
        User user =null;
        try {
            user = userService.findById(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
