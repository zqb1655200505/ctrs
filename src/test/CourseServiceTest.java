import com.zqb.IDao.CourseInfoMapper;
import com.zqb.IDao.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.security.RunAs;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-service.xml"})
public class CourseServiceTest {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseInfoMapper courseInfoMapper;
    @Test
    public void getAllCourse() throws Exception {
        //System.out.println(courseMapper.selectAll().toString());
        System.out.println(courseInfoMapper.selectAll().toString());
    }

}