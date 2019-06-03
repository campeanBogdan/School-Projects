package com.ps.Frontend;

import com.ps.Common.DTO.UserDTO;
import com.ps.Frontend.Gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserGateway userGateway;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userGateway.findByUsername(username);
        if (userDTO != null) {
            UserDetails loginUser = User
                    .withUsername(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .roles(userDTO.getRole().toString())
                    .build();
            return loginUser;
        }
        return null;
    }
}

//package com.ps.Frontend;
//
//import com.ps.Common.DTO.UserDTO;
//import com.ps.Frontend.Gateway.UserGateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    private final UserGateway userGateway;
////    private final UserDetailsImpl userDetailsImpl;
////
////
////    @Autowired
////    public WebSecurityConfig(UserGateway userGateway, UserDetailsImpl userDetailsImpl) {
////        this.userGateway = userGateway;
////        this.userDetailsImpl = userDetailsImpl;
////    }
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//////        auth.inMemoryAuthentication()
//////                .withUser("user")
//////                .password("user")
//////                .roles("USER_ROLE");
////    }
////
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
////    }
////
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
////        return new UserDetailsImpl();
////    }
//}
