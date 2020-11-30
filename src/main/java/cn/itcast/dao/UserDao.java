package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    public User findUseByUsernameAndPassword(String username, String password);

    public User addNewUser(String name , String gender , int age , String address , String qq , String email , String username , String password);

    void delete(int id);

    User findUserByID(int id);

    void update(User user);
}
