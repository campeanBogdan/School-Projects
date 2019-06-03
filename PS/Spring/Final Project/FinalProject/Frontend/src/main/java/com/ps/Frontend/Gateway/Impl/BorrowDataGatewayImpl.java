package com.ps.Frontend.Gateway.Impl;

import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Frontend.Conf.RestProperties;
import com.ps.Frontend.Gateway.BorrowDataGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BorrowDataGatewayImpl implements BorrowDataGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowDataGateway.class);
    private final String URL = "/borrow-data";
    private final RestProperties restProperties;


    @Autowired
    public BorrowDataGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public void save(BorrowDataDTO borrowDataDTO) {
        LOGGER.info("BorrowDataGatewayImpl: Executing 'save' method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(borrowDataDTO);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, httpEntity, Integer.class);
    }

    @Override
    public List<BorrowDataDTO> findAll() {
        LOGGER.info("BorrowDataGatewayImpl: Executing 'findAll' method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BorrowDataDTO[]> forEntity = restTemplate.getForEntity(url, BorrowDataDTO[].class);
        BorrowDataDTO[] response = restTemplate.getForObject(url, BorrowDataDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public BorrowDataDTO findById(Integer id) {
        LOGGER.info("BorrowDataGatewayImpl: Executing 'findById' method");
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BorrowDataDTO> forEntity = restTemplate.getForEntity(url, BorrowDataDTO.class);
        BorrowDataDTO response = restTemplate.getForObject(url, BorrowDataDTO.class);
        return response;
    }
}
