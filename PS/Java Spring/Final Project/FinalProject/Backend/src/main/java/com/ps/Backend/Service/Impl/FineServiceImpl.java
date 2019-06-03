package com.ps.Backend.Service.Impl;

import com.ps.Backend.Entity.Fine;
import com.ps.Backend.Mapper.FineMapper;
import com.ps.Backend.Repository.FineRepository;
import com.ps.Backend.Service.FineService;
import com.ps.Common.DTO.FineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;
    private final FineMapper fineMapper;


    @Autowired
    public FineServiceImpl(FineRepository fineRepository, FineMapper fineMapper) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
    }

    @Override
    public void save(FineDTO fineDTO) {
        Fine fine = fineMapper.toEntity(fineDTO);
        fineRepository.save(fine);
    }

    @Override
    public FineDTO findById(Integer id) {
        Fine fine = fineRepository.findById(id).get();
        FineDTO fineDTO = fineMapper.toDTO(fine);
        return fineDTO;
    }

    @Override
    public List<FineDTO> findAll() {
        List<FineDTO> list = fineRepository.findAll().stream()
                .map(fine -> {
                    FineDTO fineDTO = fineMapper.toDTO(fine);
                    return fineDTO;
                })
                .collect(Collectors.toList());
        return list;
    }
}
