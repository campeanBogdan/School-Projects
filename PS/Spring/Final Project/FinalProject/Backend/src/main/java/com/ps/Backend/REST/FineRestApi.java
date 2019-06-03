package com.ps.Backend.REST;

import com.ps.Common.DTO.FineDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fine")
public interface FineRestApi {

    @GetMapping("/{id}")
    FineDTO findById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<FineDTO> findAll();

    @PostMapping("/save")
    void save(@RequestBody FineDTO fineDTO);
}
