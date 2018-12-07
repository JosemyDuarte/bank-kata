package io.josemyduarte.bank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions;
    private Clock clock;

    public TransactionRepository(final Clock clock) {
        this.clock = clock;
        this.transactions = new LinkedList<>();
    }

    protected void addDeposit(final int amount) {
        Transaction transaction = new Transaction(clock.todayAsString(), amount);
        transactions.add(transaction);
    }

    protected void addWithdrawal(final int amount) {
        Transaction transaction = new Transaction(clock.todayAsString(), -amount);
        transactions.add(transaction);
    }

    protected List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
