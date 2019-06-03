package com.ps.Backend.REST;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public interface UserRestApi {

    @GetMapping("/{id}")
    UserDTO findById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<UserDTO> findAll();

    @PostMapping("/save")
    void save(@RequestBody UserDTO userDTO);

    // s-a trimis o lista de parametri
    @PostMapping(path = "/borrow")
    @ResponseBody
    void borrowBook(@RequestBody List<Object> map);

    @GetMapping("{id}/borrowed")
    List<BookDTO> getBorrowedBooks(@PathVariable("id") Integer id);

    @GetMapping("{userId}/{bookId}")
    BorrowDataDTO getBorrowData(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId);

    @PostMapping(path = "/return")
    @ResponseBody
    void returnBook(@RequestBody List<Object> list);

//    @GetMapping("/{username}")
    UserDTO findByUsername(@PathVariable("username") String username);

    void updateFine();

    // cum fac 2 variabile ??
//    @GetMapping("/{userId}", "/{bookId})
//    Integer getFinePerBook(@PathVariable("userId"))

//    @GetMapping("/{userId}")
    Integer getTotalFine(Integer userId);
}
