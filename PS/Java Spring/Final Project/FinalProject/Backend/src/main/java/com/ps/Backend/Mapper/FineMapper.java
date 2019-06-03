package com.ps.Backend.Mapper;

import com.ps.Backend.Entity.Fine;
import com.ps.Common.DTO.FineDTO;
import org.mapstruct.Mapper;

@Mapper
public interface FineMapper {

    Fine toEntity(FineDTO fineDTO);
    FineDTO toDTO(Fine fine);
}
