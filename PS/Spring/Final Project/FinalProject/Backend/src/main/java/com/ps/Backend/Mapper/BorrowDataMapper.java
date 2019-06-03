package com.ps.Backend.Mapper;

import com.ps.Backend.Entity.BorrowData;
import com.ps.Common.DTO.BorrowDataDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BorrowDataMapper {
    BorrowDataDTO toDTO(BorrowData borrowData);
    BorrowData toEntity(BorrowDataDTO borrowDataDTO);
}
