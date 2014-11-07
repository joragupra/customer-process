package com.joragupra.blog.crm;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @Test
    public void testIsRecentPurchase() {
        Date moreThanOneMonthAgo = Date.from(
                LocalDateTime.now().minusDays(32).atZone(ZoneId.systemDefault()).toInstant()
        );
        Product oldPurchase = new Product("00001", moreThanOneMonthAgo);

        assertThat(
                "A purchase done more than one month ago should not be considered a recent purchase",
                oldPurchase.isRecent(), is(false)
        );

        Product newPurchase = new Product("00002", new Date());

        assertThat("A purchase done right now is a recent purchase", newPurchase.isRecent(), is(true));
    }

}
