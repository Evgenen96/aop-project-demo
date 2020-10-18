package ru.cofeok.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cofeok.aopdemo.config.DemoConfig;
import ru.cofeok.aopdemo.dao.AccountDAO;
import ru.cofeok.aopdemo.dao.MembershipDAO;

public class MainApp {
    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from the spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
        // call the business methods
        theAccountDAO.addAccount();
        theAccountDAO.getAccount();
        theAccountDAO.updateAccount();
        theMembershipDAO.addAccount();
        context.close();

    }
}
