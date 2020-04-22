package com.itheima.dao;

import com.itheima.domain.Account;

public interface IAccountDao {

    /**
     * 根据id查询账户
     * @param accountId
     */
    Account findAccountById(Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);

    /**
     * 修改账户
     * @param account
     */
    void updateAccount(Account account);


}
