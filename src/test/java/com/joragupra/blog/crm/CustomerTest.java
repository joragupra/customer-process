package com.joragupra.blog.crm;

import com.joragupra.blog.utils.time.TimeMachine;
import com.joragupra.blog.utils.time.TimeProvider;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CustomerTest {

    @After
    public void tearDown() {
        TimeMachine.reset();
    }

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

    @Test
    public void testIsSpecialOffersEligible_SeniorsUsersWithRecentPurchasesAreEligible() {
        Date moreThanOneYearAgo = Date.from(
                LocalDateTime.now().minusDays(366).atZone(ZoneId.systemDefault()).toInstant()
        );
        Customer oldCustomer = new Customer("00001", moreThanOneYearAgo);
        String aProductCode = "00001";
        String anotherProductCode = "00002";
        oldCustomer.buy(aProductCode);
        oldCustomer.buy(anotherProductCode);

        assertThat(oldCustomer.isSpecialOffersEligible(), is(true));
    }

    @Test
    public void testIsSpecialOffersEligible_SeniorsUsersWithNoRecentPurchaseAreNotEligible() {
        LocalDateTime moreThanOneYearAgo = LocalDateTime.now().minusDays(366);
        LocalDateTime fortyDaysAgo = LocalDateTime.now().minusDays(40);
        LocalDateTime thirtyFiveDaysAgo = LocalDateTime.now().minusDays(35);
        TimeMachine.goTo(moreThanOneYearAgo);
        Customer oldCustomer = new Customer("00001", TimeProvider.now());
        TimeMachine.goTo(fortyDaysAgo);
        String aProductCode = "00001";
        oldCustomer.buy(aProductCode);
        TimeMachine.goTo(thirtyFiveDaysAgo);
        String anotherProductCode = "00002";
        oldCustomer.buy(anotherProductCode);

        assertThat(oldCustomer.isSpecialOffersEligible(), is(false));
    }

    @Test
    public void testIsSpecialOffersEligible_NewUsersNotEligible() {
        Customer newCustomer = new Customer("00001", new Date());
        String aProductCode = "00001";
        String anotherProductCode = "00002";
        newCustomer.buy(aProductCode);
        newCustomer.buy(anotherProductCode);

        assertThat(newCustomer.isSpecialOffersEligible(), is(false));
    }

}