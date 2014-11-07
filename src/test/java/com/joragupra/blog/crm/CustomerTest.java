package com.joragupra.blog.crm;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CustomerTest {

    @Test
    public void testIsSeniorCustomer() {
        Date moreThanOneYearAgo = Date.from(
                LocalDateTime.now().minusDays(366).atZone(ZoneId.systemDefault()).toInstant()
        );
        Customer oldCustomer = new Customer("00001", moreThanOneYearAgo);

        assertThat(
                "Customer created more than one year ago should be senior", oldCustomer.isSenior(),
                is(true)
        );

        Customer newCustomer = new Customer("00002", new Date());

        assertThat(
                "Customer created right now should not be considered senior",
                newCustomer.isSenior(), is(false)
        );
    }

    @Test
    public void testBuy() {
        String aProductCode = "00001";
        String anotherProductCode = "00002";
        Customer customer = new Customer("00001", new Date());

        customer.buy(aProductCode);
        customer.buy(anotherProductCode);

        assertThat(
                aProductCode + " not found in products bought by customer",
                customer.boughtProducts().stream()
                        .anyMatch((product) -> aProductCode.equals(product.code())), is(true)
        );
        assertThat(
                anotherProductCode + " not found in products bought by customer",
                customer.boughtProducts().stream()
                        .anyMatch((product) -> anotherProductCode.equals(product.code())), is(true)
        );
    }

}