package com.ps.Backend.Entity;

import javax.persistence.*;

@Entity
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer sum;

    @OneToOne(mappedBy = "fine")
    private BorrowData borrowData;


    public Fine() {
    }

    public Fine(Integer sum) {
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public BorrowData getBorrowData() {
        return borrowData;
    }

    public void setBorrowData(BorrowData borrowData) {
        this.borrowData = borrowData;
    }
}
