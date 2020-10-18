package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // example to show reusing pointcuts
    @Pointcut("execution(public * addAccount())")
    private void forDaoAdding() {
    }

    // next two are for combining
    @Pointcut("execution(public * ru.cofeok.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(public * ru.cofeok.aopdemo.dao.*.update*(..))")
    private void forDaoUpdate() {
    }


    @Before("forDaoAdding()")
    public void beforeAddAccountAdvice() {
        System.out.println(CreateLogOutput(">>> LOG: Executing @Before advice for addAccount()"));
    }

    @Before("forDaoAdding()")
    public void performApiAnalytics() {
        System.out.println(CreateLogOutput(">>> INFO: Performing some API analytics"));
    }

    // combining pointcuts
    @Before("forDaoPackage() && !forDaoUpdate()")
    public void securityCheck() {
        System.out.println(CreateLogOutput(">>> SEC: Making some security checks"));
    }

    /**
     * This method just adds some codes to make console output blue color
     */
    private String CreateLogOutput(String output) {
        StringBuilder sb = new StringBuilder(output);
        sb.insert(0, "\u001B[34m");
        sb.append("\u001B[0m");
        return sb.toString();
    }
}
