package com.ps.Backend.REST.Impl;

import com.ps.Backend.REST.BorrowDataApi;
import com.ps.Backend.Service.BorrowDataService;
import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorrowDataRestController implements BorrowDataApi {

    private final BorrowDataService borrowDataService;


    @Autowired
    public BorrowDataRestController(BorrowDataService borrowDataService) {
        this.borrowDataService = borrowDataService;
    }

    @Override
    public BorrowDataDTO findById(Integer id) {
        return borrowDataService.findBorrowData(id);
    }

    @Override
    public List<BorrowDataDTO> findAll() {
        return borrowDataService.findAll();
    }

    @Override
    public void save(BorrowDataDTO borrowDataDTO) {
        borrowDataService.save(borrowDataDTO);
    }
}
