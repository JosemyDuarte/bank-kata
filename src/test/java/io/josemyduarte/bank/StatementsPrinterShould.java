package io.josemyduarte.bank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementsPrinterShould {

    @Mock
    Console console;

    private StatementsPrinter statementsPrinter;
    private List<Transaction> EMPTY_TRANSACTIONS = Collections.emptyList();

    @Before
    public void setUp() {
        statementsPrinter = new StatementsPrinter(console);
    }

    @Test
    public void alwaysPrintAHeader() {
        statementsPrinter.printTransactions(EMPTY_TRANSACTIONS);

        verify(console).printLine("DATE       | AMOUNT  | BALANCE");
    }

    @Test
    public void printTransactionsOnReverseOrder() {
        List<Transaction> transactions = withTransactions(
                deposit("01/04/2014", 1000),
                withdrawal("02/04/2014", 100),
                deposit("10/04/2014", 500)
        );

        statementsPrinter.printTransactions(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE       | AMOUNT  | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }

    private List<Transaction> withTransactions(Transaction... transactions) {
        return Arrays.asList(transactions);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}