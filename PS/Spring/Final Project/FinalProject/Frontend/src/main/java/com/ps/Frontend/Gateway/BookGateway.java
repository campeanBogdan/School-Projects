package com.ps.Frontend.Gateway;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;

import java.util.List;

public interface BookGateway {

    void save(BookDTO bookDTO);
    List<BookDTO> findAll();
    BookDTO findById(Integer id);
    List<BookDTO> getAllBooksLike(String title);
}
