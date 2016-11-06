package com.llf.springboot.test;

import com.llf.springboot.Application;
import com.llf.springboot.model.User;
import com.llf.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhychoLee on 2016/11/4 10:49.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestUser {
    @Autowired
    private UserService userService;

    @Test
    public void testFind() throws SQLException {
        User user = userService.findById(1);
        System.out.println(user);
    }

    @Test
    public void testInsert() throws SQLException {
        User user = new User();
        user.setName("AA");

        User user1 = new User();
        user1.setName("BB");

        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user1);

        userService.insertList(userList);
    }
}
