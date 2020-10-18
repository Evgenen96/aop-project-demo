package ru.cofeok.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cofeok.aopdemo.config.DemoConfig;
import ru.cofeok.aopdemo.dao.AccountDAO;
import ru.cofeok.aopdemo.dao.MembershipDAO;
import ru.cofeok.aopdemo.entity.Account;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from the spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
        // call the business methods
        theAccountDAO.addAccount();
        Account account = theAccountDAO.getAccount();
        System.out.println(account);
        theAccountDAO.updateAccount();
        theAccountDAO.removeAccount(new Account());
        theMembershipDAO.addAccount(true);
        context.close();
        Thread.sleep(5000);

    }
}
