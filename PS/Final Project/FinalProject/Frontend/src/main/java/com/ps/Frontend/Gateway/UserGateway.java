package com.ps.Frontend.Gateway;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.UserDTO;

import java.util.Date;
import java.util.List;

public interface UserGateway {

    void save(UserDTO userDTO);
    List<UserDTO> findAll();
    UserDTO findById(Integer id);
    UserDTO findByUsername(String username);
    List<BookDTO> getBorrowedBooks(Integer userId);
    BorrowDataDTO getBorrowData(Integer userId, Integer bookId);
    void borrowBook(Integer userId, Integer bookId, String startDate, String endDate);
    void returnBook(Integer userId, Integer bookId);
}
