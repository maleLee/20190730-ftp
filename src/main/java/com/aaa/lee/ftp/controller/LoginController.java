package com.aaa.lee.ftp.controller;

import com.aaa.lee.ftp.model.DbUser;
import com.aaa.lee.ftp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/30 16:29
 * @Description
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String turnLoginPage() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(DbUser dbUser, HttpSession session) {
        DbUser user = userService.login(dbUser, session);
        if(null != user) {
            // 说明登录成功，需要跳转到上传页面中
            return "upload_file";
        }
        return "error";
    }

}
