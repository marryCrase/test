package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户web
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    /**
     * 查询所有账户
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(defaultValue = "1",required = true) int pageNum, @RequestParam(defaultValue = "5",required = true) int pageSize){
        System.out.println("表现层查询所有账户信息。。。");
        //调用service
        List<Account> list = accountService.findAllAccount(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        return "lyear_pages_doc";
    }

    /**
     * 保存账户
     * @param account
     * @return
     */
    @RequestMapping("/saveAccount")

    public void saveAccount(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("表现层保存账户信息。。。");
        //调用service
        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return;
    }

}
