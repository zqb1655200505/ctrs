package com.zqb.IDao;

import com.zqb.domain.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zqb on 2016/12/22.
 */
public interface ResourceMapper {
    int addResource(@Param("resourceType") int resourceType,
                    @Param("userId") int userId,
                    @Param("courseId") int courseId,
                    @Param("uploadTime") String uploadTime,
                    @Param("savePath") String savePath,
                    @Param("downloadTimes") int download_times,
                    @Param("remark") String remark);
    List<Resource> getResourceByCourseId(@Param("courseId") int courseId);

    Resource selectByPrimaryKey(@Param("resourceId") int resourceId);

    int removeResource(@Param("resId") int resId);

    int updateDownload(@Param("resId") int resId);
}
