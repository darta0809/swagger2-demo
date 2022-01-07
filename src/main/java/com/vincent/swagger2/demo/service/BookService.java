package com.vincent.swagger2.demo.service;

import com.vincent.swagger2.demo.entity.Book;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    /*
     * maxAttempts 最多執行次數
     * backoff 捕捉到錯誤時，間隔秒數後重試
     * */
    @Retryable(include = {NoResultException.class}, maxAttempts = 3, backoff = @Backoff(value = 2000))
    public Book getBook() {
        int count = atomicInteger.incrementAndGet();
        log.info("count = {}", count);
        if (count < 5) {
            throw new NoResultException();
        } else {
            return new Book();
        }
    }

    /*
     * 定義錯誤的處理，必須寫在同個 class 裡，若超過 maxAttempts 次數時，則導向 Recover 處理
     * */
    @Recover
    public Book recover(NoResultException e) {
        log.info("get NoResultException & return null");
        return null;
    }
}
