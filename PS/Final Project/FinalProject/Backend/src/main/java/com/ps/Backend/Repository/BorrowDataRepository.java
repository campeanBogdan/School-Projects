package com.ps.Backend.Repository;

import com.ps.Backend.Entity.BorrowData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BorrowDataRepository extends JpaRepository<BorrowData, Integer> {

    @Transactional
    @Query("select bd from BorrowData bd where bd.userId=?1 and bd.bookId=?2")
    BorrowData findByBook(Integer userId, Integer bookId);

    @Transactional
    @Modifying
    @Query("delete from BorrowData bd where bd.id=?1")
    void deleteById(Integer id);
}
