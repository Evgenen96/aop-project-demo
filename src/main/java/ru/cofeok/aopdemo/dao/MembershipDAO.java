package ru.cofeok.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
    public void addAccount(boolean a) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING MEMBERSHIP");
    }
}
