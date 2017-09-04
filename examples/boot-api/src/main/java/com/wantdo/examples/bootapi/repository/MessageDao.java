package com.wantdo.examples.bootapi.repository;

import com.wantdo.examples.bootapi.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageDao extends CrudRepository<Message, Long> {
}
