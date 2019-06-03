package com.ps.Common.DTO;

import java.util.Date;

public class BorrowDataDTO {
    private Date startDate;
    private Date endDate;
    private Integer userId;
    private Integer bookId;
    private FineDTO fineDTO;


    public BorrowDataDTO(Date startDate, Date endDate, Integer userId, Integer bookId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.bookId = bookId;
    }


    public FineDTO getFineDTO() {
        return fineDTO;
    }

    public void setFineDTO(FineDTO fineDTO) {
        this.fineDTO = fineDTO;
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

    public BorrowDataDTO() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
