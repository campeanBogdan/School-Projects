package com.ps.Frontend.Controller;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.FineDTO;
import com.ps.Common.DTO.UserDTO;
import com.ps.Frontend.Form.BorrowDataFrom;
import com.ps.Frontend.Form.ReturnBookForm;
import com.ps.Frontend.Gateway.BookGateway;
import com.ps.Frontend.Gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookGateway bookGateway;
    private final UserGateway userGateway;


    @Autowired
    public BookController(BookGateway bookGateway, UserGateway userGateway) {
        this.bookGateway = bookGateway;
        this.userGateway = userGateway;
    }

    // Book

    // afisare toate cartile + trimit mai departe user-ul curent
    @GetMapping("/view-books/{id}")
    public ModelAndView viewBooks(@PathVariable("id") Integer userId, ModelAndView mav) {
        List<BookDTO> books = bookGateway.findAll();
        UserDTO userDTO = userGateway.findById(userId);
        mav.addObject("books", books);
        mav.addObject("user", userDTO);
        mav.setViewName("Book/view-books");
        return mav;
    }

    // afisare fiecare carte in parte
    @GetMapping("/view-books/{userId}/{bookId}")
    public ModelAndView details(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId, ModelAndView mav) {
        BookDTO dto = bookGateway.findById(bookId);
        UserDTO userDTO = userGateway.findById(userId);
        mav.addObject("book", dto);
        mav.addObject("user", userDTO);
        mav.setViewName("Book/book-details");
        return mav;
    }

    // afisare cartile imprumutate de un client
    @GetMapping("/view-borrowed-books/{userId}")
    public ModelAndView borrowedBooksView(@PathVariable("userId") Integer userId, ModelAndView mav) {
        UserDTO userDTO = userGateway.findById(userId);
        List<BookDTO> books = userGateway.getBorrowedBooks(userId);
        mav.addObject("user", userDTO);
        mav.addObject("books", books);
        mav.setViewName("Book/view-books");
        return mav;
    }

    @GetMapping("/view-borrowed-books/{userId}/{bookId}")
    public ModelAndView detailsBorrowed(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId, ModelAndView mav) {
        BookDTO dto = bookGateway.findById(bookId);
        UserDTO userDTO = userGateway.findById(userId);
        BorrowDataDTO bdDTO = userGateway.getBorrowData(userId, dto.getId());
        FineDTO fineDTO = bdDTO.getFineDTO();

        mav.addObject("fine", fineDTO);
        mav.addObject("borrowedData", bdDTO);
        mav.addObject("book", dto);
        mav.addObject("user", userDTO);
        mav.setViewName("Book/book-user-details");
        return mav;
    }



    // afisez cartile + datele lor
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav) {
        List<BookDTO> list = bookGateway.findAll();
        mav.addObject("books", list);
        mav.setViewName("/book/book-list");
        return mav;
    }

    @GetMapping("/{id}/borrowed")
    public ModelAndView borrowedBooks(@PathVariable("id") Integer id, ModelAndView mav) {
        List<BookDTO> books = userGateway.getBorrowedBooks(id);
        UserDTO user = userGateway.findById(id);
        mav.addObject("books", books);
        mav.addObject("user", user);
        mav.setViewName("Book/borrowed-books");
        return mav;
    }

// BOOK
    @GetMapping("insert-book")
    public ModelAndView insertBookView(ModelAndView mav) {
        mav.setViewName("Book/insert-book");
        return mav;
    }

    // insert book
    @PostMapping(value = "/insert-book", params = "Insert")
    public ModelAndView newBook(BookDTO bookDTO, ModelAndView mav) {
        bookGateway.save(bookDTO);
        mav.setViewName("User/admin-menu");
        return mav;
    }

    // borrow book
    @PostMapping(value = "redirect", params = "Borrow")
    public ModelAndView borrowBook(BorrowDataFrom borrowDataFrom, ModelAndView mav) {
        UserDTO userDTO = userGateway.findById(borrowDataFrom.getUserId());
        BookDTO bookDTO = bookGateway.findById(borrowDataFrom.getBookId());
        List<BookDTO> books = bookGateway.findAll();

        userGateway.borrowBook(userDTO.getId(), bookDTO.getId(), borrowDataFrom.getStartDate(), borrowDataFrom.getEndDate());
        mav.addObject("user", userDTO);
        mav.addObject("book", bookDTO);
        mav.addObject("books", books);
        mav.setViewName("Book/redirect");
        return mav;
    }

    // USER
    @PostMapping(value = "/redirect-return", params = "Return")
    public ModelAndView returnBook(ReturnBookForm returnBookForm, ModelAndView mav) {
        UserDTO userDTO = userGateway.findById(returnBookForm.getUserId());
        BookDTO bookDTO = bookGateway.findById(returnBookForm.getBookId());
        userGateway.returnBook(userDTO.getId(), bookDTO.getId());
        mav.addObject("book", bookDTO);
        mav.addObject("user", userDTO);
        mav.setViewName("Book/redirect-return");
        return mav;
    }
//
//    @GetMapping(value = "/redirect-return")
//    public ModelAndView returnBookView(ModelAndView mav) {
//        mav.setViewName("Book/redirect-return");
//        System.out.println("A");
//        return mav;
//    }

}
