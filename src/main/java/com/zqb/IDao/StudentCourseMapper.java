package com.zqb.IDao;

import com.zqb.domain.StudentCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zqb on 2016/12/20.
 */
@Repository
public interface StudentCourseMapper {
    List<StudentCourse> selectByUserId(@Param("userId") int userId);

    List<StudentCourse> selectByCourseId(@Param("courseId") int courseId);

    int addStudentCourse(@Param("userId") int userId,
                         @Param("courseId") int courseId);

    int deleteStudent(@Param("userId") int userId,@Param("courseId")int courseId);

    StudentCourse checkExist(@Param("userId") int userId,
                             @Param("courseId") int courseId);
    int batchDeleteStudent(@Param("courseId") int courseId,
                           @Param("userIds") String userIds);
}
