package com.wantdo.examples.bootapi.repository;

import com.wantdo.examples.bootapi.domain.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface BookDao extends PagingAndSortingRepository<Book, Long> {

    List<Book> findByOwnerId(Long ownerId, Pageable pageable);

    List<Book> findByBorrowerId(Long borrowerId, Pageable pageable);
}
