package com.ps.Backend;

import com.ps.Backend.Entity.Book;
import com.ps.Backend.Entity.BorrowData;
import com.ps.Backend.Entity.Fine;
import com.ps.Backend.Entity.User;
import com.ps.Backend.Mapper.UserMapper;
import com.ps.Backend.REST.Impl.BookRestController;
import com.ps.Backend.REST.Impl.BorrowDataRestController;
import com.ps.Backend.REST.Impl.FineRestController;
import com.ps.Backend.REST.Impl.UserRestController;
import com.ps.Backend.REST.UserRestApi;
import com.ps.Backend.Service.UserService;
import com.ps.Common.DTO.BookDTO;
import com.ps.Common.DTO.BorrowDataDTO;
import com.ps.Common.DTO.FineDTO;
import com.ps.Common.DTO.UserDTO;
import com.ps.Common.Enumeration.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class BackendApp {

    public static DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static Date serverDate = new Date(); //format.parse("25/02/2019");
    public static Calendar cal = Calendar.getInstance();
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");



    public static void main(String[] args) {

// data curenta
        try {
            //serverDate = format.parse("10-02-2019");
            cal.setTime(serverDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(BackendApp.class);

    }


    @Bean
    public CommandLineRunner data(UserRestController controller) {
        return (args -> {

            Thread thread = new Thread() {
                public  void run() {
                    Calendar cal = Calendar.getInstance();
                    while (true) {
                        LocalDateTime now = LocalDateTime.now();
                        controller.updateFine();
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        });
    }
}
