package com.zqb.IDao;

import com.zqb.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(@Param("userId") int userId);

    User checkUserExistOrNot(@Param("username") String username,@Param("password") String password);

    User checkUser(@Param("username") String username);

    /**
     * @author zqb
     * @param
     * @return
     * @Date: 17:54 2016/12/9
     */
    int addNewUser(@Param("username") String username,
                   @Param("password") String password,
                   @Param("user_type") boolean user_type);

    User getUserByname(@Param("username")String username);

    List<User> getAllStudents();

}