package ru.cofeok.aopdemo.dao;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MembershipDAO {

    private Logger myLogger =
            Logger.getLogger(getClass().getName());
    public void addAccount(boolean a) {
        myLogger.info(getClass() + ": DOING MY DB WORK: ADDING MEMBERSHIP");
    }
}
