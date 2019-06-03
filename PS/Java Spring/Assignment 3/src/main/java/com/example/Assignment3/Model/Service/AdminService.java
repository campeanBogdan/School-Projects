package com.example.Assignment3.Model.Service;

import com.example.Assignment3.Model.Model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public Admin findAdmin(String username, String password);
}
