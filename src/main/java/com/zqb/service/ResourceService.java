package com.zqb.service;

import com.zqb.IDao.ResourceMapper;
import com.zqb.domain.Resource;
import com.zqb.util.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
        else if(path.endsWith(".png")||path.endsWith(".jpg")||path.endsWith(".PNG")||path.endsWith(".JPEG")||path.endsWith(".gif"))
        {
            type=2;
        }
        else if(path.endsWith(".zip")||path.endsWith(".rar"))
        {
            type=3;
        }
        else if(path.endsWith(".docx")||path.endsWith(".doc"))
        {
            type=4;
        }
        else if(path.endsWith(".html")||path.endsWith(".htm"))//html,txt,php
        {
            type=5;
        }
        else if(path.endsWith(".pdf"))
        {
            type=6;
        }
        else if(path.endsWith(".ppt")||path.endsWith(".pptx"))
        {
            type=7;
        }

        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        resourceMapper.addResource(type,user_id,course_id,time,path,0,"");
        return true;
    }

    public List<Resource>getResourceByCourseId(int courseId)
    {
        return resourceMapper.getResourceByCourseId(courseId);
    }

    public Resource selectByPrimaryKey(int res_id)
    {
        return resourceMapper.selectByPrimaryKey(res_id);
    }

    public int removeResource(int resId)
    {
        if(resId>0)
        {
            Resource cur_res=selectByPrimaryKey(resId);//删除数据库记录，同时删除服务器文件
            if(cur_res!=null)
            {
                String path=cur_res.getSavePath();
                String basePath=path.substring(0,path.lastIndexOf("\\"));
                basePath=basePath.substring(0,basePath.lastIndexOf("\\")+1);
                File docFile = new File(path);
                String pdfPath=basePath+"pdf\\"+ docFile.getName().substring(0,docFile.getName().lastIndexOf("."))+ ".pdf";
                FileTool.fileDelete(path);
                FileTool.fileDelete(pdfPath);
                return resourceMapper.removeResource(resId);
            }
        }
        return 0;
    }

    public int updateDownload(int resId)
    {
        return resourceMapper.updateDownload(resId);
    }
}
