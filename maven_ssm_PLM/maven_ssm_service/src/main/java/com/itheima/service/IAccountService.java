package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户service接口
 */
public interface IAccountService {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAllAccount(int pageNum,int pageSize);

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);

}
