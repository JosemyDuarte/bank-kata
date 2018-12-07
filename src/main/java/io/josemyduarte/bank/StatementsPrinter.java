package io.josemyduarte.bank;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementsPrinter {
    private final String HEADER_STATEMENT = "DATE       | AMOUNT  | BALANCE";

    private final Console console;

    public StatementsPrinter(final Console console) {
        this.console = console;
    }

    protected void printTransactions(final List<Transaction> transactions) {
        console.printLine(HEADER_STATEMENT);

        final AtomicInteger accumulatedBalance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> statementParser(transaction, accumulatedBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(e -> System.out.println(e));
    }

    private String statementParser(Transaction transaction, AtomicInteger balance) {
        return transaction.getDate() + " | " + transaction.getAmount() + " | " + balance.addAndGet(transaction.getAmount());
    }
}
