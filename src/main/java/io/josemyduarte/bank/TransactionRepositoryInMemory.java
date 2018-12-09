package io.josemyduarte.bank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepositoryInMemory implements TransactionRepository {

    private List<Transaction> transactions;
    private Clock clock;

    public TransactionRepositoryInMemory(final Clock clock) {
        this.clock = clock;
        this.transactions = new LinkedList<>();
    }

    @Override
    public void addDeposit(final int amount) {
        Transaction transaction = new Transaction(clock.todayAsString(), amount);
        transactions.add(transaction);
    }

    @Override
    public void addWithdrawal(final int amount) {
        Transaction transaction = new Transaction(clock.todayAsString(), -amount);
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
