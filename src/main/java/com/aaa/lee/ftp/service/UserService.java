package com.aaa.lee.ftp.service;

import com.aaa.lee.ftp.mapper.DbUserMapper;
import com.aaa.lee.ftp.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/30 16:31
 * @Description
 **/
@Service
public class UserService {

    @Autowired
    private DbUserMapper dbUserMapper;

    public DbUser login(DbUser dbUser, HttpSession session) {
        DbUser user = dbUserMapper.selectUserByUsernamePassword(dbUser);
        if(null != user && 0L != user.getId()) {
            session.setAttribute("user", user);
            return user;
        }
        return null;
    }

    public int updateHeadPicById(DbUser dbUser) {
        return dbUserMapper.updateHeadPicById(dbUser);
    }

}
