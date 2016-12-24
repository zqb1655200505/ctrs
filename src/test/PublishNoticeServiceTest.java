import com.zqb.IDao.NoticeMapper;
import com.zqb.domain.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zqb on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-service.xml"})
public class PublishNoticeServiceTest {

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    public void publishNotice() throws Exception {
        //int res=noticeMapper.addNotice(2,"qwqw","<p>1212</p>","2016/12/19");
        //System.out.println(res);
        //Notice notice=noticeMapper.selectByPrimaryKey(1);
        //System.out.println(notice.toString());
    }

    @Test
    public void getNotice() throws Exception{
        System.out.println(noticeMapper.getNotice().toString());
    }

}