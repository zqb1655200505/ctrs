package com.zqb.service;

import com.zqb.IDao.UserMapper;
import com.zqb.domain.User;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by zqb on 2016/12/5.
 */
@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    private int curId=0;

    public User doLogin(String username,String password)
    {
        return userMapper.checkUserExistOrNot(username,password);
    }


    public String getCurId()
    {
        String str = UUID.randomUUID().toString().replaceAll("-", "");
        // 产生的字符串太长，浪费存储，再进行MD5
        // 可以使用apache的org.apache.commons.codec.digest.DigestUtils
        // 也可以是使用java.security.MessageDigest进行加密
        // 注意返回的是长度为16的byte数组，使用Hex转换成32的char数组，在转成字符串
        return new String(Hex.encodeHex(DigestUtils.md5(str)));
    }
}
