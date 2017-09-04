package com.wantdo.examples.bootapi.repository;

import com.wantdo.examples.bootapi.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountDao extends CrudRepository<Account, Long>{

    Account findByEmail(String email);

}
