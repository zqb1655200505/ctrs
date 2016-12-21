package com.zqb.IDao;

import com.zqb.domain.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zqb on 2016/12/19.
 */
public interface CourseMapper {
    List<Course> selectAll();

    int addCourse(@Param("courseName")String courseName,
                  @Param("userId")int userId,
                  @Param("remark")String remark);

    Course selectByPrimaryKey(@Param("courseId") int courseId);

    int deleteCourse(@Param("courseId") int courseId);
}
