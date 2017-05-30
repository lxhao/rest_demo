package com.me.test.server.dao;

import com.me.test.server.bean.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> getAll();

    UserEntity getOne(String username);

    int insert(UserEntity user);

    int update(UserEntity user);

    int delete(Long id);

    int deleteAll();
}