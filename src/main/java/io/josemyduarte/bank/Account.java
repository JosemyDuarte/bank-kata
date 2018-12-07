package io.josemyduarte.bank;

public class Account {

    private final TransactionRepository transactionRepository;
    private final StatementsPrinter statementsPrinter;

    public Account(final TransactionRepository transactionRepository, final StatementsPrinter statementsPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementsPrinter = statementsPrinter;
    }

    public void deposit(final int amount) {
        transactionRepository.addDeposit(amount);
    }

    public void withdrawal(final int amount) {
        transactionRepository.addWithdrawal(amount);
    }

    public void printStatement() {
        statementsPrinter.printTransactions(transactionRepository.getTransactions());
    }
}
