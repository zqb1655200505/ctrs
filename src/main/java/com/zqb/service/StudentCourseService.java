package com.zqb.service;

import com.zqb.IDao.StudentCourseMapper;
import com.zqb.domain.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zqb on 2016/12/20.
 */
@Service
public class StudentCourseService {

    @Autowired
    StudentCourseMapper studentCourseMapper;

    public List<StudentCourse> selectByCourseId(int courseId)
    {
        return studentCourseMapper.selectByCourseId(courseId);
    }


    public List<StudentCourse> selectByUserId(int userId)
    {
        return studentCourseMapper.selectByUserId(userId);
    }

    public int addStudentCourse(int userId,int courseId)
    {
        return studentCourseMapper.addStudentCourse(userId,courseId);
    }


    public int deleteStudent(int userId,int courseId)
    {
        return studentCourseMapper.deleteStudent(userId,courseId);
    }
}
