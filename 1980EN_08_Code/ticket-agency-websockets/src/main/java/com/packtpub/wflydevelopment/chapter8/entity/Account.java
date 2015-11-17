package com.packtpub.wflydevelopment.chapter8.entity;

/**
 * @author Yoshimasa Tanabe
 */
public class Account {

  private final int balance;

  public Account(int balance) {
    this.balance = balance;
  }

  public Account charge(int amount) {
    final int newBalance = balance - amount;

    if (newBalance < 0) {
      throw new IllegalArgumentException("Debit value on account!");
    }

    return new Account(newBalance);
  }

  public int getBalance() {
    return balance;
  }

  @Override
  public String toString() {
    return "Account{" +
      "balance=" + balance +
      '}';
  }

}