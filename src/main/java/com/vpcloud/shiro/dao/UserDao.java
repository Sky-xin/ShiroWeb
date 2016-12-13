package com.vpcloud.shiro.dao;

import java.util.List;

import com.vpcloud.shiro.pojo.User;

public interface UserDao {

    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);

}
