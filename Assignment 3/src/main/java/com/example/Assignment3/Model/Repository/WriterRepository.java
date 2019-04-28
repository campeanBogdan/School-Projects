package com.example.Assignment3.Model.Repository;

import com.example.Assignment3.Model.Model.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.transaction.Transactional;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Id> {

    @Transactional
    @Query("SELECT w FROM Writer w WHERE username=?1")
    Writer findByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Writer w WHERE id=?1")
    void deleteById(Integer id);

    @Transactional
    @Query("SELECT w FROM Writer w WHERE username=?1 AND password=?2")
    Writer findByUP(String username, String password);

}
