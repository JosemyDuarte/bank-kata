package io.josemyduarte.features;

import io.josemyduarte.bank.Account;
import io.josemyduarte.bank.Console;
import io.josemyduarte.bank.StatementsPrinter;
import io.josemyduarte.bank.TransactionRepository;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.Mockito.inOrder;

public class PrintStatementsFeature {

    @Mock
    Console console;

    @Test
    public void givenSomeTransactions_anAccountShouldPrintTransactionsInReverseOrder() {
        Account account = new Account(new TransactionRepository(), new StatementsPrinter());
        account.deposit(1000);
        account.withdrawal(100);
        account.deposit(500);

        account.printStatement();

        InOrder inOrder = inOrder(console);

        inOrder.verify(console).printLine("DATE       | AMOUNT  | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");

    }
}
