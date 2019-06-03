package com.ps.Backend.Service;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface UserService {
    void save(UserDTO userDTO);
    List<UserDTO> findAll();
    UserDTO findUser(Integer id);
    void borrowBook(Integer userId, Integer bookId, String startDate, String endDate);
    List<BookDTO> getOwnBooks(Integer id);
    void returnBook(Integer userId, Integer bookId);
    UserDTO findByUsername(String username);
    void updateFine();
    Integer getFinePerBook(Integer userId, Integer bookId);
    Integer getTotalFine(Integer userId);
    BorrowDataDTO getBorrowData(Integer userId, Integer bookId);
}
