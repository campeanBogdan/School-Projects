package com.ps.Frontend.Controller;

import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.FineDTO;
import com.ps.Common.DTO.UserDTO;
import com.ps.Common.Enumeration.UserRole;
import com.ps.Common.DTO.LoginForm;
import com.ps.Frontend.Gateway.BookGateway;
import com.ps.Frontend.Gateway.BorrowDataGateway;
import com.ps.Frontend.Gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserGateway userGateway;
    private final BookGateway bookGateway;
    private final BorrowDataGateway borrowDataGateway;


    @Autowired
    public UserController(UserGateway userGateway, BookGateway bookGateway, BorrowDataGateway borrowDataGateway) {
        this.userGateway = userGateway;
        this.bookGateway = bookGateway;
        this.borrowDataGateway = borrowDataGateway;
    }

    // Login
    @GetMapping("/")
    public ModelAndView home(ModelAndView mav) {
        mav.setViewName("index.html");
        return mav;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginGet(ModelAndView mav) {
        mav.setViewName("User/admin-menu");
        return mav;
    }

    @PostMapping(value = "/login", params = "Login")
    public ModelAndView login(LoginForm loginForm, BindingResult result, ModelAndView mav, HttpServletRequest request, HttpSession session) {

        System.out.println(session.getId());
//        if (!session.isNew())
//            session.invalidate();

//        UserDTO dto = userGateway.login(loginForm);
//        System.out.println(dto.getId() + " " + dto.getUsername());

        String username = loginForm.getUsername();
        UserDTO userDTO = null;
        List<UserDTO> list = userGateway.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());

        if (list.size() > 0) {
            System.out.println("Start session: " + session.getId() + ". Start time: " + session.getCreationTime());

            userDTO = list.get(0);
            mav.addObject("user", userDTO);

            session.setAttribute("currentUser", userDTO);

            if (userDTO.getRole().equals(UserRole.ADMIN_ROLE))
                mav.setViewName("User/admin-menu.html");
            else
                mav.setViewName("User/user-menu.html");
        } else
            mav.setViewName("index.html");
        return mav;
    }

    @PostMapping(value = "/login", params = "Register")
    public ModelAndView register(LoginForm loginForm, BindingResult result, ModelAndView mav) {
        mav.setViewName("User/register.html");
        return mav;
    }

    // logout
    @PostMapping(value = "/", params = "Logout")
    public ModelAndView logout(ModelAndView mav, HttpServletRequest request, HttpSession session) {
        System.out.println("End session: " + session.getId() + ". End time: " + session.getLastAccessedTime());
        session.invalidate();
        mav.setViewName("index");
        return mav;
    }

    @GetMapping(value = "/", params = "Logout")
    public ModelAndView logoutView(ModelAndView mav, HttpServletRequest request, HttpSession session) {
        System.out.println("End session: " + session.getId() + ". End time: " + session.getLastAccessedTime());
        session.setAttribute("currentUser", null);
        session.invalidate();
        mav.setViewName("index");
        return mav;
    }

    // Register
    @PostMapping(value = "/register", params = "Register")
    public ModelAndView newAccount(LoginForm loginForm, BindingResult result, ModelAndView mav) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        UserDTO userDTO = new UserDTO(username, password, UserRole.USER_ROLE);
        userGateway.save(userDTO);
        mav.setViewName("index");
        return mav;
    }

    // spring security ??

// Register
//    @PostMapping(value = "/register", params = "Register")
//    public ModelAndView makeNewAccount(LoginForm loginForm, ModelAndView mav) {
//        String username = loginForm.getUsername();
//        UserDTO userDTO = null;
//        List<UserDTO> list = userGateway.findAll().stream()
//                .filter(user -> user.getUsername().equals(username))
//                .collect(Collectors.toList());
//        if (list.size() == 0)
//            mav.setViewName("User/index.html");
//        return mav;
//    }

// Admin View
    @GetMapping("/admin-menu")
    public ModelAndView adminView(ModelAndView mav) {
        mav.setViewName("User/admin-menu");
        return mav;
    }

    @GetMapping("/admin-view-users")
    public ModelAndView adminViewUsers(ModelAndView mav) {
        List<UserDTO> users = userGateway.findAll();
        mav.addObject("users", users);
        mav.setViewName("User/admin-view-users");
        return mav;
    }

//    @GetMapping("/admin-view-books")
//    public ModelAndView adminViewBooks(ModelAndView mav) {
//        List<BookDTO> books = bookGateway.findAll();
//        mav.addObject("books", books);
//        mav.setViewName("User/admin-view-books");
//        return mav;
//    }



    // afisare toti utilizatorii
    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav){
        List<UserDTO> list = userGateway.findAll();
        mav.addObject("users", list);
        mav.setViewName("User/list.html");
        return mav;
    }

    // afisare o carte dupa id
    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, ModelAndView mav) {
        UserDTO dto = userGateway.findById(id);
        mav.addObject("user", dto);
        mav.setViewName("User/user-details.html");
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

    // borrowData si fine la fiecare user in parte
    @GetMapping("/{userId}/{bookId}")
    public ModelAndView ss(@PathVariable("userId") Integer userId, @PathVariable("bookId") Integer bookId, ModelAndView mav) {
        BorrowDataDTO bdDTO = userGateway.getBorrowData(userId, bookId);
        UserDTO userDTO = userGateway.findById(userId);
        List<BookDTO> books = userGateway.getBorrowedBooks(userId);
        BookDTO bookDTO = null;
        FineDTO fine = bdDTO.getFineDTO();
        for (BookDTO b : books)
            if (b.getId().equals(bookId)) {
                bookDTO =  b;
                break;
            }

        mav.addObject("book", bookDTO);
        mav.addObject("user", userDTO);
        mav.addObject("borrowData", bdDTO);
        mav.addObject("fine", fine);
        mav.setViewName("Book/borrowed-data");
        return mav;
    }

}
