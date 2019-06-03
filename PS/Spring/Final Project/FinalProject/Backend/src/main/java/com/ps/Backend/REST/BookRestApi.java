package com.ps.Backend.REST;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
public interface BookRestApi {

    @GetMapping("/{id}")
    BookDTO findById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<BookDTO> findAll();

    @PostMapping("/save")
    void save(@RequestBody BookDTO bookDTO);

    @GetMapping("/get-books")
    List<BookDTO> getAllBooksLike(String title);
}
