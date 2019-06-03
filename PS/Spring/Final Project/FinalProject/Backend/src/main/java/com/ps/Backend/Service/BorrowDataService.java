package com.ps.Backend.Service;

import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BorrowDataService {
    void save(BorrowDataDTO borrowDataDTO);
    BorrowDataDTO findBorrowData(Integer id);
    List<BorrowDataDTO> findAll();
}
