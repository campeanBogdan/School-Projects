package com.ps.Backend.REST.Impl;

import com.ps.Backend.REST.BookRestApi;
import com.ps.Backend.Service.BookService;
import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController implements BookRestApi {
    private final BookService bookService;


    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public BookDTO findById(Integer id) {
        return bookService.findBook(id);
    }

    @Override
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @Override
    public void save(BookDTO bookDTO) {
        bookService.save(bookDTO);
    }

    @Override
    public List<BookDTO> getAllBooksLike(String title) {
        return bookService.findByTitleLike(title);
    }
}
