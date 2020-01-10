package com.zicheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;

/**
 * 子诚
 * Description：
 * 时间：2020/1/8 12:34
 */
@Controller
public class LoginController {
    //登陆
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        //模拟登陆，账号随意，密码是123456才能登陆
        if (!StringUtils.isEmpty(username) && password.equals("123456")) {
            //可以登陆的话,添加session
            session.setAttribute("LoginUser",username);
            //防止表单重复提交，故重定向
            return "redirect:/main";//登陆成功
        } else {
            //告诉用户登陆失败了
            model.addAttribute("msg", "用户名或者密码错误");
            return "index";
        }
    }
    //注销退出
    @RequestMapping("/user/logout")
    public String logOut(HttpSession session){
        //清除session
        session.invalidate();
        return "redirect:/index";
    }
}
