package com.ps.Backend.Repository;

import com.ps.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Query("select u from User u where u.username=?1 and u.password=?2")
    User findByUP(String username, String password);
}
