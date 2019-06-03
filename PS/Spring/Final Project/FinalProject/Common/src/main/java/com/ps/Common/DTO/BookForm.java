package com.ps.Common.DTO;

public class BookForm {

    private String title;
    private Integer userId;


    public BookForm(String title, Integer userId) {
        this.title = title;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
