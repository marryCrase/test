package cn.itcast.service.impl;

import cn.itcast.domain.Account;
import cn.itcast.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户service实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    /**
     * 查询所有账户
     * @return
     */
    @Override
    public List<Account> findAllAccount() {
        System.out.println("业务层：查询所有账户。。。");
        return null;
    }
    /**
     * 保存账户
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存账户。。。");
    }
}
