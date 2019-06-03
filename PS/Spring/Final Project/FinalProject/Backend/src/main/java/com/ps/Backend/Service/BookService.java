package com.ps.Backend.Service;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    void save(BookDTO bookDTO);
    List<BookDTO> findAll();
    BookDTO findBook(Integer id);
    List<BookDTO> findByTitleLike(String title);
}
