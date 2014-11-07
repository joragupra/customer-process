package com.joragupra.blog.crm;

import java.util.Date;

public class Product {

    private String code;

    private Date purchasedAt;

    Product(String code, Date purchaseDate) {
        this.code = code;
        this.purchasedAt = purchaseDate;
    }

    public String code() {
        return code;
    }

    public Date purchasedAt() {
        return purchasedAt;
    }

    public boolean isRecent() {
        return false;
    }
}