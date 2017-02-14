package com.zqb.service;

import com.zqb.IDao.StudentCourseMapper;
import com.zqb.IDao.UserMapper;
import com.zqb.domain.StudentCourse;
import com.zqb.domain.User;
import com.zqb.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqb on 2016/12/20.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StudentCourseMapper studentCourseMapper;

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
    public Map<String,Object> batchAdd(String path,String courseId)
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
            if(!path.endsWith(".xls")&&!path.endsWith(".xlsx"))
            {
                resultMap.put("code",500);
                resultMap.put("msg","上传文件类型不符");
                return resultMap;
            }
            else
            {
                try
                {
                    List<User> list=ExcelHelper.getAllByExcel(path);
                    if(list!=null)
                    {
                        for(int i=0;i<list.size();i++)
                        {
                            String name=list.get(i).getUserName();
                            User user=userMapper.checkUser(name);
                            if(user==null)
                            {
                                userMapper.addNewUser(name,"123456",false);
                                int userId=userMapper.getUserByname(name).getUserId();
                                studentCourseMapper.addStudentCourse(userId,Integer.parseInt(courseId));
                            }
                            else
                            {
                                StudentCourse studentCourse=studentCourseMapper.checkExist(user.getUserId(),Integer.parseInt(courseId));
                                if(studentCourse==null)
                                {
                                    studentCourseMapper.addStudentCourse(user.getUserId(),Integer.parseInt(courseId));
                                }

                            }
                        }
                    }
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
