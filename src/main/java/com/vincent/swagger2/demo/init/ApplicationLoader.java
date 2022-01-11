package com.vincent.swagger2.demo.init;

import com.vincent.swagger2.demo.service.BookService;
import com.vincent.swagger2.demo.service.TimeService;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationLoader implements CommandLineRunner {

    @Autowired
    private BookService bookService;
    @Autowired
    private TimeService timeService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("test retry start");
            bookService.getBook();
            log.info("test retry end");

            log.info("test cache start");
            for (int i = 0; i < 10; i++) {
                System.out.println(formatter.format(timeService.getTime()));
                Thread.sleep(1000);
            }
            log.info("test cache end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
