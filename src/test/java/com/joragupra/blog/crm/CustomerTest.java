package com.joragupra.blog.crm;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CustomerTest {

    @Test
    public void testIsSeniorCustomer() {
        Customer customer = new Customer("00001", new Date());

        assertThat(customer.isSenior(), is(true));
    }

}