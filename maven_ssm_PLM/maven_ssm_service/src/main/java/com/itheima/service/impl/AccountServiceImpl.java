package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户service实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;
    /**
     * 查询所有账户
     * @return
     */
    @Override
    public List<Account> findAllAccount(int pageNum,int pageSize) {
        System.out.println("业务层：查询所有账户。。。");
        PageHelper.startPage(1,5);
        return accountDao.findAllAccount(pageNum,pageSize);
    }
    /**
     * 保存账户
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存账户。。。");
        accountDao.saveAccount(account);
    }
}
