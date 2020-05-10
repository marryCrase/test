package cn.itcast.controller;

import cn.itcast.service.IAccountService;
import cn.itcast.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账户web
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(){
        System.out.println("表现层查询所有账户信息。。。");
        //调用service
        accountService.findAllAccount();
        return "list";
    }

}
