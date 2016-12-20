package com.zqb.IDao;

import com.zqb.domain.CourseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zqb on 2016/12/20.
 */
public interface CourseInfoMapper {
    List<CourseInfo> selectAll();


    List<CourseInfo>selectByKeyword(@Param("keyWord") String keyWord);
}
