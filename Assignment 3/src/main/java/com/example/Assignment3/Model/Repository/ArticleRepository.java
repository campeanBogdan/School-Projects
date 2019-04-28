package com.example.Assignment3.Model.Repository;

import com.example.Assignment3.Model.Model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Id> {

    @Transactional
    @Query("SELECT a FROM Article a WHERE title=?1")
    Article findByTitle(String title);

    @Modifying
    @Transactional
    @Query("DELETE FROM Article a WHERE id=?1")
    void deleteById(Integer id);

    @Transactional
    @Query("SELECT a FROM Article a WHERE category=?1")
    List<Article> findAllByCategory(String category);

    @Transactional
    @Query("SELECT a FROM Article a WHERE title LIKE ?1% OR category LIKE ?1%")
    List<Article> findAllByTitleLike(@Param("str") String str);

    @Transactional
    @Query("SELECT a FROM Article a WHERE author=?1")
    List<Article> findAllByAuthor(String author);
}
