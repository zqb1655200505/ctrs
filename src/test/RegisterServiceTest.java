import com.zqb.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2016/12/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-service.xml"})
public class RegisterServiceTest {

    @Autowired
    RegisterService registerService;
//    @Test
//    public void registerCheck() throws Exception {
//        if(registerService.registerCheck("zqb"))
//        {
//            System.out.println("用户不存在，可以注册该用户");
//        }
//        else
//        {
//            System.out.println("该用户已存在");
//        }
//    }

    @Test
    public void registerNewUser() throws Exception {
        if(registerService.registerNewUser("zzz","123456"))
        {
            System.out.println("注册成功");
        }
        else
        {
            System.out.println("注册失败");
        }
    }

}