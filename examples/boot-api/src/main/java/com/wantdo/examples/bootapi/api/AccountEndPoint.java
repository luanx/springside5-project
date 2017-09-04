package com.wantdo.examples.bootapi.api;

import com.wantdo.examples.bootapi.service.AccountService;
import com.wantdo.examples.bootapi.service.exception.ErrorCode;
import com.wantdo.examples.bootapi.service.exception.ServiceException;
import com.wantdo.modules.web.MediaTypes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AccountEndPoint {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8)
    public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
        }
        String token = accountService.login(email, password);
        return Collections.singletonMap("token", token);
    }

    @RequestMapping(value = "/api/accounts/logout")
    public void logout(@RequestParam(value = "token", required = false) String token) {
        accountService.logout(token);
    }

    @RequestMapping(value = "/api/accounts/register")
    public void register(@RequestParam("email") String email,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam("password") String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            throw new ServiceException("User or name or password empty", ErrorCode.BAD_REQUEST);
        }

        accountService.register(email, name, password);
    }

}
