package com.zqb.service;

import com.zqb.IDao.UserMapper;
import com.zqb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zqb on 2016/12/9.
 */
@Service
public class RegisterService {

    @Autowired
    UserMapper userMapper;
    public boolean registerCheck(String username)
    {
        User user=userMapper.checkUser(username);
        if(user==null)
        {
            return true;
        }
        return false;
    }

    public boolean registerNewUser(String username,String password)
    {
        int id=userMapper.addNewUser(username,password,false);
        if(id>0)//注册成功返回当前用户id
        {
            return true;
        }
        return false;
    }
}
