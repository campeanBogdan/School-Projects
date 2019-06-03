package com.ps.Backend.Service.Impl;

import com.ps.Backend.Entity.Book;
import com.ps.Backend.Entity.BorrowData;
import com.ps.Backend.Mapper.BookMapper;
import com.ps.Backend.Mapper.BorrowDataMapper;
import com.ps.Backend.Repository.BookRepository;
import com.ps.Backend.Repository.BorrowDataRepository;
import com.ps.Backend.Repository.UserRepository;
import com.ps.Backend.Service.BookService;
import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    private final BorrowDataRepository borrowDataRepository;
    private final BorrowDataMapper borrowDataMapper;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository, BorrowDataRepository borrowDataRepository, BorrowDataMapper borrowDataMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
        this.borrowDataRepository = borrowDataRepository;
        this.borrowDataMapper = borrowDataMapper;
    }

    @Override
    public void save(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        bookRepository.save(book);
    }

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> dtos = bookRepository.findAll().stream()
                .map(book -> {
                    BookDTO dto = bookMapper.toDTO(book);
                    return dto;
                })
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public BookDTO findBook(Integer id) {
        Book book = bookRepository.findById(id).get();
        return bookMapper.toDTO(book);
    }
}
