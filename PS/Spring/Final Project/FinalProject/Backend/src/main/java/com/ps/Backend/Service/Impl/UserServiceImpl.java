package com.ps.Backend.Service.Impl;

import com.ps.Backend.BackendApp;
import com.ps.Backend.Entity.Book;
import com.ps.Backend.Entity.BorrowData;
import com.ps.Backend.Entity.Fine;
import com.ps.Backend.Entity.User;
import com.ps.Backend.Mapper.BookMapper;
import com.ps.Backend.Mapper.BorrowDataMapper;
import com.ps.Backend.Mapper.FineMapper;
import com.ps.Backend.Mapper.UserMapper;
import com.ps.Backend.Repository.BookRepository;
import com.ps.Backend.Repository.BorrowDataRepository;
import com.ps.Backend.Repository.FineRepository;
import com.ps.Backend.Repository.UserRepository;
import com.ps.Backend.Service.UserService;
import com.ps.Common.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowDataRepository borrowDataRepository;
    private final FineRepository fineRepository;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final BorrowDataMapper borrowDataMapper;
    private final FineMapper fineMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository, BorrowDataRepository borrowDataRepository, FineRepository fineRepository, UserMapper userMapper, BookMapper bookMapper, BorrowDataMapper borrowDataMapper, FineMapper fineMapper) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowDataRepository = borrowDataRepository;
        this.fineRepository = fineRepository;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
        this.borrowDataMapper = borrowDataMapper;
        this.fineMapper = fineMapper;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> dtos = userRepository.findAll().stream()
                .map(e -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO = userMapper.toDTO(e);
                    return userDTO;
        }).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UserDTO findUser(Integer id) {
        User user = userRepository.findById(id).get();
        return userMapper.toDTO(user);
    }

    private boolean contains(Set<Book> set, Book book) {
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getId().equals(book.getId()))
                return true;
        }
        return false;
    }

    @Override
    public void borrowBook(Integer userId, Integer bookId, String startDate, String endDate) {
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();

        if (book.getStock() > 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateD = new Date();
            Date endDateD = new Date();
            try {
                startDateD = format.parse(startDate);
                endDateD = format.parse(endDate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            BorrowData borrowData = new BorrowData(user.getId(), book.getId(), startDateD, endDateD);
            Fine fine = new Fine(0);
            borrowData.setFine(fine);
            if (!this.contains(user.getBookList(), book)) {
                book.setStock(book.getStock() - 1);
                bookRepository.updateStock(bookId, book.getStock());
                user.getBookList().add(book);
                borrowDataRepository.save(borrowData);
                userRepository.save(user);
            }
        }
    }

    @Override
    public List<BookDTO> getOwnBooks(Integer id) {
        User user = userRepository.findById(id).get();
        List<BookDTO> dtos = user.getBookList().stream()
                .map(e -> {
                    BookDTO bookDTO = bookMapper.toDTO(e);
                    return bookDTO;
                }).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void returnBook(Integer userId, Integer bookId) {
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();
        BorrowData bd = borrowDataRepository.findByBook(userId, bookId);
        Fine fine = bd.getFine();
        System.out.println("FINE  " + fine.getId());

        Iterator<Book> iterator = user.getBookList().iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getId().equals(bookId)) {
                user.getBookList().remove(b);
                book.setStock(book.getStock() + 1);
                bd.setFine(null);
                fineRepository.deleteById(fine.getId());
                borrowDataRepository.deleteById(bd.getId());
                break;
            }
        }
        userRepository.save(user);
        bookRepository.save(book);
    }

    public void updateFine() {
        List<BorrowData> list = borrowDataRepository.findAll();
        for (BorrowData bd : list) {
            Fine fine = bd.getFine();
            Date endDate = bd.getEndDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            Date serverDate = BackendApp.serverDate;
            Calendar serverCal = BackendApp.cal;
            int endDays = cal.get(Calendar.DAY_OF_YEAR);
            int serverDays = serverCal.get(Calendar.DAY_OF_YEAR);
            int sum = 0;
            if (serverDays > endDays)
                sum = serverDays - endDays;
            fine.setSum(sum);
            borrowDataRepository.save(bd);
        }
    }

    @Override
    public Integer getFinePerBook(Integer userId, Integer bookId) {
        BorrowData bd = borrowDataRepository.findByBook(userId, bookId);
        Fine fine = bd.getFine();
        return fine.getSum();
    }

    @Override
    public Integer getTotalFine(Integer userId) {
        Set<Book> bookList = userRepository.findById(userId).get().getBookList();
        Integer sum = 0;
        for (Book b : bookList) {
            BorrowData bd = borrowDataRepository.findByBook(userId, b.getId());
            Fine fine = bd.getFine();
            sum += fine.getSum();
        }
        return sum;
    }

    @Override
    public BorrowDataDTO getBorrowData(Integer userId, Integer bookId) {
        BorrowData bd = borrowDataRepository.findByBook(userId, bookId);
        BorrowDataDTO bdDTO = borrowDataMapper.toDTO(bd);
        return bdDTO;
    }

    @Override
    public UserDTO login(LoginForm loginForm) {
        User user = userRepository.findByUP(loginForm.getUsername(), loginForm.getPassword());
        UserDTO dto = userMapper.toDTO(user);
        return dto;
    }
}
