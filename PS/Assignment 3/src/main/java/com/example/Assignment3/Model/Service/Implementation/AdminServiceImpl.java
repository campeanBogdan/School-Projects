package com.example.Assignment3.Model.Service.Implementation;

import com.example.Assignment3.Model.Model.Admin;
import com.example.Assignment3.Model.Repository.AdminRepository;
import com.example.Assignment3.Model.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository repository;


    public Admin findAdmin(String username, String password) {
        return repository.findByUP(username, password);
    }
}
