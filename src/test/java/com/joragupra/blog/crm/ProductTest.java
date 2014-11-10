package com.joragupra.blog.crm;

import com.joragupra.blog.utils.time.TimeMachine;
import com.joragupra.blog.utils.time.TimeProvider;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @Test
    public void testIsRecentPurchase() {
        LocalDateTime moreThanOneMonthAgo = LocalDateTime.now().minusDays(32);
        TimeMachine.goTo(moreThanOneMonthAgo);

        Product oldPurchase = new Product("00001", TimeProvider.now());

        assertThat(
                "A purchase done more than one month ago should not be considered a recent purchase",
                oldPurchase.isRecent(), is(false)
        );

        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        TimeMachine.goTo(oneWeekAgo);

        Product newPurchase = new Product("00002", TimeProvider.now());

        assertThat("A purchase done right now is a recent purchase", newPurchase.isRecent(), is(true));
    }

}
