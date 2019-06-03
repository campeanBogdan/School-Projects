package com.ps.Backend.REST.Impl;

import com.ps.Backend.Entity.User;
import com.ps.Backend.Mapper.UserMapper;
import com.ps.Backend.REST.UserRestApi;
import com.ps.Backend.Service.UserService;
import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.LoginForm;
import com.ps.Common.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import java.io.ObjectInputStream;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class UserRestController implements UserRestApi {

    private final UserService userService;


    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO findById(Integer id) {
        return userService.findUser(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @Override
    public void save(UserDTO userDTO) {
        userService.save(userDTO);
    }

//    @Override
//    public void borrowBook(Integer userId, Integer bookId, String startDate, String endDate) {
//        userService.borrowBook(userId, bookId, startDate, endDate);
//    }

    @Override
    public List<BookDTO> getBorrowedBooks(Integer id) {
        return userService.getOwnBooks(id);
    }

    @Override
    public void returnBook(List<Object> list) {
        Integer userId = (Integer)list.get(0);
        Integer bookId = (Integer)list.get(1);
        userService.returnBook(userId, bookId);
    }

    @Override
    public void updateFine() {
        userService.updateFine();
    }

    @Override
    public Integer getTotalFine(Integer userId) {
        return userService.getTotalFine(userId);
    }

    @Override
    public BorrowDataDTO getBorrowData(Integer userId, Integer bookId) {
        return userService.getBorrowData(userId, bookId);
    }

    @Override
    public void borrowBook(List<Object> list) {
        Integer userId = (Integer)list.get(0);
        Integer bookId = (Integer)list.get(1);
        String startDate = (String)list.get(2);
        String endDate = (String)list.get(3);
        userService.borrowBook(userId, bookId, startDate, endDate);
    }

    @Override
    public UserDTO login(LoginForm loginForm) {
        return userService.login(loginForm);
    }
}
