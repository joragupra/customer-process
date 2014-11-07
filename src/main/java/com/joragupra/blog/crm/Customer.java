package com.joragupra.blog.crm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Customer {

    private String code;

    private Date createdAt;

    private Set<Product> purchases;

    Customer(String code, Date createdAt) {
        this.code = code;
        this.createdAt = createdAt;
        this.purchases = new HashSet<>();
    }

    public String code() {
        return code;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Set<Product> boughtProducts() {
        return new HashSet<>(purchases);
    }

    public boolean isSenior() {
        return LocalDate.now().isAfter(
                createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(1)
        );
    }

    public void buy(String productCode) {
        purchases.add(new Product(productCode, new Date()));
    }

    public boolean isSpecialOffersEligible() {
        return isSenior();
    }

}