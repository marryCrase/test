package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户Contriller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findUser")
    public void findUser(@RequestBody User user){
        ModelAndView mv = new ModelAndView();
        User u = userService.findUser(user);
        if (u.getUsername().equals(user.getUsername())&&u.getPassword().equals(user.getPassword())){
            mv.addObject("user",u);
            mv.setViewName("forward:/index.jsp");
        }else {
            mv.setViewName("forward:/login.jsp");
        }
    }
}
