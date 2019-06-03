package com.ps.Common.DTO;

import com.ps.Common.Enumeration.UserRole;

public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    //private String role;
    private UserRole role;


    public UserDTO() {}

//    public UserDTO(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }

        public UserDTO(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

        public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
