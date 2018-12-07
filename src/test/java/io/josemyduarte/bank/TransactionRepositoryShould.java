package io.josemyduarte.bank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private final String TODAY = "12/08/1992";
    @Mock
    Clock clock;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        given(clock.todayAsString()).willReturn(TODAY);

        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    public void saveADepositTransaction() {
        transactionRepository.addDeposit(10);

        final List<Transaction> transactions = transactionRepository.getTransactions();

        assertThat(transactions.size(), is(1));
        assertThat("Transactions contains added deposit", transactions.get(0), is(transaction(TODAY, 10)));
    }

    @Test
    public void saveAWithdrawalTransaction() {
        transactionRepository.addWithdrawal(10);

        final List<Transaction> transactions = transactionRepository.getTransactions();

        assertThat(transactions.size(), is(1));
        assertThat("Transactions contains added withdrawal", transactions.get(0), is(transaction(TODAY, -10)));
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }

}