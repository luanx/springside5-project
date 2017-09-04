package com.wantdo.examples.bootapi.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sun.corba.se.impl.oa.toa.TOA;
import com.wantdo.examples.bootapi.domain.Account;
import com.wantdo.examples.bootapi.repository.AccountDao;
import com.wantdo.examples.bootapi.service.exception.ErrorCode;
import com.wantdo.examples.bootapi.service.exception.ServiceException;
import com.wantdo.modules.utils.misc.IdGenerator;
import com.wantdo.modules.utils.text.EncodeUtil;
import com.wantdo.modules.utils.text.HashUtil;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CounterService counterService;

    private Cache<String, Account> loginUsers;


    @PostConstruct
    public void init() {
        loginUsers = CacheBuilder.newBuilder().build();
    }

    public String login(String email, String password) {
        Account account = accountDao.findByEmail(email);

        if (account == null) {
            throw new ServiceException("User not exist", ErrorCode.UNAUTHORIZED);
        }

        if (!account.hashPassword.equals(hashPassword(password))) {
            throw new ServiceException("Password wrong", ErrorCode.UNAUTHORIZED);
        }

        String token = IdGenerator.uuid2();
        loginUsers.put(token, account);
        counterService.increment("loginUser");
        return token;
    }

    public void logout(String token) {
        Account account = loginUsers.getIfPresent(token);
        if (account == null) {

        } else {
            loginUsers.invalidate(token);
            counterService.decrement("loginUser");
        }
    }

    public Account getLoginUser(String token) {
        Account account = loginUsers.getIfPresent(token);

        if (account == null) {
            throw new ServiceException("User doesn't login", ErrorCode.UNAUTHORIZED);
        }

        return account;
    }

    public void register(String email, String name, String password) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
        }

        Account account = new Account();
        account.email = email;
        account.name = name;
        account.hashPassword = hashPassword(password);

        accountDao.save(account);
    }

    protected static String hashPassword(String password) {
        return EncodeUtil.encodeBase64(HashUtil.sha1(password));
    }

}
