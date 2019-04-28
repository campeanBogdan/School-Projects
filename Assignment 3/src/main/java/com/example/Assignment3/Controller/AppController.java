package com.example.Assignment3.Controller;

import com.example.Assignment3.Model.Model.Admin;
import com.example.Assignment3.Model.Model.Article;
import com.example.Assignment3.Model.Model.Option;
import com.example.Assignment3.Model.Model.Writer;
import com.example.Assignment3.Model.Repository.ArticleRepository;
import com.example.Assignment3.Model.Service.AdminService;
import com.example.Assignment3.Model.Service.ArticleService;
import com.example.Assignment3.Model.Service.WriterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AppController {

    // SERVICE

    @Autowired
    private ArticleService articleService;
    @Autowired
    private WriterService writerService;
    @Autowired
    private AdminService adminService;
    private Option option = new Option();
    private Admin admin = new Admin();
    private Writer writer = new Writer();
    private Article article = new Article();
    private List<Article> sortedArticles = null;


// article
    public void addNewArticle(Article article) {
        articleService.addNewArticle(article);
    }

    public void deleteArticle(String title) {
        articleService.deleteArticle(title);
    }

    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    public List<Article> getAllArticlesByLike(String str) { return articleService.getAllByLike(str); }

// writer
    public void addNewWriter(Writer writer) {
        writerService.addNewWriter(writer);
    }

    public void deleteWriter(String username) {
        writerService.deleteWriter(username);
    }

    public List<Writer> getAllWriters() {
        return writerService.getAllWRiters();
    }


    // WEB

// reader
    // homepage
    @GetMapping("/index")
    public String index() {
        sortedArticles = articleService.getAllArticles();
        return "index";
    }

    // reading page
    @RequestMapping(value = "/reader", method = RequestMethod.GET)
    public String showAllArticles(Model model) {
        if (this.option == null) {
            sortedArticles = articleService.getAllArticles();
        }
        else
            if (this.option.getOption().equals("")){
                sortedArticles = articleService.getAllArticles();
            } else {
                String str = this.option.getOption();
                sortedArticles = articleService.getAllByLike(str);
            }
        Collections.sort(sortedArticles, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        model.addAttribute("articles", sortedArticles);
        return "reader";
    }

// search category, title by
    @RequestMapping(value = "/reader", method = RequestMethod.POST)
    public String getReaderOption(Option option, BindingResult result, Model model) {
        this.option = new Option();
        this.option.setOption(option.getOption());
        System.out.println(option.getOption());
        return "reader-action";
    }


// login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() { return "login"; }

    @RequestMapping(value = "/login", params = "LoginAdmin", method = RequestMethod.POST)
    public String getLoginValues(Admin admin, BindingResult result, Model model) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        Admin findAdmin = adminService.findAdmin(username, password);
        this.admin = new Admin(admin.getUsername(), admin.getPassword());
        if (findAdmin != null) {
            return "admin-page";
        }

        return "login";
    }

    @RequestMapping(value = "/login", params = "LoginWriter", method = RequestMethod.POST)
    public String getLoginValues(Writer writer, BindingResult result, Model model) {
        String username = writer.getUsername();
        String password = writer.getPassword();
        Writer findWriter = writerService.findWriter(username, password);
        this.writer = new Writer(writer.getUsername(), writer.getPassword());
        if (findWriter != null) {
            return "writer-page";
        }
        return "login";
    }


// admin-page
    // admin's name
    @RequestMapping(value = "/admin-page", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("admin", this.admin);
        return "admin-page";
    }


    // create new writer
    @RequestMapping(value = "/admin-page", params = "Create", method = RequestMethod.POST)
    public String insertNewWriter(Writer writer, BindingResult result, Model model) {
        String username = writer.getUsername();
        String password = writer.getPassword();
        Writer findWriter = writerService.findWriter(username);
        this.writer = new Writer(writer.getUsername(), writer.getPassword());
        if (findWriter == null) {
            writerService.addNewWriter(writer);
            return "admin-insert-writer-success";
        }
        return "admin-insert-writer-fail";
    }

    // delete writer
    @RequestMapping(value = "/admin-page", params = "Delete", method = RequestMethod.POST)
    public String deleteWriter(Option option, BindingResult result, Model model) {
        String username = option.getOption();
        writerService.deleteWriter(username);
        return "admin-delete-writer";
    }

    // insert new writer - success
    @RequestMapping(value = "/admin-insert-writer-success", method = RequestMethod.GET)
    public String insertSuccessfully(Model model) {
        model.addAttribute("writer", this.writer);
        return "admin-insert-writer-success";
    }

// admin-view-writers
    // show all writers
    @RequestMapping(value = "/admin-view-writers", method = RequestMethod.GET)
    public String getAllWriters(Model model) {
        List<Writer> list = writerService.getAllWRiters();
        Collections.sort(list, new Comparator<Writer>() {
            @Override
            public int compare(Writer o1, Writer o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        model.addAttribute("writers", list);
        return "admin-view-writers";
    }

// writer
    // writer-page
    @RequestMapping(value = "/writer-page", method = RequestMethod.GET)
    public String writerPage(Model model) {
        model.addAttribute("writer", this.writer);
        return "writer-page";
    }

    // insert new article
    @RequestMapping(value = "/writer-page", params = "Create", method = RequestMethod.POST)
    public String insertNewArticle(Article article, BindingResult result, Model model) {
        this.article = new Article(article.getTitle(), article.getAbs(), article.getAuthor(), article.getCategory(), article.getBody());
        Article findArticle = articleService.getArticle(article.getTitle());
        if (findArticle == null) {
            articleService.addNewArticle(article);
            return "writer-insert-article";
        }
        return "writer-delete-article-fail";
    }

    // insert article success
    @RequestMapping(value = "/writer-insert-article", method = RequestMethod.GET)
    public String insertArticleSuccess(Model model) {
        model.addAttribute("article", this.article);
        return "writer-insert-article";
    }

    // delete article
    @RequestMapping(value = "/writer-page", params = "Delete", method = RequestMethod.POST)
    public String insertNewArticle(Option option, BindingResult result, Model model) {
        String title = option.getOption();
        articleService.deleteArticle(title);
        return "writer-delete-article";
    }

    // view-articles
    @RequestMapping(value = "/writer-view-articles", method = RequestMethod.GET)
    public String getMyArticles(Model model) {
        List<Article> list = articleService.getAllByAuthor(this.writer.getUsername());
        Collections.sort(list, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        model.addAttribute("articles", list);
        return "writer-view-articles";
    }
}

