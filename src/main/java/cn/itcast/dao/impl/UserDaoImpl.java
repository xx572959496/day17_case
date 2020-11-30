package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        String sql = "select * from day17.user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUseByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from day17.user where username = ? and password = ?";
            User user =
                    jdbcTemplate.queryForObject
                            (sql , new BeanPropertyRowMapper<User>(User.class)
                                    , username , password);
            //System.out.println(user);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User addNewUser(String name, String gender, int age, String address, String qq, String email, String username, String password) {
        try {
            String sql = "INSERT INTO day17.user(name , gender , age , address , qq , email , username , password) VALUES (?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql,name,gender,age,address,qq,email,username,password);
            User user = new User();
            user.setName(name);
            user.setGender(gender);
            user.setAge(age);
            user.setAddress(address);
            user.setQq(qq);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            return user;
        }catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM day17.user WHERE id = ?";
        jdbcTemplate.update(sql , id);
    }

    @Override
    public User findUserByID(int id) {
        try {
            String sql = "select * from day17.user where id = ?";
            User user = jdbcTemplate.queryForObject(sql , new BeanPropertyRowMapper<User>(User.class),id);
            //System.out.println(user);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE day17.user SET name = ? , gender = ? , age = ? , address = ? , qq = ? , email = ? WHERE id = ?";
            jdbcTemplate.update(sql , user.getName() , user.getGender() , user.getAge() , user.getAddress() , user.getQq() , user.getEmail() , user.getId());
            System.out.println("修改后"+user);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
