package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggingAspect {


    @Before("execution(public * addAccount())()")
    public void beforeAddAccountAdvice() {
        System.out.println(CreateLogOutput(">>> LOG: Executing @Before advice for addAccount()"));
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
