import com.zqb.service.StudentCourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2016/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-service.xml"})
public class StudentCourseServiceTest {

    @Autowired
    StudentCourseService studentCourseService;

    @Test
    public void addStudentCourse() throws  Exception{
        System.out.println(studentCourseService.addStudentCourse(1,15));
    }
    @Test
    public void selectByCourseId() throws Exception {
        System.out.println(studentCourseService.selectByCourseId(1).toString());
    }

    @Test
    public void selectByUserId() throws Exception {
        System.out.println(studentCourseService.selectByUserId(1).toString());
    }

    @Test
    public void deleteStudent (int userId,int courseId)
    {
        studentCourseService.deleteStudent(userId,courseId);
    }

    @Test
    public void batchDeleteStudent()
    {
        int res=studentCourseService.batchDeleteStudent(2,"2");
        System.out.println(res);
    }
}