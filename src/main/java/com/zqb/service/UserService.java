package com.zqb.service;

import com.zqb.IDao.UserMapper;
import com.zqb.domain.User;
import com.zqb.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zqb on 2016/12/20.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectByPrimaryKey(int userId)
    {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * @author zqb
     * @param   path:之前上传文件的服务器路径
     * @return
     * @Date: 21:26 2016/12/21
     */
    public Map<String,Object> batchAdd(String path)
    {
        Map<String,Object>resultMap=new HashMap<String,Object>();
        //读取刚刚上传的文件
        File read_file=new File(path);
        if(!read_file.exists())
        {
            resultMap.put("code",500);
            resultMap.put("msg","读取上传文件出错");
        }
        else
        {
            if(!path.endsWith(".xls"))
            {
                resultMap.put("code",500);
                resultMap.put("msg","上传文件类型不符");
                return resultMap;
            }
            else
            {
                try
                {
                    new ExcelHelper().addRecordFromExcel(path);
                }
                catch (Exception e)
                {
                    resultMap.put("code",500);
                    resultMap.put("msg","导入数据出错");
                    return resultMap;
                }
            }
        }
        if(resultMap.isEmpty())
        {
            resultMap.put("code",200);
            resultMap.put("msg","导入成功");
        }
        return resultMap;
    }


    public User getUserByname(String name)
    {
        return userMapper.getUserByname(name);
    }

}
