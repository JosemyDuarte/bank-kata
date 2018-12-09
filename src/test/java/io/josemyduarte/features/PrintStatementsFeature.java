package io.josemyduarte.features;

import io.josemyduarte.bank.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementsFeature {

    @Mock
    Console console;
    @Mock
    Clock clock;

    @Test
    public void givenSomeTransactions_anAccountShouldPrintTransactionsInReverseOrder() {
        given(clock.todayAsString()).willReturn("01/04/2014", "02/04/2014", "10/04/2014");
        Account account = new Account(new TransactionRepositoryInMemory(clock), StatementsPrinterDefault.createStatementsPrinterDefault(console));
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
