package com.zqb.IDao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by zqb on 2016/12/22.
 */
public interface ResourceMapper {
    int addResource(@Param("resourceType") int resourceType,
                    @Param("userId") int userId,
                    @Param("uploadTime") String uploadTime,
                    @Param("savePath") String savePath,
                    @Param("downloadTimes") int download_times,
                    @Param("remark") String remark);
}
