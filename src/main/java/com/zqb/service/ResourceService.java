package com.zqb.service;

import com.zqb.IDao.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zqb on 2016/12/22.
 */
@Service
public class ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    public boolean addResource(String path,int course_id,int user_id)
    {
        //读取刚刚上传的文件
        File read_file=new File(path);
        int type=0;
        if(!read_file.exists())
        {
            return false;
        }
        if(path.endsWith(".xls")||path.endsWith(".xlsx"))
        {
            type=1;
        }
        else if(path.endsWith(".png")||path.endsWith(".jpg")||path.endsWith(".PNG")||path.endsWith(".JPEG"))
        {
            type=2;
        }
        else if(path.endsWith(".zip")||path.endsWith(".rar"))
        {
            type=3;
        }
        else if(path.endsWith(".docx")||path.endsWith(".doc")||path.endsWith(".xls")||path.endsWith(".dot"))
        {
            type=4;
        }
        else if(path.endsWith(".html")||path.endsWith(".htm"))
        {
            type=5;
        }
        else if(path.endsWith(".pdf"))
        {
            type=6;
        }
        else//其他，如txt,php,ini,
        {
            type=7;
        }
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        resourceMapper.addResource(type,user_id,course_id,time,path,0,"");
        return true;
    }
}
