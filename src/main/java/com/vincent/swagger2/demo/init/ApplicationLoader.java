package com.vincent.swagger2.demo.init;

import com.vincent.swagger2.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationLoader implements CommandLineRunner {

    @Autowired
    private BookService bookService;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            bookService.getBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
