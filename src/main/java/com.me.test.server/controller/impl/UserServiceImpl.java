package com.me.test.server.controller.impl;

import com.me.test.server.bean.UserEntity;
import com.me.test.server.controller.UserService;
import com.me.test.server.dao.UserMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

@Api("/user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserEntity queryByUserId(String username) {
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(userMapper);
        return userMapper.getOne(username);
    }

    public Response updateUser(UserEntity user) {
        int res = userMapper.update(user);
        Response r;
        //更新的行数为1行
        if (res == 1) {
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }
        return r;
    }

    public Response addUser(UserEntity user) {
        int res = userMapper.insert(user);
        Response r;
        if (res == 1) {
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }
        return r;
    }

    public Response delUser(@FormParam("id") Long id) {
        int res = userMapper.delete(id);
        Response r;
        //删除的行数为1行
        if (res == 1) {
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }
        return r;
    }
}
