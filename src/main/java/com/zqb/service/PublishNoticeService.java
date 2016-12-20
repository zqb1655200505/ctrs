package com.zqb.service;

import com.zqb.IDao.NoticeMapper;
import com.zqb.domain.User;
import com.zqb.util.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zqb on 2016/12/19.
 */
@SessionAttributes("user")
@Service
public class PublishNoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    public Map<String,Object> publishNotice(String title, String content, HttpServletRequest request)
    {
        Map<String,Object> resultMap=new HashMap<String, Object>();
        if(title.length()<=0)
        {
            resultMap.put("code",500);
            resultMap.put("msg","标题不能为空");
            return resultMap;
        }
        if(content.length()<=0)
        {
            resultMap.put("code",500);
            resultMap.put("msg","标题不能为空");
            return resultMap;
        }
        if(CheckParam.CheckParams(title))
        {
            resultMap.put("code",500);
            resultMap.put("msg","标题存在危险字符");
            return resultMap;
        }
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user!=null&&user.isUserType())
        {
            Date date=new Date();
            SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
            int id=noticeMapper.addNotice(user.getUserId(),title,content,format.format(date));
            if(id>0)
            {
                resultMap.put("code",200);
                resultMap.put("msg","发布公告成功");
            }
            else
            {
                resultMap.put("code",500);
                resultMap.put("msg","发布公告失败");
            }
            return resultMap;
        }
        else
        {
            resultMap.put("code",500);
            resultMap.put("msg","请使用管理员账户登录");
        }
        return resultMap;
    }
}
