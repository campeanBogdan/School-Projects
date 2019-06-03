package com.example.Assignment3.Model.Repository;

import com.example.Assignment3.Model.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.transaction.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Id> {

    @Transactional
    @Query("SELECT a FROM Admin a WHERE username=?1 AND password=?2")
    Admin findByUP(String username, String password);
}
