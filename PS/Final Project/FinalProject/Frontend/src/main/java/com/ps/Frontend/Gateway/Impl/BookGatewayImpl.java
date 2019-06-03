package com.ps.Frontend.Gateway.Impl;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Frontend.Conf.RestProperties;
import com.ps.Frontend.Gateway.BookGateway;
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
public class BookGatewayImpl implements BookGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookGateway.class);
    private final String URL = "/book";
    private final RestProperties restProperties;


    @Autowired
    public BookGatewayImpl(RestProperties restProperties) { this.restProperties = restProperties; }


    @Override
    public void save(BookDTO bookDTO) {
        LOGGER.info("BookGatewayImpl: Executing 'save' method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(bookDTO);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, httpEntity, Integer.class);
    }

    @Override
    public List<BookDTO> findAll() {
        LOGGER.info("BookGatewayImpl: Executing 'findAll' method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookDTO[]> forEntity = restTemplate.getForEntity(url, BookDTO[].class);
        BookDTO[] response = restTemplate.getForObject(url, BookDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public BookDTO findById(Integer id) {
        LOGGER.info("UserGatewayImpl: Executing 'findById' method");
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookDTO> forEntity = restTemplate.getForEntity(url, BookDTO.class);
        BookDTO response = restTemplate.getForObject(url, BookDTO.class);
        return response;
    }
}
