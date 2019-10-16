package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.UniqueTransactionList;

/**
 * Bank account of the user.
 */
public class BankAccount implements ReadOnlyBankAccount {
    private Amount balance;
    private UniqueTransactionList transactions;

    public BankAccount() {
        balance = new Amount(0);
        transactions = new UniqueTransactionList();
    }

    public BankAccount(ReadOnlyBankAccount bankAccount) {
        this();
        resetData(bankAccount);
    }

    private void resetData(ReadOnlyBankAccount bankAccount) {
        requireNonNull(bankAccount);

        setTransactions(bankAccount.getTransactionHistory());
    }

    private void setTransactions(List<Transaction> transactionHistory) {
        this.transactions.setTransactions(transactionHistory);
    }

    /**
     * Adds a transaction to the bank account.
     * @param txn Transaction to be added to bank account.
     */
    public void addTransaction(Transaction txn) {
        transactions.add(txn);
        Amount newBalance = txn.handleBalance(this.balance);
        this.balance = newBalance;
    }

    /**
     * Checks if transaction exists in bank account.
     * @param transaction Transaction to be checked.
     * @return true if transaction is in bank account, else otherwise.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    @Override
    public ObservableList<Transaction> getTransactionHistory() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BankAccount)) {
            return false;
        }

        BankAccount otherBankAccount = (BankAccount) other;
        return this.balance.equals(otherBankAccount.balance)
                && this.transactions.equals(otherBankAccount.transactions);
    }
}
