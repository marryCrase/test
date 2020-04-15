package com.itheima.service;

import org.springframework.stereotype.Service;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    /**
     * 模拟保存账户
     */
    void savaAccount();

    /**
     * 模拟更改账户
     * @param i
     */
    void updateAccount(int i);

    /**
     * 删除账户
     * @return
     */
    int deleteAccount();


}
