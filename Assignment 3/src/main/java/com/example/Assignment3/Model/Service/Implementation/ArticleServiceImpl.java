package com.example.Assignment3.Model.Service.Implementation;

import com.example.Assignment3.Model.Model.Article;
import com.example.Assignment3.Model.Repository.ArticleRepository;
import com.example.Assignment3.Model.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;


    @Override
    public void addNewArticle(Article article) {
        repository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return repository.findAll();
    }

    @Override
    public void deleteArticle(String title) {
        Article article = repository.findByTitle(title);
        repository.deleteById(article.getId());
    }

    @Override
    public void updateArticle(String title) {

    }

    @Override
    public List<Article> getAllByLike(String str) {
        List<Article> list2 = repository.findAllByTitleLike(str);
        List<Integer> list = new LinkedList<Integer>();

        return list2;
    }

    @Override
    public Article getArticle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Article> getAllByAuthor(String author) {
        return repository.findAllByAuthor(author);
    }
}
