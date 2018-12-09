package io.josemyduarte.bank;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class ClockTest {

    @Test
    public void todayAsString() {
        Clock clock = new TesteableClock();
        Assert.assertThat(clock.todayAsString(), is(equalTo("12/08/1991")));
    }

    private class TesteableClock extends ClockDefault {

        @Override
        protected LocalDate today() {
            return LocalDate.of(1991, 8, 12);
        }
    }
}