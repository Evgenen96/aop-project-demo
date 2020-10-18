package ru.cofeok.aopdemo.entity;

public class Account {

    private int id;
    private int login;
    private long balance;

    public Account() {

    }

    public Account(int login, long balance) {
        this.login = login;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "login=" + login +
                ", balance=" + balance +
                '}';
    }
}
