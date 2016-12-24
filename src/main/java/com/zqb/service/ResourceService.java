package com.zqb.service;

import com.zqb.IDao.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zqb on 2016/12/22.
 */
@Service
public class ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    public Map<String,Object> addResource(String path)
    {
        Map<String,Object>resultMap=new HashMap<String,Object>();
        //读取刚刚上传的文件
        File read_file=new File(path);
        return resultMap;
    }
}
