package com.ps.Common.DTO;

public class FineDTO {

    private Integer id;
    private Integer sum;


    public FineDTO(Integer sum) {
        this.sum = sum;
    }

    public FineDTO() {
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
}
