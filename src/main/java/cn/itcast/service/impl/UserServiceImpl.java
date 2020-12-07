package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUseByUsernameAndPassword(user.getUsername() , user.getPassword());
    }

    @Override
    public User add(User user) {
        return dao.addNewUser(user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getUsername(), user.getPassword());
    }

    @Override
    public void delete(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserByID(String id) {
        return dao.findUserByID(Integer.parseInt(id));
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectUsers(String[] uids) {
        for (String uid : uids) {
            dao.delete(Integer.parseInt(uid));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        PageBean<User> pageBean = new PageBean<User>();

        return null;
    }
}
