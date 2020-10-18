package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.cofeok.aopdemo.entity.Account;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

    private Logger myLogger =
            Logger.getLogger(getClass().getName());

    @Before("execution(public * ru.cofeok.aopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice() {
        myLogger.info(CreateLogOutput(">>> LOG: Executing @Before advice for addAccount()"));
    }

    @AfterReturning(pointcut = "execution(public * ru.cofeok.aopdemo.dao.AccountDAO.getAccount())",
            returning = "result")
    public void afterGetAccountAdvice(JoinPoint theJoinPoint, Account result) {
        myLogger.info(CreateLogOutput(">>> LOG: Executing @AfterReturning advice for addAccount()"));
        myLogger.info(CreateLogOutput("   >>> " + result.toString()));
        result.setBalance(9999); // will change the real result
    }

    @AfterThrowing(pointcut = "execution(public * ru.cofeok.aopdemo.dao.AccountDAO.updateAccount())",
            throwing = "exception")
    public void throwingUpdateAccountAdvice(JoinPoint theJoinPoint, Throwable exception) {
        myLogger.info(CreateLogOutput(">>> LOG: Executing @AfterThrowing advice for updateAccount()"));
        myLogger.warning(CreateLogOutput("   >>> " + exception.toString()));
    }

    @Around("execution(public * ru.cofeok.aopdemo.dao.AccountDAO.removeAccount(*))")
    public boolean aroundRemoveAccountAdvice(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        myLogger.info(CreateLogOutput(">>> LOG: Executing @AroundThrowing advice for removeAccount()"));
        long begin = System.currentTimeMillis();
        boolean b;
        try {
            b = (Boolean) theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            myLogger.warning("@Around advice: handling an exception expect of main program");
            // use default value for returning default or rethrow the exception
            b = false;
            // rethrow exception
            // throw exc;
        }
        long end = System.currentTimeMillis();
        long duration = end - begin;
        myLogger.info(CreateLogOutput("   >>> Duration: " + duration / 1000.0 + "seconds "));
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
