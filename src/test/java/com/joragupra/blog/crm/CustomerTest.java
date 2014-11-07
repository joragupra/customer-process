package com.joragupra.blog.crm;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

}