package ru.cofeok.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cofeok.aopdemo.config.DemoConfig;
import ru.cofeok.aopdemo.dao.AccountDAO;

public class MainApp {
    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        // get the bean from the spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
        // call the business method
        theAccountDAO.addAccount();
        context.close();

    }
}
