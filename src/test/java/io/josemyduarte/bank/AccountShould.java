package io.josemyduarte.bank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    private StatementsPrinter statementsPrinter;

    private Account account;

    @Before
    public void setUp() {
        account = new Account(transactionRepository, statementsPrinter);
    }

    @Test
    public void createADepositTransaction() {
        account.deposit(10);
        verify(transactionRepository).addDeposit(10);
    }

    @Test
    public void createAWithdrawalTransaction() {
        account.withdrawal(99);
        verify(transactionRepository).addWithdrawal(99);
    }

    @Test
    public void printStatementsOfTransactions() {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        given(transactionRepository.getTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementsPrinter).printTransactions(transactions);

    }

}