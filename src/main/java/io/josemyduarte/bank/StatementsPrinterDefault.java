package io.josemyduarte.bank;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementsPrinterDefault implements StatementsPrinter {

    private final String HEADER_STATEMENT = "DATE       | AMOUNT  | BALANCE";

    private final Console console;
    private final DecimalFormat decimalFormat;

    private StatementsPrinterDefault(final Console console) {
        decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        decimalFormat.applyPattern("#.00");
        this.console = console;
    }

    public static StatementsPrinterDefault createStatementsPrinterDefault(final Console console) {
        return new StatementsPrinterDefault(console);
    }

    @Override
    public void printTransactions(final List<Transaction> transactions) {
        console.printLine(HEADER_STATEMENT);
        formatTransactions(transactions);
    }

    private void formatTransactions(final List<Transaction> transactions) {
        final AtomicInteger accumulatedBalance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> statementParser(transaction, accumulatedBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    private String statementParser(Transaction transaction, AtomicInteger balance) {
        return transaction.getDate() + " | "
                + decimalFormat.format(transaction.getAmount()) + " | "
                + decimalFormat.format(balance.addAndGet(transaction.getAmount()));
    }
}
