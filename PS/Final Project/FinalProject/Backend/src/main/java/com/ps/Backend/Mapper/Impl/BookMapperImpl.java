package com.ps.Backend.Mapper.Impl;

import com.ps.Backend.Entity.Book;
import com.ps.Backend.Entity.BorrowData;
import com.ps.Backend.Mapper.BookMapper;
import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import org.mapstruct.Mapper;

@Mapper
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setTitle(bookDTO.getTitle());
        book.setStock(bookDTO.getStock());
        book.setId(bookDTO.getId());
        return book;
    }

    @Override
    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setAuthor(book.getAuthor());
        dto.setDescription(book.getDescription());
        dto.setTitle(book.getTitle());
        dto.setStock(book.getStock());
        dto.setId(book.getId());
        return dto;
    }
}
