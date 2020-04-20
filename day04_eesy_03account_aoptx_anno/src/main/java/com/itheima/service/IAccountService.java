package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-03-30
 * Time:13:40
 */
public interface IAccountService {

    /**
     * 查找全部
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查找一个
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 修改
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 转账
     * @param sourceName    转出金额
     * @param targetName    转入金额
     * @param money         转账金额
     */
    void transfer(String sourceName, String targetName, Float money);

}
