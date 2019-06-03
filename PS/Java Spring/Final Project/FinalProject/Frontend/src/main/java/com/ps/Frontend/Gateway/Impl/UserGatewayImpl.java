package com.ps.Frontend.Gateway.Impl;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.LoginForm;
import com.ps.Common.DTO.UserDTO;
import com.ps.Frontend.Conf.RestProperties;
import com.ps.Frontend.Gateway.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component
public class UserGatewayImpl implements UserGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/user";
    private final RestProperties restProperties;


    @Autowired
    public UserGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }


    @Override
    public void save(UserDTO userDTO) {
        LOGGER.info("UserGatewayImpl: Executing 'save' method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(userDTO);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, httpEntity, Integer.class);
    }

    @Override
    public List<UserDTO> findAll() {
        LOGGER.info("UserGatewayImpl: Executing 'findAll' method");
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO[]> forEntity = restTemplate.getForEntity(url, UserDTO[].class);
        UserDTO[] response = restTemplate.getForObject(url, UserDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public UserDTO findById(Integer id) {
        LOGGER.info("UserGatewayImpl: Executing 'findById' method");
        String url = restProperties.getUrl() + URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> forEntity = restTemplate.getForEntity(url, UserDTO.class);
        UserDTO response = restTemplate.getForObject(url, UserDTO.class);
        return response;
    }

    @Override
    public UserDTO findByUsername(String username) {
        LOGGER.info("UserGatewayImpl: Executing 'findByUsername' method");
        String url = restProperties.getUrl() + URL + "/" + username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> forEntity = restTemplate.getForEntity(url, UserDTO.class);
        UserDTO response = restTemplate.getForObject(url, UserDTO.class);
        return response;
    }

    // cartile imprumutate de un user
    @Override
    public List<BookDTO> getBorrowedBooks(Integer userId) {
        LOGGER.info("UserGatewayImpl: Executing 'getBorrowedBooks' method");
        String url = restProperties.getUrl() + URL + "/" + userId + "/borrowed";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BookDTO[]> forEntity = restTemplate.getForEntity(url, BookDTO[].class);
        BookDTO[] response = restTemplate.getForObject(url, BookDTO[].class);
        return Arrays.asList(response);
    }

    // datele imprumutului dupa user si carte
    @Override
    public BorrowDataDTO getBorrowData(Integer userId, Integer bookId) {
        LOGGER.info("UserGatewayImpl: Executing 'getBorrowData' method");
        String url = restProperties.getUrl() + URL + "/" + userId + "/" + bookId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BorrowDataDTO> forEntity = restTemplate.getForEntity(url, BorrowDataDTO.class);
        BorrowDataDTO response = restTemplate.getForObject(url, BorrowDataDTO.class);
        return response;
    }

    // trimit o lista de Object
    // UserRestApi = borrowBook()
    @Override
    public void borrowBook(Integer userId, Integer bookId, String startDate, String endDate) {
        String url = restProperties.getUrl() + URL + "/borrow/";
        LOGGER.info("UserGatewayImpl: Executing 'borrowBook' method");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<Object> list = new ArrayList<>();
        list.add(userId);
        list.add(bookId);
        list.add(startDate);
        list.add(endDate);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(list, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Object>>() {});
    }

    @Override
    public void returnBook(Integer userId, Integer bookId) {
        String url = restProperties.getUrl() + URL + "/return";
        LOGGER.info("UserGatewayImpl: Executing 'returnBook' method");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<Object> list = new ArrayList<>();
        list.add(userId);
        list.add(bookId);
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(list, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<List<Object>>() {});
    }

    @Override
    public UserDTO login(LoginForm loginForm) {
        LOGGER.info("UserGatewayImpl: Executing 'login' method");
        String url = restProperties.getUrl() + URL + "/login";

//        HttpEntity<Object> httpEntity = new HttpEntity<>(loginForm);
//        RestTemplate restTemplate = new RestTemplate();
//        UserDTO userDTO = restTemplate.postForObject(url, httpEntity, UserDTO.class);
//        return userDTO;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(loginForm, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Object>() {});
        UserDTO userDTO = (UserDTO)responseEntity.getBody();
        //UserDTO userDTO = restTemplate.postForObject(url, httpEntity, UserDTO.class);
        return userDTO;


    }
}
