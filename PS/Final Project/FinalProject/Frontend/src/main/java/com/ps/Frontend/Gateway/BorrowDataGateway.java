package com.ps.Frontend.Gateway;

import com.ps.Common.DTO.BorrowDataDTO;

import java.util.List;

public interface BorrowDataGateway {

    void save(BorrowDataDTO borrowDataDTO);
    List<BorrowDataDTO> findAll();
    BorrowDataDTO findById(Integer id);
}
