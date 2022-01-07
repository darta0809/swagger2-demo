package com.vincent.swagger2.demo.controller;

import com.vincent.swagger2.demo.dto.BookDto;
import com.vincent.swagger2.demo.entity.Book;
import com.vincent.swagger2.demo.repository.BookRepository;
import io.swagger.annotations.*;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Book")
@RestController
@RequestMapping(value = "/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "取得書本", notes = "列出所有書本")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @ApiOperation(value = "新增書本", notes = "新增書本內容")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "書本資訊")})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "v1/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto create(@ApiParam(required = true, value = "書本內容") @RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setBookid(bookDto.getBookId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book = bookRepository.save(book);
        bookDto.setBookId(book.getBookid());
        return bookDto;
    }

    @ApiOperation(value = "取得書本內容", notes = "取得書本內容")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "書本資訊")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "v1/book/{bookid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto get(@ApiParam(required = true, name = "bookid", value = "書本ID") @PathVariable Integer bookid) throws Exception {
        Optional<Book> bookOpt = bookRepository.findById(bookid);
        Book book = bookOpt.orElseThrow(() -> new EntityNotFoundException(String.valueOf(bookid)));
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookid());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        return bookDto;
    }
}
