package io.josemyduarte.bank;

import java.util.List;

public interface StatementsPrinter {
    void printTransactions(List<Transaction> transactions);
}
