package com.ps.Common.DTO;

public class BookDTO {
    private Integer id;
    private String title;
    private String description;
    private String author;
    private Integer stock;


    public BookDTO() { }

    public BookDTO(String title, String description, String author, Integer stock) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.stock = stock;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
