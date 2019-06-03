package com.ps.Backend.REST;

import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/borrow-data")
public interface BorrowDataApi {

    @GetMapping("{id}")
    BorrowDataDTO findById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<BorrowDataDTO> findAll();

    @PostMapping("/save")
    void save(@RequestBody BorrowDataDTO borrowDataDTO);
}
