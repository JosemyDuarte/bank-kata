package io.josemyduarte.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClockDefault implements Clock {

    private static final DateTimeFormatter dd_MM_yyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    ClockDefault() {
    }

    public static ClockDefault createClockDefault() {
        return new ClockDefault();
    }

    @Override
    public String todayAsString() {
        LocalDate today = today();
        return today.format(dd_MM_yyyy);
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
