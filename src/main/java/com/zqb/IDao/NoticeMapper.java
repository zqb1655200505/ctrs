package com.zqb.IDao;

import com.zqb.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zqb on 2016/12/19.
 */
public interface NoticeMapper
{
    Notice selectByPrimaryKey(@Param("noticeId") int noticeId);

    /**
     * @author zqb
     * @param
     * @return 插入成功后当前id
     * @Date: 14:39 2016/12/19
     */
    int addNotice(@Param("userId") int userId,
                  @Param("title") String title,
                  @Param("content") String content,
                  @Param("publishTime") String publishTime);

    List<Notice>getNotice();
}
