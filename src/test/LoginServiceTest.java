import com.zqb.domain.User;
import com.zqb.service.LoginService;
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
public class LoginServiceTest {
    @Autowired
    private LoginService loginService;
    @Test
    public void doLogin() throws Exception {
        User user=loginService.doLogin("zqb","123456");//loginService.doLogin("zqb","123456");
        System.out.print(user.toString());
    }

}