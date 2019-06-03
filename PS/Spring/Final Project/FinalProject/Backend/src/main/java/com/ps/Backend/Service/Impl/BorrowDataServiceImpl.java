package com.ps.Backend.Service.Impl;

import com.ps.Backend.Entity.BorrowData;
import com.ps.Backend.Mapper.BorrowDataMapper;
import com.ps.Backend.Repository.BorrowDataRepository;
import com.ps.Backend.Service.BorrowDataService;
import com.ps.Common.DTO.BorrowDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowDataServiceImpl implements BorrowDataService {

    private final BorrowDataRepository borrowDataRepository;
    private final BorrowDataMapper borrowDataMapper;


    @Autowired
    public BorrowDataServiceImpl(BorrowDataRepository borrowDataRepository, BorrowDataMapper borrowDataMapper) {
        this.borrowDataRepository = borrowDataRepository;
        this.borrowDataMapper = borrowDataMapper;
    }

    @Override
    public void save(BorrowDataDTO borrowDataDTO) {
        BorrowData borrowData = borrowDataMapper.toEntity(borrowDataDTO);
        borrowDataRepository.save(borrowData);
    }

    @Override
    public BorrowDataDTO findBorrowData(Integer id) {
        BorrowData borrowData = borrowDataRepository.findById(id).get();
        BorrowDataDTO borrowDataDTO = borrowDataMapper.toDTO(borrowData);
        return borrowDataDTO;
    }

    @Override
    public List<BorrowDataDTO> findAll() {
        List<BorrowDataDTO> dtos = borrowDataRepository.findAll().stream()
                .map(borrowData -> {
                    BorrowDataDTO borrowDataDTO = borrowDataMapper.toDTO(borrowData);
                    return borrowDataDTO;
                })
                .collect(Collectors.toList());
        return dtos;
    }
}
