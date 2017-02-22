import com.zqb.IDao.UserMapper;
import com.zqb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zqb on 2016/12/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
//    @Test
//    public void selectByPrimaryKey() throws Exception {
//        User user = userMapper.selectByPrimaryKey(1);
//        System.out.print(user.toString());
//    }

    @Test
    public void checkUser() throws Exception{
        User user = userMapper.checkUser("郑泉斌");
        if(user!=null)
            System.out.print(user.toString());
    }

    @Test
    public void getAllStudents()
    {
        List<User> list=userMapper.getAllStudents();
        System.out.println(list);
    }
}