package cn.itcast.service;

import cn.itcast.domain.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User login(User user);

    public User add(User user);

    void delete(String id);

    User findUserByID(String id);

    void update(User user);
}
