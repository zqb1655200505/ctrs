package com.zqb.service;

import com.zqb.IDao.CourseInfoMapper;
import com.zqb.IDao.CourseMapper;
import com.zqb.domain.Course;
import com.zqb.domain.CourseInfo;
import com.zqb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zqb on 2016/12/19.
 */
@Service
public class CourseService {

    @Autowired
    protected CourseMapper courseMapper;

    @Autowired
    protected CourseInfoMapper courseInfoMapper;

    public List<Course>getAllCourse()
    {
        return courseMapper.selectAll();
    }

    public int addCourse(String course_name, String remark, HttpServletRequest request)
    {
        User user=(User)request.getSession().getAttribute("user");
        return courseMapper.addCourse(course_name,user.getUserId(),remark);
    }

    public List<CourseInfo> getAllCourseInfo()
    {
        return courseInfoMapper.selectAll();
    }
}
