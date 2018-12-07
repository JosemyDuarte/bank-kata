package io.josemyduarte.bank;

import java.util.Objects;

public class Transaction {

    private final String date;
    private final int amount;

    public Transaction(final String date, final int amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    protected int getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Transaction that = (Transaction) o;
        return amount == that.amount &&
                Objects.equals(date, that.date);
    }
}
