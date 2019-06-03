package com.ps.Frontend.Controller;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.UserDTO;
import com.ps.Frontend.Gateway.BookGateway;
import com.ps.Frontend.Gateway.BorrowDataGateway;
import com.ps.Frontend.Gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/borrow-data")
public class BorrowDataController {

    private final BorrowDataGateway borrowDataGateway;
    private final UserGateway userGateway;
    private final BookGateway bookGateway;


    @Autowired
    public BorrowDataController(BorrowDataGateway borrowDataGateway, UserGateway userGateway, BookGateway bookGateway) {
        this.borrowDataGateway = borrowDataGateway;
        this.userGateway = userGateway;
        this.bookGateway = bookGateway;
    }

    // afisez cartile imprumutate + datele lor
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav){
        List<BorrowDataDTO> list = borrowDataGateway.findAll();
        mav.addObject("borrowData", list);
        mav.setViewName("BorrowData/list.html");
        return mav;
    }

    @GetMapping("/{userId}&{bookId}")
    public ModelAndView getBorrowedData(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId, ModelAndView mav) {
        UserDTO user = userGateway.findById(userId);
        BookDTO book = bookGateway.findById(bookId);
        System.out.println("DA " + userId + " " + bookId);

        return mav;
    }
}
