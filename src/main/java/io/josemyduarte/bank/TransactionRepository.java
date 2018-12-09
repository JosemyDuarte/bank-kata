package io.josemyduarte.bank;

import java.util.List;

public interface TransactionRepository {
    void addDeposit(int amount);

    void addWithdrawal(int amount);

    List<Transaction> getTransactions();
}
