import com.zqb.IDao.ResourceMapper;
import com.zqb.IDao.UserMapper;
import com.zqb.domain.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        resourceMapper.addResource(3,2,2,time,"F:\\javaEE\\learnMaven\\target\\ssm-1.0-SNAPSHOT\\WEB-INF\\upload\\resource/实验六实验报告-磁盘调度.docx",0,"");
    }

    @Test
    public void getResourceBycourseId()
    {
        List<Resource> res_list=resourceMapper.getResourceByCourseId(2);
        System.out.println(res_list);
    }

    @Test
    public void updateDownload()
    {
        int res=resourceMapper.updateDownload(3);
        System.out.println(res);
    }
}