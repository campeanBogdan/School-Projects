package com.ps.Backend.Mapper.Impl;

import com.ps.Backend.Entity.BorrowData;
import com.ps.Backend.Entity.Fine;
import com.ps.Backend.Mapper.BorrowDataMapper;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.FineDTO;
import org.mapstruct.Mapper;

@Mapper
public class BorrowDataMapperImpl implements BorrowDataMapper {

    @Override
    public BorrowDataDTO toDTO(BorrowData borrowData) {
        BorrowDataDTO borrowDataDTO = new BorrowDataDTO();
        borrowDataDTO.setStartDate(borrowData.getStartDate());
        borrowDataDTO.setEndDate(borrowData.getEndDate());
        borrowDataDTO.setUserId(borrowData.getUserId());
        borrowDataDTO.setBookId(borrowData.getBookId());
        FineDTO fineDTO = new FineDTO();
        Fine fine = borrowData.getFine();
        fineDTO.setId(fine.getId());
        fineDTO.setSum(fine.getSum());
        borrowDataDTO.setFineDTO(fineDTO);
        return borrowDataDTO;
    }

    @Override
    public BorrowData toEntity(BorrowDataDTO borrowDataDTO) {
        BorrowData borrowData = new BorrowData();
        borrowData.setStartDate(borrowDataDTO.getStartDate());
        borrowData.setEndDate(borrowDataDTO.getEndDate());
        borrowData.setUserId(borrowDataDTO.getUserId());
        borrowData.setBookId(borrowDataDTO.getBookId());
        Fine fine = new Fine();
        FineDTO fineDTO = borrowDataDTO.getFineDTO();
        fine.setId(fineDTO.getId());
        fine.setSum(fineDTO.getSum());
        borrowData.setFine(fine);
        return borrowData;
    }
}
