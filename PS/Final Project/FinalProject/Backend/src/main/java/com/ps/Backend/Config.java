package com.ps.Backend;

import com.ps.Backend.Mapper.BookMapper;
import com.ps.Backend.Mapper.BorrowDataMapper;
import com.ps.Backend.Mapper.FineMapper;
import com.ps.Backend.Mapper.Impl.BookMapperImpl;
import com.ps.Backend.Mapper.Impl.BorrowDataMapperImpl;
import com.ps.Backend.Mapper.Impl.FineMapperImpl;
import com.ps.Backend.Mapper.Impl.UserMapperImpl;
import com.ps.Backend.Mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

//    @Bean
//    public UserService userService() { return new UserServiceImpl(); }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public BookMapper bookMapper() {
        return new BookMapperImpl();
    }

    @Bean
    public BorrowDataMapper borrowDataMapper() {
        return new BorrowDataMapperImpl();
    }

    @Bean
    public FineMapper fineMapper() { return new FineMapperImpl(); }

}
