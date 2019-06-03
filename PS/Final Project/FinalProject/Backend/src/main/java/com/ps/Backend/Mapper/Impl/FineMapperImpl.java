package com.ps.Backend.Mapper.Impl;

import com.ps.Backend.Entity.Fine;
import com.ps.Backend.Mapper.FineMapper;
import com.ps.Common.DTO.FineDTO;

public class FineMapperImpl implements FineMapper {

    @Override
    public Fine toEntity(FineDTO fineDTO) {
        Fine fine = new Fine();
        fine.setId(fineDTO.getId());
        fine.setSum(fineDTO.getSum());
        return fine;
    }

    @Override
    public FineDTO toDTO(Fine fine) {
        FineDTO fineDTO = new FineDTO();
        fineDTO.setId(fine.getId());
        fineDTO.setSum(fine.getSum());
        return fineDTO;
    }
}
