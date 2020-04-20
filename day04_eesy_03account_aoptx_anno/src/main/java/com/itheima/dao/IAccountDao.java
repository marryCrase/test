package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-03-30
 * Time:13:51
 */
public interface IAccountDao {

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
     * 根据名称查询账户
     * @param accountName
     * @return      如果有唯一的账户就返回，如果账户为空就返回null
     *              如果账户大于1个就抛异常
     */
    Account findAccountByName(String accountName);

}
