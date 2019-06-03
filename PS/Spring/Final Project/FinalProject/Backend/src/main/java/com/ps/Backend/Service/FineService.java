package com.ps.Backend.Service;

import com.ps.Common.DTO.FineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FineService {

    void save(FineDTO fineDTO);
    FineDTO findById(Integer id);
    List<FineDTO> findAll();
}
