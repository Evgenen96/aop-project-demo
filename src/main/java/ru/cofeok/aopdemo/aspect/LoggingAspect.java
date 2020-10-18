package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.cofeok.aopdemo.entity.Account;

@Aspect
@Component
@Order(1)
public class LoggingAspect {


    @Before("execution(public * ru.cofeok.aopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println(CreateLogOutput(">>> LOG: Executing @Before advice for addAccount()"));
    }

    @AfterReturning(pointcut = "execution(public * ru.cofeok.aopdemo.dao.AccountDAO.getAccount())",
            returning = "result")
    public void afterGetAccountAdvice(JoinPoint theJoinPoint, Account result) {
        System.out.println(CreateLogOutput(">>> LOG: Executing @AfterReturning advice for addAccount()"));
        System.out.println(CreateLogOutput("   >>> " + result.toString()));
        result.setBalance(9999); // will change the real result
    }

    @AfterThrowing(pointcut = "execution(public * ru.cofeok.aopdemo.dao.AccountDAO.updateAccount())",
            throwing = "exception")
    public void throwingUpdateAccountAdvice(JoinPoint theJoinPoint, Throwable exception) {
        System.out.println(CreateLogOutput(">>> LOG: Executing @AfterThrowing advice for updateAccount()"));
        System.out.println(CreateLogOutput("   >>> " + exception.toString()));
    }

    @Around("execution(public * ru.cofeok.aopdemo.dao.AccountDAO.removeAccount(*))")
    public boolean aroundRemoveAccountAdvice(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        System.out.println(CreateLogOutput(">>> LOG: Executing @AroundThrowing advice for removeAccount()"));
        long begin = System.currentTimeMillis();
        boolean b = (Boolean)theProceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println(CreateLogOutput("   >>> Duration: " + duration/1000.0 + "seconds "));
        return b;
    }

    /**
     * This method just adds some codes to make console output blue color
     */
    public static String CreateLogOutput(String output) {
        StringBuilder sb = new StringBuilder(output);
        sb.insert(0, "\u001B[34m");
        sb.append("\u001B[0m");
        return sb.toString();
    }
}
