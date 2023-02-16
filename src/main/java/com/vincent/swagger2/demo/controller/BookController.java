package com.vincent.swagger2.demo.controller;

import com.vincent.swagger2.demo.dto.BookDto;
import com.vincent.swagger2.demo.entity.Book;
import com.vincent.swagger2.demo.repository.BookRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Api(tags = "Book")
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @ApiOperation(value = "取得書本", notes = "列出所有書本")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/v1/book")
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @ApiOperation(value = "新增書本", notes = "新增書本內容")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "書本資訊")})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "v1/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto create(@ApiParam(required = true, value = "書本內容") @RequestBody BookDto bookDto) {
        Book book = Book.builder()
                .bookid(bookDto.getBookId())
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .build();
        book = bookRepository.save(book);
        bookDto.setBookId(book.getBookid());
        return bookDto;
    }

    @ApiOperation(value = "取得書本內容", notes = "取得書本內容")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "書本資訊")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "v1/book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto get(@ApiParam(required = true, name = "bookid", value = "書本ID") @PathVariable Integer bookId)  {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Book book = bookOpt.orElseThrow(() -> new EntityNotFoundException(String.valueOf(bookId)));
        return BookDto.builder()
                .bookId(book.getBookid())
                .name(book.getName())
                .author(book.getAuthor())
                .build();
    }
}
