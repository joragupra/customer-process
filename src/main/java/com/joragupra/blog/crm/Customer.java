package com.joragupra.blog.crm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Customer {

    private String code;

    private Date createdAt;

    Customer(String code, Date createdAt) {
        this.code = code;
        this.createdAt = createdAt;
    }

    public String code() {
        return code;
    }

    public Date createdAt() {
        return createdAt;
    }

    public boolean isSenior() {
        return LocalDate.now().isAfter(
                createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(1)
        );
    }

}