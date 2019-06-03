package com.ps.Backend.REST.Impl;

import com.ps.Backend.REST.FineRestApi;
import com.ps.Backend.Service.FineService;
import com.ps.Common.DTO.FineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FineRestController implements FineRestApi {

    private final FineService fineService;


    @Autowired
    public FineRestController(FineService fineService) {
        this.fineService = fineService;
    }

    @Override
    public FineDTO findById(Integer id) {
        return fineService.findById(id);
    }

    @Override
    public List<FineDTO> findAll() {
        return fineService.findAll();
    }

    @Override
    public void save(FineDTO fineDTO) {
        fineService.save(fineDTO);
    }
}
