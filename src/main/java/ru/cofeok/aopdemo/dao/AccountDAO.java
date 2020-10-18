package ru.cofeok.aopdemo.dao;

import org.springframework.stereotype.Component;
import ru.cofeok.aopdemo.entity.Account;

@Component
public class AccountDAO {
    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");
    }

    public Account getAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: RETRIEVING ACCOUNT");
        Account demoAccount = new Account(100, 1000);
        return demoAccount;
    }

    public void updateAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: UPDATE ACCOUNT");
        try {
            throw new RuntimeException("work");
        } catch (RuntimeException e) {
            // AOP still works even if catch the exception
            // todo but it doesnt work for me
        }
    }

    public boolean removeAccount(Account account) {
        System.out.println(getClass() + ": DOING MY DB WORK: REMOVE ACCOUNT");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
