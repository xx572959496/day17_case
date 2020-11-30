import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import org.junit.jupiter.api.Test;

public class DaoTest {
    @Test
    public void loginTest(){
        UserDaoImpl userDao = new UserDaoImpl();
        //userDao.findUseByUsernameAndPassword("zhangsan", "1111");
        //System.out.println(zhangsan);
        User user = new User();
        user.setName("张三");
        user.setGender("男");
        user.setAddress("北京");
        user.setQq("123456");
        user.setEmail("zhangsan@qq.com");
        user.setAge(23);
        user.setId(1);
        userDao.update(user);
    }
}
