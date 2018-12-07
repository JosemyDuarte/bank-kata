package io.josemyduarte.bank;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ClockTest {

    @Test
    public void todayAsString() {
        Clock clock = new TesteableClock();
        Assert.assertThat(clock.todayAsString(), is(equalTo("12/08/1991")));

    }

    private class TesteableClock extends Clock {

        @Override
        protected LocalDate today() {
            return LocalDate.of(1991, 8, 12);
        }
    }
}