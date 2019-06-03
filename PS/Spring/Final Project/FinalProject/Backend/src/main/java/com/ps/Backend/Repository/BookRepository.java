package com.ps.Backend.Repository;

import com.ps.Backend.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Modifying
    @Transactional
    @Query("update Book b set b.stock=?2 where b.id=?1")
    void updateStock(Integer id, Integer stock);

    @Transactional
    @Query("SELECT b FROM Book b WHERE title LIKE ?1%")
    List<Book> findByTitleLike(@Param("title") String title);
}
