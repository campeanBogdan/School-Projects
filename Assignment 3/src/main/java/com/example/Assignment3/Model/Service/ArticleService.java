package com.example.Assignment3.Model.Service;

import com.example.Assignment3.Model.Model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    void addNewArticle(Article article);
    List<Article> getAllArticles();
    void deleteArticle(String title);
    void updateArticle(String title);
    List<Article> getAllByLike(String str);
    Article getArticle(String title);
    List<Article> getAllByAuthor(String author);
}
