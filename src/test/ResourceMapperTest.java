import com.zqb.IDao.ResourceMapper;
import com.zqb.IDao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2017/2/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class ResourceMapperTest {

    @Autowired
    private ResourceMapper resourceMapper;
    @Test
    public void addResource() throws Exception {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        resourceMapper.addResource(3,2,2,time,"qwqweqwe",0,"");
    }

}