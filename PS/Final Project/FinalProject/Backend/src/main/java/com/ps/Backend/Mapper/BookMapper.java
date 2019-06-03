package com.ps.Backend.Mapper;

import com.ps.Backend.Entity.Book;
import com.ps.Common.DTO.BookDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book toEntity(BookDTO bookDTO);
    BookDTO toDTO(Book book);
}
