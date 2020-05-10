package cn.itcast.service;

import cn.itcast.domain.Account;

import java.util.List;

/**
 * 账户service接口
 */
public interface IAccountService {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);

}
