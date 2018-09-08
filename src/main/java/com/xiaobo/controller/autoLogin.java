package com.xiaobo.controller;

import com.xiaobo.bean.autoLoginBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class autoLogin {

    @RequestMapping("autoLoginFun")
    public String autoLogin(autoLoginBean bean, HttpSession session, Model model) {

        if ("on".equals(session.getAttribute("auto"))) {
            bean.setUsername((String) session.getAttribute("username"));
            bean.setPassword((String) session.getAttribute("password"));
            bean.setAuto((String) session.getAttribute("auto"));
        }

        if ("xiaobo".equals(bean.getUsername()) && "123".equals(bean.getPassword())) {
            session.setAttribute("username", bean.getUsername());
            session.setAttribute("password", bean.getPassword());
            session.setAttribute("auto", bean.getAuto());
            System.out.println(bean.getUsername() + "-" + bean.getPassword() + "-" + bean.getAuto());
            model.addAttribute("username", bean.getUsername());
            return "welcome";
        }
        model.addAttribute("msg", "用户名或者密码错误");
        return "autoLogin";
    }

    @RequestMapping("LoginUI")
    public String autoLoginUI() {
        return "autoLogin";
    }

}
