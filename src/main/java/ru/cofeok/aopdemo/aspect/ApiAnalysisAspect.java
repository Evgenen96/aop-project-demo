package ru.cofeok.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static ru.cofeok.aopdemo.aspect.LoggingAspect.CreateLogOutput;

@Aspect
@Component
@Order(2)
public class ApiAnalysisAspect {

    // example to show reusing pointcuts
    @Pointcut("execution(public * addAccount())")
    private void forDaoAdding() {
    }

    @Before("forDaoAdding()")
    public void performApiAnalytics() {
        System.out.println(CreateLogOutput(">>> INFO: Performing some API analytics"));
    }
}
