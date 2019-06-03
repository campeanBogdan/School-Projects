package com.ps.Frontend.Form;

public class ReturnBookForm {
    private Integer userId;
    private Integer bookId;

    public ReturnBookForm(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public ReturnBookForm() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
